package model.api.clients

case class NewClientMessage(customerName: String, phoneNumber: String, emailAddress: String,
                            eventType: String, budget: Double, notes: String, eventDate: String, businessId: Int)