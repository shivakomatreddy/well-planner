package model

case class User(id: Int, auth0Id: String, username: String, password: String, email: String)

case class Company(id: Int, name: String, createDate: String, userId: Int)

case class Customer(id: Int, name: String, email: String, notes: String, phase: String, companyId: Int)

case class Project(id: Option[Int] = None, name: String, eventType: String, companyId: Int, customerId: Int)

case class Task(id: Option[Int] = None, isCategory: Boolean, name: String, notes: String, isCompleted: Boolean,
                taskDateTime: String, createdDate: String, parentId: Int, companyId: Int, projectId: Int)

object Database {
  var users: Seq[User] = Seq.empty[User]

  var tasks: Seq[Task] = Seq.empty[Task]
}