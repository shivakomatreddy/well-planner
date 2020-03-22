package model

import scala.concurrent.Future

object ApplicationAPI {
  def allBusinesses(businessesRepo: BusinessRepo): Future[Seq[Business]] = businessesRepo.list()

  def createBusiness(business: Business, businessesRepo: BusinessRepo): Future[Business] = {
    businessesRepo.create(business.name, business.city, business.state, business.country)
  }
}
