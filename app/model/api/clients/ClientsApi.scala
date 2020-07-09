package model.api.clients

import model.dataModels.Client
import model.databases.ClientsDB
import model.tools.DateTimeNow
import play.api.db.DBApi
import play.api.libs.ws.WSClient

class ClientsApi(dbApi: DBApi, ws: WSClient) {

  private val clientsDb = new ClientsDB(dbApi)

  object ClientStatuses {
    val New = "New"
    val FollowedUp = "Followed Up"
    val ConsultationScheduled = "Consultation Scheduled"
    val ProposalSent = "Proposal Sent"
    val ProposalAccepted = "Proposal Accepted"
    val ContractSent = "Contract Sent"
    val ContractAccepted = "Contract Accepted"
    val Booked = "Booked"
    val Lost = "Lost"
  }

  import ClientStatuses._

  def statusTypes(): Seq[String] =
    Seq(New, FollowedUp, ConsultationScheduled, ProposalSent, ProposalAccepted, ContractSent, ContractAccepted, Lost)

  def addNewClient(newClientMessage: NewClientMessage): Either[String, Client] = {

    val newClientId = clientsDb.addNewClient(
      Client(name = Some(newClientMessage.customerName),
        event_type = Some(newClientMessage.eventType),
        phone_number = newClientMessage.phoneNumber.toString,
        email = Some(newClientMessage.emailAddress),
        notes = Some(""),
        budget = Some(newClientMessage.budget),
        status = Some(newClientMessage.status),
        business_id = newClientMessage.businessId,
        modified_date = DateTimeNow.getCurrent, created_date = Some(DateTimeNow.getCurrent))
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


  def updateClientsBasicInfo(updateClientMessage: UpdatedClientMessage): Either[String, Client] = {
    val updatedRows = clientsDb.updateBasicClientInfo(
      Client(id = Some(updateClientMessage.clientId),
             name = Some(updateClientMessage.customerName),
             phone_number = updateClientMessage.phoneNumber.toString,
             email = Some(updateClientMessage.emailAddress),
             budget = Some(updateClientMessage.budget),
             status = Some(updateClientMessage.status),
             business_id = updateClientMessage.businessId,
             modified_date = DateTimeNow.getCurrent)
    )

    if(updatedRows == 1) {
      val updatedClient = clientsDb.byId(updateClientMessage.clientId)
      Right(updatedClient.get)
    } else
      Left("Failed during database update or reading the update client data back from database")
  }

  def updateClientsNotes(clientId: Int, businessId: Int, newNote: String): Either[String, Client] = {
    val rowsUpdated = clientsDb.updateClientNotes(clientId, businessId, newNote, DateTimeNow.getCurrent)
    if(rowsUpdated == 1) {
      Right(clientsDb.byId(clientId).get)
    } else {
      Left("Failed during database update")
    }
  }


  def allClientsByBusiness(businessId: Int): Seq[Client] =
    clientsDb.list().filter(_.business_id == businessId)


  def deleteClientById(clientId: Int, businessId: Int): Seq[Client] = {
    val rowsDeleted = clientsDb.deleteByClientIdAndBusinessId(clientId, businessId)
    clientsDb.list()
  }

}
