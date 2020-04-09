package model.api.businesses

import model.dataModels.{Business, User}

case class NewBusinessSignupMessage(business: Business, user: User)
