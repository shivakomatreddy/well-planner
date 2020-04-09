package model.databases

import model.dataModels.Business

trait BusinessDbApi {

  def addNewBusiness(business: Business): Option[Long]

  def list(): Seq[Business]

//  def businessKpi(id: Int)
//
//  def updateBusinessInfo(business: Business): Future[Business]
}
