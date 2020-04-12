package model.api.businesses

import model.dataModels.{Business, User}

case class NewBusinessSignupMessage(business: Business, user: User)

case class AdminSignUpMessage(email: String, password: String, businessName: String, phoneNumber: Int)
