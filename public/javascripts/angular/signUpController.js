app.controller('signUpController', function($http, $window) {

    console.log("Attached JS: signUpController");

    var pageController = this;
    pageController.businessName = undefined;
    pageController.email = undefined;

    pageController.basicInfo = function() {
        console.log("Inside basic Info");
        pageController.validateEmail();
    };

    pageController.validateEmail = function (email) {
        $http({
            method: 'GET',
            url: '/users/email='+ email
        }).then(function successCallback(response) {
            if(response.status === 200) {
                pageController.emailExists = response.data.data;
                console.log("email  exist " + pageController.emailExists);
                if(pageController.emailExists) {
                    $window.location.href = "http://" + $window.location.host + "/"
                } else {
                    $window.location.href =
                        "http://" + $window.location.host + "/register-business-user?businessName=" + pageController.businessName + "&email=" + pageController.email
                }
            }
        }, function errorCallback(response) {
            console.log("Request failed!!");
        });
    };


});