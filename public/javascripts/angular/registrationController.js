app.controller('registerBusinessController', function($http, $window) {

    var pageController = this;

    pageController.businessName = undefined;
    pageController.phoneNumber = undefined;

    pageController.password = undefined;
    pageController.email = undefined;


    $(document).ready(function() {
        businessRegistrationWizard.initWizard(); // Initialize Business User Registration Wizard
        setTimeout(function() { $('.card.card-wizard').addClass('active'); }, 600);
    });

    pageController.onFinish = function () {
        var $valid = $('.card-wizard form').valid();
        if (!$valid) {
            return false;
        } else {
            pageController.completeRegistration();
        }
    };

    function getUrlVars() {
        var vars = {};
        var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
            vars[key] = value;
        });
        return vars;
    }


    pageController.initializeFormValidators = function () {
        pageController.businessName = decodeURI(getUrlVars()['businessName']);
        pageController.email = decodeURI(getUrlVars()['email']);

        $.validator.addMethod("alphanumericWithSpaces", function (value, element) {
            return this.optional(element) || wellPlannerValidators.isValidAlphanumericWithSpaces(value);
        }, "Alphanumeric format with spaces only, other special characters not allowed!");

        $.validator.addMethod("digitsOnly", function (value, element) {
            return this.optional(element) || wellPlannerValidators.isNotDigitsOnly(value);
        }, "Found only digits, we need alphanumeric format please!");

        $.validator.addMethod("ourPasswordValid", function (password, element) {
            return this.optional(element) || wellPlannerValidators.isOurPasswordOnly(password);
        }, "At least 1 upper, 1 lower, 1 digit, 1 special char, and minimum 8 characters");

        $.validator.addMethod("emailAddressAvailable", function (email, element) {
            return this.optional(element) || !pageController.validateEmail(email);
        }, "Email address is taken!!");
    };

    pageController.completeRegistration = function () {
        var data = {};
        data.businessName = pageController.businessName;
        data.password = pageController.password;
        data.email = pageController.email;
        data.phoneNumber = pageController.phoneNumber;

        $http({
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            url: '/businesses/signUp/',
            data: JSON.stringify(data),
        }).then(function mySuccess() {
            alert("successfully created");
        }, function myError() {
            alert("ERROR registering");
        })
    };


    pageController.validateEmail = function (email) {
        $http({
            method: 'GET',
            url: '/users/email='+ email
        }).then(function successCallback(response) {
            if(response.status === 200) {
                pageController.emailExists = response.data.data;
                console.log("email  exist " + pageController.emailExists);
                return pageController.emailExists;
            }
        }, function errorCallback(response) {
            console.log("Request failed!!");
            return false;
        });
    };

});