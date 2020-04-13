app.controller('signUpController', function($http, $window) {

    console.log("Attached JS: signUpController");

    var pageCtrl = this;
    pageCtrl.businessName = "";
    pageCtrl.email = "";
    pageCtrl.step = 1;


    pageCtrl.setStep = function(step){
        pageCtrl.step = step;
    };

    pageCtrl.sectionName = "";

    console.log("sectionName ->" + pageCtrl.sectionName);

    pageCtrl.basicInfo = function () {
        reDirectToRegisterPage();
    };

    function reDirectToRegisterPage() {
        $window.location.href =
            "http://" + $window.location.host + "/register-business-user?businessName=" + pageCtrl.firstname + "&email=" + pageCtrl.email
    }

    pageCtrl.nextSignUpSection = function () {
        console.log("button next is clicked");
        onNextSection();
    };

    function onNextSection() {
        console.log(pageCtrl.sectionName)
    }
});