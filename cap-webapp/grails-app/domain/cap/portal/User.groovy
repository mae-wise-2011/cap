package cap.portal

class User {
  String firstName
  String lastName
  String username
  String password
  
  static constraints = {
    firstName blank: false
    lastName blank: false
    username blank: false
    password blank: false, password : true
  }
}

