package model.dataModels

case class Business(id: Option[Int] = None, name: String, city: String, state: String, phone_number: String,
                    country: String, modified_date: Int, created_date: Int)
