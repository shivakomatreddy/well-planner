package model.api.clients

import model.dataModels.Client
import model.databases.ClientsDB
import model.tools.DateTimeNow
import play.api.db.DBApi
import play.api.libs.ws.WSClient

class ClientsApi(dbApi: DBApi, ws: WSClient) {

  private val clientsDb = new ClientsDB(dbApi)

  private val LeadStatus = "LEAD"

  def addNewClient(newClientMessage: NewClientMessage): Either[String, Client] = {

    val newClientId = clientsDb.addNewClient(
      Client(name = Some(newClientMessage.customerName),
        event_type = Some(newClientMessage.eventType),
        email = Some(newClientMessage.emailAddress),
        notes = Some(newClientMessage.notes),
        budget = Some(newClientMessage.budget),
        status = Some(LeadStatus),
        business_id = newClientMessage.businessId,
        modified_date = DateTimeNow.getCurrent, created_date = DateTimeNow.getCurrent)
    )

    val newClient =
      for {
        id <- newClientId
        clientData <- clientsDb.byId(id)
      } yield clientData

    if(newClient.nonEmpty)
      Right(newClient.get)
    else
      Left("Failed during database insertion or reading the newly created data")

  }

}
