package model.api.businesses

import model.api.users.{UsersApi, UsersFacade}
import model.dataModels.{Business, User}
import model.databases.BusinessesDb
import model.tools.{DateTimeNow, PasswordProtector}
import org.joda.time.DateTime
import play.api.db.DBApi
import play.api.libs.ws.WSClient

class BusinessesApi(dbApi: DBApi, ws: WSClient) {

  val businessesDb: BusinessesDb =  new BusinessesDb(dbApi)
  val usersApi: UsersApi = new UsersFacade(dbApi)

  def signUpBusiness(newBusiness: AdminSignUpMessage): Either[String, (Business, User)] = {

      println("Business Sign up process for new business -> " + AdminSignUpMessage.toString())

      def registerBusiness: Option[Business] = {
        val business =
          Business(name = newBusiness.businessName, city = "N/A", state = "N/A", phone_number = newBusiness.phoneNumber,
                   country = "N/A", modified_date = DateTimeNow.getCurrent, created_date = DateTimeNow.getCurrent)

        businessesDb
          .addNewBusiness(business)
          .map(id => business.copy(id = Some(id.toInt)))
      }


      def registerUser(newBusinessId: Int): Option[User] = {
        usersApi.register(User(username = newBusiness.email, user_auth_0_id = newBusiness.auth0Id,
                               password = PasswordProtector.md5HashString(newBusiness.password),
                               email = newBusiness.email, business_id = newBusinessId,
                               modified_date = DateTimeNow.getCurrent, created_date = DateTimeNow.getCurrent))
      }

      val result = for {
         businessRegistered <- registerBusiness
         userRegistered <- registerUser(businessRegistered.id.get)
        } yield Right((businessRegistered, userRegistered))

      result.getOrElse(Left("Failed to Register"))
  }

  def businessExists(businessName: String): Boolean = {
    businessesDb.existsByName(businessName)
  }

}