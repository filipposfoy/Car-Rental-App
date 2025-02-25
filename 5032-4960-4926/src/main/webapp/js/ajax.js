const username = document.getElementById('customerName');
const surname = document.getElementById('customerSurname');
const address = document.getElementById('customerAddress');
const dob = document.getElementById('customerDOB');
const lnumber = document.getElementById('licenseNumber');
const creditcard = document.getElementById('creditCard');
const msg = document.getElementById('msg')
const brand = document.getElementById('supplyBrand');
const model = document.getElementById('supplyModel');
const color = document.getElementById('supplyColor');
const cost = document.getElementById('supplyCost');
const customer_id = document.getElementById('Booking_CustomerID');
const licenceNumber = document.getElementById('arithmospinakidwn');
const driver_id = document.getElementById('Booking_DriverID');
const startingdate = document.getElementById('startingDate');
const endingdate = document.getElementById('endingDate');

const switchForm = (type) => {
    $('#formsContainer form').hide();
    
    switch (type) {
        case 'Add':
            $('#addCustomerForm').show();
            break;

        case 'Supply':
            $('#supplyVehiclesForm').show();
            break;
            
        case 'Search':
            $('#searchVehicleForm').show();
            break;

        case 'Renting':
            showVehiclesforRent('filtered');
            $('#rentVehicleForm').show();
            break;
         
        case 'Returns':
            $('#returnVehicleForm').show();
            break;

        case 'Repairs':
            $('#repairVehicleForm').show();
            break;
            
        case 'malfunction':
            $('#malfunctionForm').show();
            break;

        case 'accident':
            $('#accidentForm').show();
            break;
            
        case 'Questions':
            $('#questionsForm').show();
            break;
    }
}

const getType = (str) => {
    return str;
}

const repairVehicle = (typeOfRepair) => {
    
    if (document.getElementById('getRepairId').value === "") {
        onError(document.getElementById('getRepairId'), "Enter a vehicle id");
        $("#"+typeOfRepair).prop("checked", false);
        return;
    } else {  
        onSuccess(document.getElementById('getRepairId'));
    }

    const req = {
        vehicle_id: document.getElementById('getRepairId').value,
        type: typeOfRepair,
    };
    console.log(req);

    var xhr = new XMLHttpRequest();
    var queryParams;
    
    xhr.onload = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                const responseData = JSON.parse(xhr.responseText);
                document.getElementById('smallRep').innerText = responseData.str;
                document.getElementById('smallRep').style.color = responseData.color;
                document.getElementById('getRepairId').style.borderColor = responseData.color;                
            } else {
                console.log("not good.");
                reject("Error occurred during the request.");
            }
        } else {
            console.log("not good.");
            reject("Error occurred during the request.");
        }
    };
    
    console.log(req);

    const url = "repairVehicle";
    xhr.open("POST", url);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    queryParams = `user=${encodeURIComponent(JSON.stringify(req))}`;
    xhr.send(queryParams);
}

const showVehicles = (type) => {
    var xhr = new XMLHttpRequest();
    var queryParams;

    xhr.onload = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                const responseData = JSON.parse(xhr.responseText);

                console.log(responseData.str);
                var tableContainer = document.getElementById("vehicleTable");
                var table = document.createElement("table");
                var row = table.insertRow();

                for (let i = 0; i < responseData.str; i++) {
                    var dataItem = responseData['' + i];
                    var cell = row.insertCell();
                    cell.innerHTML = dataItem;
                }
                tableContainer.innerHTML = "";
                tableContainer.appendChild(table);
            } else {
               console.log("Status not 200:", xhr.status);
            }
        } else {
            console.error("Error occurred during the request:", error);
        }
    };

    const url = "searchVehicle";
    xhr.open("POST", url);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    queryParams = `user=${encodeURIComponent(JSON.stringify({ type }))}`;
    xhr.send(queryParams);
};

const showVehiclesforRent = (type) => {
    var xhr = new XMLHttpRequest();
    var queryParams;

    xhr.onload = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                const responseData = JSON.parse(xhr.responseText);
                var tableContainer = document.getElementById("toRent");

                var table = document.createElement("table");
                var row = table.insertRow();

                for (let i = 0; i < responseData.str; i++) {
                    var dataItem = responseData['' + i];
                    var cell = row.insertCell();
                    cell.innerHTML = dataItem;
                }
                tableContainer.innerHTML = "";
                tableContainer.appendChild(table);
            } else {
                console.log("not good.");
                reject("Error occurred during the request.");
            }
        } else {
            console.log("not good.");
            reject("Error occurred during the request.");
        }
    };

    const url = "searchVehicle";
    xhr.open("POST", url);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    queryParams = `user=${encodeURIComponent(JSON.stringify({type}))}`;
    xhr.send(queryParams);
};

const toggleFields = () => {
    const type = document.querySelector('input[name="type"]:checked').value;
    document.getElementById('rvar1').style.display = (type === "Car") ? "table-row" : "none";
    document.getElementById('rvar2').style.display = (type === "Car") ? "table-row" : "none";
    if (type === "Car") {
        document.getElementById('rvar3').innerText = "Licence number";
        document.getElementById('rvar3').placeholder = "licence plate number";
    } else if (type === "Bike") {
        document.getElementById('rvar3').innerText = "Bike id";
        document.getElementById('rvar3').placeholder = "bike id number";
    } else {
        document.getElementById('rvar3').innerText = "Scooter id";
        document.getElementById('rvar3').placeholder = "scooter id number";
    }
    
    document.getElementById('rvar4').style.display = (type === "Car") ? "table-row" : "none";
}

const getSupply = () => {
    const type = document.querySelector('input[name="type"]:checked').value;
    const JSON = {
        brand: brand.value,
        model: model.value,
        color: color.value,
        type: type,
        rentingCost: cost.value,
        isRented: 0,
        under_service: 0,
        licenceNumber: document.getElementById("licensePlateNumber").value
    };
    
    if (type === "Car") {
        JSON.carType = document.getElementById("supplyType").value;
        JSON.mileage = document.getElementById("supplyMileage").value;
        JSON.carType = document.getElementById("supplyType").value;
        JSON.passengerCapacity = document.getElementById("supplyCapacity").value;

    }
    
    return JSON;
}

const getUser = () => {
  return {
        name: username.value,
        surname: surname.value,
        address: address.value,
        birthdate: dob.value,
        drivers_licence: lnumber.value,
        creditcard: creditcard.value
      };
};

const sendSupplyRequst = () => {
    const user = getSupply();
    var xhr = new XMLHttpRequest();
    var queryParams;

    console.log(user);

    xhr.onload = function () {
      if (xhr.readyState === 4) {
          if (xhr.status === 200) {
              const responseData = JSON.parse(xhr.responseText);
              console.log(responseData.str);
              document.getElementById('msgR').innerText = responseData.str;
              document.getElementById('msgR').style.color = responseData.color;
              document.getElementById('RentalButton').style.borderColor = responseData.color;
          } else {
              console.log("not good.");
              reject("Error occurred during the request.");
          }
      } else {
          console.log("not good.");
          reject("Error occurred during the request.");
      }
  };

  const url = "addRentalVehicle";
  xhr.open("POST", url);
  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  queryParams = `user=${encodeURIComponent(JSON.stringify(user))}`;
  xhr.send(queryParams);
    
}

const sendAddRequest = (user) => {
  var xhr = new XMLHttpRequest();
  var queryParams;

  xhr.onload = function () {
      if (xhr.readyState === 4) {
          if (xhr.status === 200) {
              const responseData = JSON.parse(xhr.responseText);
              console.log(responseData.str);
              msg.innerText = responseData.str;
              msg.style.color = responseData.color;
              document.getElementById('addButton').style.borderColor = responseData.color;
          } else {
              console.log("not good.");
              reject("Error occurred during the request.");
          }
      } else {
          console.log("not good.");
          reject("Error occurred during the request.");
      }
  };

  const url = "addUser";
  xhr.open("POST", url);
  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  queryParams = `user=${encodeURIComponent(JSON.stringify(user))}`;
  xhr.send(queryParams);
}

const addBooking = () =>{
   sendAJAXrequest('book');
};

const getBooking = () => {
    return{
        customer_id: customer_id.value,
        driver_id: driver_id.value,
        start_date: startingdate.value,
        end_date: endingdate.value,
        licenceNumber: licenceNumber.value
    };
};

const getText = (str) => {
    switch (str) {
        case true:
            return {
                carId: document.getElementById('getCarIdacc').value,
                text: document.getElementById('accidentSubmission').value,
                flag: 1
            }
            break;
            
        case false:
            return {
                carId: document.getElementById('getCarIdmal').value,
                text: document.getElementById('malfunctionSubmission').value,
                flag: 0
            }
            break;
    }
}

const sendIssueRequest = (flag) => {
    var xhr = new XMLHttpRequest();
    var queryParams;
    
    const issue = getText(flag === 'accident');
    
    xhr.onload = function () {
      if (xhr.readyState === 4) {
          if (xhr.status === 200) {
              const responseData = JSON.parse(xhr.responseText);
              switch (flag) {
                    case "accident":
                        document.getElementById("accidentButton").style.borderColor = responseData.color;
                        document.getElementById("accidentMsg").style.color = responseData.color;
                        document.getElementById("accidentMsg").innerText = responseData.str;
                        break;
                      
                    case "malfunction":
                        document.getElementById("malfButton").style.borderColor = responseData.color;
                        document.getElementById("malfMsg").style.color = responseData.color;
                        document.getElementById("malfMsg").innerText = responseData.str;
                        break;
              }
          } else {
              console.log("not good.");
              reject("Error occurred during the request.");
          }
      } else {
          console.log("not good.");
          reject("Error occurred during the request.");
        }
    };

    const url = "issue";
    xhr.open("POST", url);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    queryParams = `user=${encodeURIComponent(JSON.stringify(issue))}`;
    xhr.send(queryParams);
};


const sendBookRequest = (book) =>{
      var xhr = new XMLHttpRequest();
      var queryParams;
      
  xhr.onload = function () {
      if (xhr.readyState === 4) {
          if (xhr.status === 200) {
              const responseData = JSON.parse(xhr.responseText);
              document.getElementById('bookMsg').innerText = responseData.str;
              document.getElementById('bookMsg').style.color = responseData.color;
              document.getElementById('bookButton').style.borderColor = responseData.color;
              
              document.getElementById('startingDate').style.borderColor = responseData.dcolor;
              document.getElementById('endingDate').style.borderColor = responseData.dcolor;
              
              document.getElementById('vehicleMsgId').innerText = responseData.vehicle;
              document.getElementById('arithmospinakidwn').style.borderColor = responseData.vcolor;
              
              document.getElementById('Booking_CustomerID_small').innerText = responseData.customer;
              document.getElementById('Booking_CustomerID').style.borderColor = responseData.ccolor;
              
              if (responseData.ccolor != "red") {
                  document.getElementById('cardMsg').innerText = responseData.card;
                    document.getElementById('cardMsg').style.borderColor = responseData.cardcolor;
              }
              
              if (!document.getElementById("Booking_DriverID").value) {
                  document.getElementById("Booking_DriverID").value = book.customer_id;
              }
              
              document.getElementById("Booking_DriverID").style.borderColor = responseData.lcolor;
                  document.getElementById("licenceIdMsg").innerText = responseData.licence;
          } else {
              console.log("not good.");
              reject("Error occurred during the request.");
          }
      } else {
          console.log("not good.");
          reject("Error occurred during the request.");
      }
  };

  const url = "book";
  xhr.open("POST", url);
  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  queryParams = `book=${encodeURIComponent(JSON.stringify(book))}`;
  xhr.send(queryParams);
};

const fixedQuery = (num) => {
    var xhr = new XMLHttpRequest();
    var queryParams;
    
    const format = {
        text: "",
    }
    switch (num) {
        case 0:
            format.text = "SELECT IsRented, type FROM Vehicles";
            break;
        case 1:
            format.text = "SELECT color, brand FROM Vehicles WHERE licenceNumber IN (SELECT licenceNumber FROM bookings)";
            break;
        case 2:
            format.text = "SELECT DATEDIFF(end_date,start_date) FROM bookings HAVING MAX(DATEDIFF(end_date,start_date));";
            break;
        case 3:
            format.text = `
                WITH RankedVehicles AS (
                    SELECT
                        v.type,
                        v.model,
                        COUNT(*) AS rental_count,
                        ROW_NUMBER() OVER (PARTITION BY v.type ORDER BY COUNT(*) DESC) AS rank
                    FROM
                        Vehicles v
                        JOIN Bookings b ON v.licenceNumber = b.licenceNumber
                    GROUP BY
                        v.type, v.model
                )
                SELECT
                    type,
                    model,
                    rental_count
                FROM
                    RankedVehicles
                WHERE
                    rank = 1;
                `;
            break;
            
        case 4:
            format.text = `SELECT 
                v.type,
                v.brand,
                v.licenceNumber,
                SUM(DATEDIFF(b.end_date, b.start_date) * v.rentingCost) as total_revenue
              FROM 
                Bookings b
              JOIN 
                Vehicles v ON b.licenceNumber = v.licenceNumber
              GROUP BY 
                v.type, 
                v.brand, 
                v.licenceNumber
              ORDER BY 
                total_revenue DESC;`;
            break;
    }
    
    xhr.onload = function () {
      if (xhr.readyState === 4) {
          if (xhr.status === 200) {
                const responseData = JSON.parse(xhr.responseText);
                console.log(responseData.str);
                
                var data = responseData.str;
                var dynamicTableContainer = document.getElementById('d');
                dynamicTableContainer.innerHTML = '';
                var table = document.createElement('table');
                
                var thead = document.createElement('thead');
                var headerRow = document.createElement('tr');

                var columns = data.split('\n')[0].split('\t');
                for (var i = 0; i < columns.length; i++) {
                    var th = document.createElement('th');
                    th.textContent = columns[i];
                    headerRow.appendChild(th);
                }

                thead.appendChild(headerRow);
                table.appendChild(thead);

                var rows = data.split('\n');

                var tbody = document.createElement('tbody');
                for (var i = 1; i < rows.length; i++) {
                    var row = document.createElement('tr');
                    var columns = rows[i].split('\t');
                    for (var j = 0; j < columns.length; j++) {
                        var cell = document.createElement('td');
                        cell.textContent = columns[j];
                        row.appendChild(cell);
                    }
                    tbody.appendChild(row);
                }
                table.appendChild(tbody);

                dynamicTableContainer.appendChild(table);
          } else {
              console.log("not good.");
              reject("Error occurred during the request.");
          }
      } else {
          console.log("not good.");
          reject("Error occurred during the request.");
        }
    };

    const url = "printQuery";
    xhr.open("POST", url);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    queryParams = `user=${encodeURIComponent(JSON.stringify(format))}`;
    xhr.send(queryParams);
}

const submitQuery = () => {
    var xhr = new XMLHttpRequest();
    var queryParams;
    
    const format = {
        text: document.getElementById('getQuery').value,
    }
    
    xhr.onload = function () {
      if (xhr.readyState === 4) {
          if (xhr.status === 200) {
                const responseData = JSON.parse(xhr.responseText);
                console.log(responseData.str);

                var data = responseData.str;
                var dynamicTableContainer = document.getElementById('d');
                dynamicTableContainer.innerHTML = '';
                var table = document.createElement('table');

                var thead = document.createElement('thead');
                var headerRow = document.createElement('tr');

                var columns = data.split('\n')[0].split('\t');
                for (var i = 0; i < columns.length; i++) {
                    var th = document.createElement('th');
                    th.textContent = columns[i];
                    headerRow.appendChild(th);
                }

                thead.appendChild(headerRow);
                table.appendChild(thead);

                var rows = data.split('\n');

                var tbody = document.createElement('tbody');
                for (var i = 1; i < rows.length; i++) {
                    var row = document.createElement('tr');
                    var columns = rows[i].split('\t');
                    for (var j = 0; j < columns.length; j++) {
                        var cell = document.createElement('td');
                        cell.textContent = columns[j];
                        row.appendChild(cell);
                    }
                    tbody.appendChild(row);
                }
                table.appendChild(tbody);

                dynamicTableContainer.appendChild(table);
          } else {
              console.log("not good.");
              reject("Error occurred during the request.");
          }
      } else {
          console.log("not good.");
          reject("Error occurred during the request.");
        }
    };

    const url = "printQuery";
    xhr.open("POST", url);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    queryParams = `user=${encodeURIComponent(JSON.stringify(format))}`;
    xhr.send(queryParams);
}

const addReturn = () => {
    var xhr = new XMLHttpRequest();
    var queryParams;
    
    const format = {
        vehicle_id: document.getElementById('getVehicleIdreturn').value,
        date: document.getElementById('returnDate').value
    }
    
    if (format.date === "") {
        document.getElementById('returnDate').style.borderColor = "red";
        return;
    }
    
    if (format.vehicle_id === "") {
        document.getElementById('getVehicleIdreturn').style.borderColor = "red";
        return;
    }
    
    xhr.onload = function () {
      if (xhr.readyState === 4) {
          if (xhr.status === 200) {
              const responseData = JSON.parse(xhr.responseText);
              console.log(responseData.str);
              document.getElementById('returnDate').style.borderColor = "green";
              document.getElementById('getVehicleIdreturn').style.borderColor = "green";
              document.getElementById('submitReturnButton').style.borderColor = responseData.color;
              document.getElementById('returnMsg').innerText = responseData.str;
              document.getElementById('returnMsg').style.color = responseData.color;
          } else {
              console.log("not good.");
              reject("Error occurred during the request.");
          }
      } else {
          console.log("not good.");
          reject("Error occurred during the request.");
        }
    };

    const url = "returnVehicle";
    xhr.open("POST", url);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    queryParams = `user=${encodeURIComponent(JSON.stringify(format))}`;
    xhr.send(queryParams);
};


const addCustomerOnSubmit = () => {
    if (!validateElemement(username) ||
        !validateElemement(surname)  ||    
        !validateElemement(address)  ||
        !validateElemement(dob))
    {
      return;
    } else {
      sendAJAXrequest('add');
    }
};

const sendAJAXrequest = (op) => {
  switch (op) {
    case 'add':
      sendAddRequest(getUser());
      break;
    case 'book':
      sendBookRequest(getBooking());  
  }
    
};

const validateElemement = (element) => {
  
  if (element.value === "") {
    onError(element, "field cannot be empty");
    return false;
  } 

  onSuccess(element);
  return true;
};

const onSuccess = (input) => {
  const parent = input.parentElement;
  const border = parent.querySelector("input");
  const msgElement = parent.querySelector("small");
  msgElement.style.visibility = "hidden";
  msgElement.innerText = "";
  border.style.borderColor = "green";
};

const onError = (input, str) => {
  const parent = input.parentElement;
  const border = parent.querySelector("input");
  const msgElement = parent.querySelector("small");
  msgElement.style.visibility = "visible";
  msgElement.innerText = str;
  msgElement.style.color = "red";
  border.style.borderColor = "red";
};
