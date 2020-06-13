package model.databases

import model.dataModels.Client

trait ClientDBApi {

  def addNewClient(client: Client): Option[Long]

  def byId(clientId: Long): Option[Client]

}