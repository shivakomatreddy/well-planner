app.controller('signUpController', function($http, $window) {
    console.log("Attached JS: signUpController");

    var pageCtrl = this;
    pageCtrl.businessName = "";
    pageCtrl.email = "";

    pageCtrl.basicInfo = function () {
        reDirectToRegisterPage();
    };

    function reDirectToRegisterPage() {
        $window.location.href =
            "http://" + $window.location.host + "/register-business-user?businessName=" + pageCtrl.businessName + "&email=" + pageCtrl.email
    }
});
