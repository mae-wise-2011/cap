package cap.grails

class User {
  
  String firstName;
  String lastName;

  static constraints = {
    firstName( blank: false )
    lastName( blank: false )
  }
}
