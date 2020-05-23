package model.api.businesses

import model.dataModels.{Business, User}

case class NewBusinessSignupMessage(business: Business, user: User)

case class AdminSignUpMessage(userAuth0Id: String, email: String, businessName: String, phoneNumber: Int)
