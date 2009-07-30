class ReportController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ reportList: Report.list( params ) ]
    }

    def show = {
        def report = Report.get( params.id )

        if(!report) {
            flash.message = "Report not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ report : report ] }
    }

    def delete = {
        def report = Report.get( params.id )
        if(report) {
            report.delete()
            flash.message = "Report ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "Report not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def report = Report.get( params.id )

        if(!report) {
            flash.message = "Report not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ report : report ]
        }
    }

    def update = {
        def report = Report.get( params.id )
        if(report) {
            report.properties = params
            if(!report.hasErrors() && report.save()) {
                flash.message = "Report ${params.id} updated"
                redirect(action:show,id:report.id)
            }
            else {
                render(view:'edit',model:[report:report])
            }
        }
        else {
            flash.message = "Report not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def report = new Report()
        report.properties = params
        return ['report':report]
    }

    def save = {
        def report = new Report(params)
        if(!report.hasErrors() && report.save()) {
            flash.message = "Report ${report.id} created"
            redirect(action:show,id:report.id)
        }
        else {
            render(view:'create',model:[report:report])
        }
    }
}
