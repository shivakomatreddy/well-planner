app.controller('clientsController', function($http, $window) {
    var clientController = this;
    clientController.clients = [];

    clientController.getAllClients = function(businessId) {
        allClients(businessId);
    };

    clientController.creatNewClient = function () {
        var businessId = $("#newCustomer").data('id');
        console.log(businessId);
        createAClient(businessId)
    };

    function allClients(businessId) {
        $http({
            method: 'GET',
            url: '/businesses/clients/'+ businessId
        }).then(function mySuccess (response) {
            clientController.clients = response.data.data;
        }, function myError (response) {
            console.log(response.statusText)
        });
    }

    function createAClient(businessId) {
        clientController.formData.businessId = businessId;
        console.log(clientController.formData);
        $http({
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            url: '/businesses/clients',
            data: JSON.stringify(clientController.formData),
        }).then(function mySuccess() {
            console.log("successfully created");
            $window.location.href = "http://" + $window.location.host + "/pages/customers"
        }, function myError() {
            console.log("ERROR creating a client");
        })
    }
});