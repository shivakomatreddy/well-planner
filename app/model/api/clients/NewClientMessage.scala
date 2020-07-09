package model.api.clients

case class NewClientMessage(customerName: String, phoneNumber: String, emailAddress: String,
                            eventType: String, budget: Double, notes: Option[String] = None, status: String, businessId: Int)

case class UpdatedClientMessage(clientId: Int, customerName: String, phoneNumber: String, emailAddress: String,
                                budget: Double, notes: String, status: String, businessId: Int)