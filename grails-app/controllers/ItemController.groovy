import java.text.SimpleDateFormat
class ItemController {
    
    
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ itemList: Item.list( params ) ]
    }

    def show = {
        def item = Item.get( params.id )

        if(!item) {
            flash.message = "Item not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ item : item ] }
    }

    def delete = {
        def item = Item.get( params.id )
        if(item) {
            item.delete()
            flash.message = "Item ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "Item not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def item = Item.get( params.id )

        if(!item) {
            flash.message = "Item not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ item : item ]
        }
    }

    def update = {
        def item = Item.get( params.id )
        if(item) {
            item.properties = params
            if(!item.hasErrors() && item.save()) {
                flash.message = "Item ${params.id} updated"
                redirect(action:show,id:item.id)
            }
            else {
                render(view:'edit',model:[item:item])
            }
        }
        else {
            flash.message = "Item not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def item = new Item()
        item.properties = params
        return ['item':item]
    }

    def save = {
        def item = new Item(params)
        if(!item.hasErrors() && item.save()) {
            flash.message = "Item ${item.id} created"
            redirect(action:show,id:item.id)
        }
        else {
            render(view:'create',model:[item:item])
        }
    }

    def itemSearchService
    def search = {

        //println params
        if(!params.max) params.max = 10
        if(!params.offset) params.offset = 0
        if (request.method == 'POST') {

            //if(!params.max) params.max = 10
            render(view:'results',model:[itemList: itemSearchService.executeCriteriaQuery(params), params: params])
        }

    }

    def searchByEventId = {
        render(view:'results',model:[itemList: itemSearchService.executeCriteriaQuery(params), params: params])
    }

    def fastSearchAJAX = {
        def events = ExpenseEvent.findAllByNameLike("%${params.query}%")

        //Create XML response
        render(contentType: "text/xml") {
            results() {
                events.each { event ->
                    result(){
                        name(event.name)
                        //Optional id which will be available in onItemSelect
                        id(event.id)
                    }
                }
            }
        }
    }

def fastSearch = {

    }



    def myitems = {

        if (!session.user) {
            redirect(controller: "login", action: 'form')
        }
        println session.user.id
        render(view:'myitems',model:[itemList: myItemsQuery(params)])
        //        if (request.method == 'POST') {
        //
        //            //if(!params.max) params.max = 10
        //            render(view:'list',model:[itemList: myItemsQuery(params)])
        //        }

    }

    def  myItemsQuery =  {
        Item.createCriteria().list {
            and {
                eq('user.id', session.user.id)
                //             if(params.expenseType.id != "null" && params.expenseType.id) {
                //                 eq('expenseType.id', Long.parseLong(params.expenseType.id))
                //             }
            }
        }
    }
}