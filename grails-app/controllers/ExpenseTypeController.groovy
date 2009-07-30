class ExpenseTypeController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ expenseTypeList: ExpenseType.list( params ) ]
    }

    def show = {
        def expenseType = ExpenseType.get( params.id )

        if(!expenseType) {
            flash.message = "ExpenseType not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ expenseType : expenseType ] }
    }

    def delete = {
        def expenseType = ExpenseType.get( params.id )
        if(expenseType) {
            expenseType.delete()
            flash.message = "ExpenseType ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "ExpenseType not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def expenseType = ExpenseType.get( params.id )

        if(!expenseType) {
            flash.message = "ExpenseType not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ expenseType : expenseType ]
        }
    }

    def update = {
        def expenseType = ExpenseType.get( params.id )
        if(expenseType) {
            expenseType.properties = params
            if(!expenseType.hasErrors() && expenseType.save()) {
                flash.message = "ExpenseType ${params.id} updated"
                redirect(action:show,id:expenseType.id)
            }
            else {
                render(view:'edit',model:[expenseType:expenseType])
            }
        }
        else {
            flash.message = "ExpenseType not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def expenseType = new ExpenseType()
        expenseType.properties = params
        return ['expenseType':expenseType]
    }

    def save = {
        def expenseType = new ExpenseType(params)
        if(!expenseType.hasErrors() && expenseType.save()) {
            flash.message = "ExpenseType ${expenseType.id} created"
            redirect(action:show,id:expenseType.id)
        }
        else {
            render(view:'create',model:[expenseType:expenseType])
        }
    }
}
