package model.api.businesses.db

import model.api.businesses.Business
import scala.concurrent.Future

trait BusinessDbApi {

  def addNewBusiness(business: Business): Option[Long]

  def list(): Seq[Business]

//  def businessKpi(id: Int)
//
//  def updateBusinessInfo(business: Business): Future[Business]
}
