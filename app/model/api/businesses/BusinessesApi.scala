package model.api.businesses

import model.api.users.{UsersApi, UsersFacade}
import model.dataModels.{Business, User}
import model.databases.BusinessesDb
import play.api.db.DBApi

class BusinessesApi(dbApi: DBApi) {

  val businessesDb: BusinessesDb =  new BusinessesDb(dbApi)
  val usersApi: UsersApi = new UsersFacade(dbApi)

  def signUpBusiness(newBusiness: NewBusinessSignupMessage): Either[String, (Business, User)] = {

      def registerBusinessFirstAndThenUser  = {
        val transactionResult = businessesDb.addNewBusiness(newBusiness.business)
        if(transactionResult.nonEmpty) {
          val userRegisterTransaction = usersApi.register(newBusiness.user)
          if(userRegisterTransaction.nonEmpty)
            Right(newBusiness.business.copy(id = transactionResult.get.toInt), userRegisterTransaction.get)
          else Left("User registration failed")
        }
        else Left("Business Creation failed")
      }

      businessesDb.existsByName(newBusiness.business.name) match {
        case false => registerBusinessFirstAndThenUser
        case true => Left("business name already exists")
      }
  }

  def businessExists(businessName: String): Boolean =
    businessesDb.existsByName(businessName)

}