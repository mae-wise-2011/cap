package cap.portal

class LoginController {

  def index = {
    log.info("Hello from Login index")
  }
  
  def login = {
    log.info("Hello From login action")
    def user = User.findWhere(username: params['username'], password: params['password'])
    session.user = user
    if( user ) {
      redirect(controller: 'user', action: 'list')
    } else {
      redirect(controller: 'login', action: 'index')
    }
  }
}
