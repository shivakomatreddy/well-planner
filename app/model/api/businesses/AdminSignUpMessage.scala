package model.api.businesses

case class AdminSignUpMessage(email: String, businessName: String, phoneNumber: String, password: String, auth0Id: String)
