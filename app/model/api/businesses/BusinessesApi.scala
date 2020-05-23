package model.api.businesses

import model.api.users.{UsersApi, UsersFacade}
import model.dataModels.{Business, User}
import model.databases.BusinessesDb
import play.api.db.DBApi
import org.joda.time.DateTime
import play.api.libs.ws.WSClient

class BusinessesApi(dbApi: DBApi, ws: WSClient) {

  val businessesDb: BusinessesDb =  new BusinessesDb(dbApi)
  val usersApi: UsersApi = new UsersFacade(dbApi)

  def signUpBusiness(newBusiness: AdminSignUpMessage): Either[String, (Business, User)] = {

      def registerBusinessFirstAndThenUser  = {

        val business = Business(name = newBusiness.businessName, city = "test", state = "CA", country = "USA",
                 modifiedDate = DateTime.now, createdDate = DateTime.now)

        val transactionResult = businessesDb.addNewBusiness(business)

        if(transactionResult.nonEmpty) {
          val user = User(username = newBusiness.email, userAuth0Id = newBusiness.userAuth0Id, password = "", email = newBusiness.email, businessId = transactionResult.get.toInt)
          val userRegisterTransaction = usersApi.register(user)
          if(userRegisterTransaction.nonEmpty)
            Right(business.copy(id = Some(transactionResult.get.toInt)), userRegisterTransaction.get)
          else Left("User registration failed")
        }
        else Left("Business Creation failed")
      }

      businessesDb.existsByName(newBusiness.businessName) match {
        case false => registerBusinessFirstAndThenUser
        case true => Left("business name already exists")
      }
  }

  def businessExists(businessName: String): Boolean =
    businessesDb.existsByName(businessName)

}