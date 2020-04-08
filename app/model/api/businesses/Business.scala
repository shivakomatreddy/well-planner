package model.api.businesses

case class Business(name: String, city: String, state: String,
                    country: String, modifiedDate: String,
                    createdDate: String)



//case class Business(id: Int, name: Option[String] = None, city: Option[String] = None, state: Option[String] = None,
//                    country: Option[String] = None, modifiedDate: Option[java.sql.Timestamp] = None,
//                    createdDate: Option[java.sql.Timestamp] = None)
