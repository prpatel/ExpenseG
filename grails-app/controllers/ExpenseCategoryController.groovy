class ExpenseCategoryController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ expenseCategoryList: ExpenseCategory.list( params ) ]
    }

    def show = {
        def expenseCategory = ExpenseCategory.get( params.id )

        if(!expenseCategory) {
            flash.message = "ExpenseCategory not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ expenseCategory : expenseCategory ] }
    }

    def delete = {
        def expenseCategory = ExpenseCategory.get( params.id )
        if(expenseCategory) {
            expenseCategory.delete()
            flash.message = "ExpenseCategory ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "ExpenseCategory not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def expenseCategory = ExpenseCategory.get( params.id )

        if(!expenseCategory) {
            flash.message = "ExpenseCategory not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ expenseCategory : expenseCategory ]
        }
    }

    def update = {
        def expenseCategory = ExpenseCategory.get( params.id )
        if(expenseCategory) {
            expenseCategory.properties = params
            if(!expenseCategory.hasErrors() && expenseCategory.save()) {
                flash.message = "ExpenseCategory ${params.id} updated"
                redirect(action:show,id:expenseCategory.id)
            }
            else {
                render(view:'edit',model:[expenseCategory:expenseCategory])
            }
        }
        else {
            flash.message = "ExpenseCategory not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def expenseCategory = new ExpenseCategory()
        expenseCategory.properties = params
        return ['expenseCategory':expenseCategory]
    }

    def save = {
        def expenseCategory = new ExpenseCategory(params)
        if(!expenseCategory.hasErrors() && expenseCategory.save()) {
            flash.message = "ExpenseCategory ${expenseCategory.id} created"
            redirect(action:show,id:expenseCategory.id)
        }
        else {
            render(view:'create',model:[expenseCategory:expenseCategory])
        }
    }
}
