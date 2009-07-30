class ExpenseEventController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ expenseEventList: ExpenseEvent.list( params ) ]
    }

    def show = {
        def expenseEvent = ExpenseEvent.get( params.id )

        if(!expenseEvent) {
            flash.message = "ExpenseEvent not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ expenseEvent : expenseEvent ] }
    }

    def delete = {
        def expenseEvent = ExpenseEvent.get( params.id )
        if(expenseEvent) {
            expenseEvent.delete()
            flash.message = "ExpenseEvent ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "ExpenseEvent not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def expenseEvent = ExpenseEvent.get( params.id )

        if(!expenseEvent) {
            flash.message = "ExpenseEvent not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ expenseEvent : expenseEvent ]
        }
    }

    def update = {
        def expenseEvent = ExpenseEvent.get( params.id )
        if(expenseEvent) {
            expenseEvent.properties = params
            if(!expenseEvent.hasErrors() && expenseEvent.save()) {
                flash.message = "ExpenseEvent ${params.id} updated"
                redirect(action:show,id:expenseEvent.id)
            }
            else {
                render(view:'edit',model:[expenseEvent:expenseEvent])
            }
        }
        else {
            flash.message = "ExpenseEvent not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def expenseEvent = new ExpenseEvent()
        expenseEvent.properties = params
        return ['expenseEvent':expenseEvent]
    }

    def save = {
        def expenseEvent = new ExpenseEvent(params)
        if(!expenseEvent.hasErrors() && expenseEvent.save()) {
            flash.message = "ExpenseEvent ${expenseEvent.id} created"
            redirect(action:show,id:expenseEvent.id)
        }
        else {
            render(view:'create',model:[expenseEvent:expenseEvent])
        }
    }
}
