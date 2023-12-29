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

const toggleFields = () => {
    const type = document.querySelector('input[name="type"]:checked').value;
    document.getElementById('rvar1').style.display = (type === "Car") ? "table-row" : "none";
    document.getElementById('rvar2').style.display = (type === "Car") ? "table-row" : "none";
    document.getElementById('rvar3').style.display = (type === "Car") ? "table-row" : "none";
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
    };
    
    if (type === "Car") {
        JSON.carType = document.getElementById("supplyType").value;
        JSON.mileage = document.getElementById("supplyMileage").value;
        JSON.carType = document.getElementById("supplyType").value;
        JSON.passengerCapacity = document.getElementById("supplyCapacity").value;
        JSON.licenseNumber = document.getElementById("licensePlateNumber").value;
    }
    
    return JSON;
}

const getUser = () => {
  return {
        name: username.value,
        surname: surname.value,
        address: address.value,
        birthdate: dob.value,
        licenseNumber: lnumber.value,
        creditCardNumber: creditcard.value
      };
};

const sendSupplyRequst = () => {
    const user = getSupply();
    var xhr = new XMLHttpRequest();
    var queryParams;

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



const addCustomerOnSubmit = () => {
    if (!validateElemement(username) ||
        !validateElemement(surname)  ||    
        !validateElemement(address)  ||
        !validateElemement(dob)      ||
        !validateElemement(lnumber)  ||
        !validateElemement(creditcard))
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
