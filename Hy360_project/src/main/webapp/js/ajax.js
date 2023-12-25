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

        default:
            break;
    }
}
