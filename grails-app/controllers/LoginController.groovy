class LoginController {

    def index = {println "index"; redirect(action: 'form')}

    def login = {LoginCommand cmd ->

        log.debug "Attempting login"
        if (cmd.hasErrors()) {
            println "cmd.hasErrors"
            redirect(action: 'form')
        } else {

            println "cmd else"
            User user = User.findByUseridAndPassword(cmd.userid, cmd.password)
            if (user) {
                session.user = user
             } else {
                session.user = null
                flash.loginError = "Invalid username or password. Please try again"
            }

            redirect(uri: "/")

        }

    }
    

    def logout = {
        if (session.user) {
            session.user = null
        }

        redirect(uri: "/")  // to the blog they logged out from
    }

    def form = {
        // placeholder for login page
    }
}