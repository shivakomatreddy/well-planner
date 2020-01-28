package model

case class User(id: Int, auth0Id: String, username: String, password: String, email: String,
                travelNickname: String = "", hometown: String = "", birthDate: Int = 0,
                numberOfCountriesVisited: Int = 0)



object Database {
  var users: Seq[User] = Seq.empty[User]

}



//{ "id": 1, "name": "Toronto October Trip", "description": "Parents visit", "userId": 1 }
//{ "id": 2, "name": "Euro 2019", "description": "My big vacation", "userId": 1 }
//{ "id": 3, "name": "Montreal Trip Christmas", "description": "My Christmas Trip", "userId": 1 }

//{ "id": 1, "name": "Montreal", "tripId": 3}

//{ "id": 4, "name": "Montreal Trip Christmas", "description": "My Christmas Trip", "userId": 1 }