app.controller('registerBusinessController', function($http, $window) {

    console.log("Attached JS: registerBusinessController");

    var pageCtrl = this;
    pageCtrl.businessName = null;
    pageCtrl.phoneNumber = null;
    pageCtrl.websiteLink = null;

    pageCtrl.username = null;
    pageCtrl.password = null;
    pageCtrl.confirmPassword = null;
    pageCtrl.email = null;

    pageCtrl.nextButtonEnabled = false;
    pageCtrl.validateButtonEnabled = true;

    pageCtrl.businessNameExists = true;
    pageCtrl.isMatch = false;
    pageCtrl.usernameAndEmailValidated = true;
    pageCtrl.userNameExists = true;
    pageCtrl.emailExists = true;

    pageCtrl.errorMsg = "";
    pageCtrl.errorMsgUserName = "";
    pageCtrl.errorMsgEmail = "";

    pageCtrl.step = 1;

    pageCtrl.setStep = function(step){
        pageCtrl.step = step;
    };

    pageCtrl.nextSignUpSection = function () {
        if(pageCtrl.step === 1 && pageCtrl.businessName !== null && pageCtrl.phoneNumber != null) {
            pageCtrl.resetValidateButton();
            pageCtrl.step = pageCtrl.step + 1;
        } else if(pageCtrl.step === 2 && pageCtrl.username !== null && pageCtrl.password != null && pageCtrl.email != null && pageCtrl.confirmPassword != null) {
            pageCtrl.resetValidateButton();
            pageCtrl.step = pageCtrl.step + 1;
        } else if(pageCtrl.step === 3 && pageCtrl.city !== null && pageCtrl.state != null && pageCtrl.zipCode != null && pageCtrl.country != null) {
            pageCtrl.validateButtonEnabled = false;
            // do nothing yet
        }
    };

    pageCtrl.previousSection = function () {
        pageCtrl.resetValidateButton();
        pageCtrl.step = pageCtrl.step - 1;
    };

    pageCtrl.registerBusiness = function () {

    };

    pageCtrl.passwordMatch = function () {
        pageCtrl.isMatch = angular.equals(pageCtrl.password, pageCtrl.confirmPassword);
    };

    pageCtrl.resetValidateButton = function () {
        pageCtrl.validateButtonEnabled = true;
        pageCtrl.nextButtonEnabled = false;
    };

    pageCtrl.validateForm = function () {
        console.log("validate button pressed");

        if(pageCtrl.step === 1) {

            if(pageCtrl.businessName === undefined || pageCtrl.businessName === null) {
                pageCtrl.errorMsg = "required field"
            } else {
                console.log("validating business name usage");
                pageCtrl.validateBusinessName();
            }

        } else if (pageCtrl.step === 2) {

            console.log("step 2");
            console.log(pageCtrl.username);
            console.log(pageCtrl.email);

            if(pageCtrl.username === null) pageCtrl.errorMsgUserName = "required field";
            else pageCtrl.errorMsgUserName = "";

            if(pageCtrl.email === null) pageCtrl.errorMsgEmail = "required field";
            else pageCtrl.errorMsgEmail = "";

            if(pageCtrl.username !== undefined && pageCtrl.username !== null && pageCtrl.email !== undefined && pageCtrl.email !== null) {
                pageCtrl.validationsForUsernameEmailAndPassword();
            }
        }
    };

    pageCtrl.validateBusinessName = function () {
        $http({
            method: 'GET',
            url: '/businesses/exists/businessName=' + pageCtrl.businessName
        }).then(function successCallback(response) {
            if(response.status === 200) {
                pageCtrl.businessNameExists = response.data.data;
                console.log("business name exist " + pageCtrl.businessNameExists);

                if(pageCtrl.businessNameExists) {
                    pageCtrl.errorMsg = "Business Name is used!";
                    pageCtrl.validateButtonEnabled = true;
                    pageCtrl.nextButtonEnabled = false;
                } else {
                    pageCtrl.validateButtonEnabled = false;
                    pageCtrl.nextButtonEnabled = true;
                    pageCtrl.errorMsg = "";
                }
                return response.data.data;
            }
        }, function errorCallback(response) {
            console.log("Request failed!!")
        });
    };

    pageCtrl.validationsForUsernameEmailAndPassword = function () {
        $http({
            method: 'GET',
            url: '/users/username='+ pageCtrl.username
        }).then(function successCallback(response) {
            if(response.status === 200) {
                pageCtrl.userNameExists = response.data.data;
                console.log("user name exist " + pageCtrl.userNameExists);

                if(pageCtrl.userNameExists) {
                    pageCtrl.validateButtonEnabled = true;
                    pageCtrl.nextButtonEnabled = false;
                    pageCtrl.errorMsgUserName = "username is used!"
                } else {
                    pageCtrl.validateEmail();
                }
            }
        }, function errorCallback(response) {
                console.log("Request failed!!")
        });
    };


    pageCtrl.validateEmail = function () {
        $http({
            method: 'GET',
            url: '/users/email='+ pageCtrl.email
        }).then(function successCallback(response) {
            if(response.status === 200) {
                pageCtrl.emailExists = response.data.data;
                console.log("email  exist " + pageCtrl.userNameExists);

                if(pageCtrl.emailExists) {
                    pageCtrl.validateButtonEnabled = true;
                    pageCtrl.nextButtonEnabled = false;
                    pageCtrl.errorMsgEmail = "email is used!"
                } else if(!pageCtrl.userNameExists && !pageCtrl.emailExists) {
                    pageCtrl.validateButtonEnabled = false;
                    pageCtrl.nextButtonEnabled = true;
                    pageCtrl.errorMsgEmail = "";
                }

            }
        }, function errorCallback(response) {
            console.log("Request failed!!")
        });
    };

    // not being used at the moment
    pageCtrl.validateUsernameAndEmail = function () {
        $http({
            method: 'GET',
            url: '/businesses/users/username='+ pageCtrl.username + '/email=' + pageCtrl.email
        }).then(function successCallback(response) {
            if(response.status === 200) {
                pageCtrl.userNameExists = response.data.data.usernameExists;
                pageCtrl.emailExists = response.data.data.emailExists;

                if(!pageCtrl.userNameExists && !pageCtrl.emailExists) {
                    pageCtrl.validateButtonEnabled = false;
                    pageCtrl.nextButtonEnabled = true;
                    pageCtrl.errorMsgUserName = "";
                    pageCtrl.errorMsgEmail = "";
                }

                if(pageCtrl.userNameExists) {
                    pageCtrl.validateButtonEnabled = true;
                    pageCtrl.nextButtonEnabled = false;
                    pageCtrl.errorMsgUserName = "username is used!"
                }

                if(pageCtrl.emailExists) {
                    pageCtrl.validateButtonEnabled = true;
                    pageCtrl.nextButtonEnabled = false;
                    pageCtrl.errorMsgEmail = "email is used!"
                }

                return response.data.data;
            }
        }, function errorCallback(response) {
            console.log("Request failed!!")
        });
    }


});