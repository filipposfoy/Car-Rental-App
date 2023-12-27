const username = document.getElementById('customerName');
const surname = document.getElementById('customerSurname');
const address = document.getElementById('customerAddress');
const dob = document.getElementById('customerDOB');
const lnumber = document.getElementById('licenseNumber');
const creditcard = document.getElementById('creditCard');

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

const getUser = () => {
  return {
        name: username.value,
        surname: surname.value,
        address: address.value,
        birthdate: dob.value,
        licenseNumber: lnumber.value,
        creditCard: creditcard.value
      };
}

const sendAddRequest = (user) => {
  var xhr = new XMLHttpRequest();
  var queryParams;

  xhr.onload = function () {
      if (xhr.readyState === 4) {
          if (xhr.status === 200) {
              const responseData = JSON.parse(xhr.responseText);
              console.log(responseData.str);
              // petMsg.innerText = responseData.str;
              // petMsg.style.color = "green";
              // petButton.style.borderColor = "green";
              // petMsg.style.display = "block";
//                    onSuccess(petButton, responseData.str);
          } else {
              console.log("not good");
              reject("Error occurred during the request.");
          }
      } else {
          console.log("not good");
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
} 

const validateElemement = (element) => {
  
  if (element.value === "") {
    onError(element, "field cannot be empty");
    return false;
  } 

  onSuccess(element);
  return true;
}

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
