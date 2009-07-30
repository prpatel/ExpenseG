class SearchTests extends GroovyTestCase {
    
    ItemController controller
    def itemSearchService
    Item item1
    Item item2

    void setUp() {
        controller = new ItemController()
        controller.itemSearchService = itemSearchService
    }

    void tearDown() {
                
    }

    void testSearchByType() {
        createTestData()
        assertEquals 3,  item1.expenseType.id
        assertEquals 2, Item.list().size

        // WORKS
        // controller.request.parameters = ["expenseType.id":  Long.toString(item1.expenseType.id)]
        // WORKS
        controller.params.expenseType = ["id" : Long.toString(item1.expenseType.id)]
        
        // DOESN'T WORK
        // controller.params.put( ["expenseType.id",  Long.toString(item1.expenseType.id) ])
        // DOESN'T WORK
        // controller.request.parameters << ["expenseType.id":  Long.toString(item1.expenseType.id)]

        controller.request.method = "POST"
        controller.search()

        def items = controller.modelAndView.model.itemList
        assertEquals item1, (Item.findByExpenseType(item1.expenseType))
        assertEquals 1, items.size
        Item returnedItem = items[0]
        assertEquals item1.desc, returnedItem.desc

    }

    void testSearchByEvent() {
        createTestData()
        //assertEquals 1,  item1.event.id

        controller.params.event = ["id" : Long.toString(item1.event.id)]
        controller.request.method = "POST"
        controller.search()

        def items = controller.modelAndView.model.itemList
        assertEquals 1, items.size
        Item returnedItem = items[0]
        assertEquals item1.desc, returnedItem.desc

    }

    void testSearchByEventAndType() {
        createTestData()

        controller.params.event = ["id" : Long.toString(item1.event.id)]
        controller.params.expenseType = ["id" : Long.toString(item1.expenseType.id)]
        controller.request.method = "POST"
        controller.search()

        def items = controller.modelAndView.model.itemList
        assertEquals 1, items.size
        Item returnedItem = items[0]
        assertEquals item1.desc, returnedItem.desc

    }

    void testSearchByEventAndTypeFailue() {
        createTestData()

        controller.params.event = ["id" : Long.toString(item1.event.id)]
        // Use item2's type, it is setup to use the other expenseType'
        controller.params.expenseType = ["id" : Long.toString(item2.expenseType.id)]
        controller.request.method = "POST"
        controller.search()

        def items = controller.modelAndView.model.itemList
        assertEquals 0, items.size
    }

    void testSearchByEventAndTypeAndPayee() {
        createTestData()
        controller.params.event = ["id" : Long.toString(item2.event.id)]
        // Use item2's type, it is setup to use the other expenseType'
        controller.params.expenseType = ["id" : Long.toString(item2.expenseType.id)]
        controller.params.payee = item2.payee
        controller.request.method = "POST"
        controller.search()

        def items = controller.modelAndView.model.itemList
        assertEquals 1, items.size
    }

    void testSearchByPayee() {
        createTestData()
        controller.params.payee = item2.payee
        controller.request.method = "POST"
        controller.search()

        def items = controller.modelAndView.model.itemList
        assertEquals 1, items.size
    }

    void testSearchByInvalidPayee() {
        createTestData()
        // this is all caps, should it work?
        controller.params.payee = "DELTA"
        controller.request.method = "POST"
        controller.search()
        def items = controller.modelAndView.model.itemList
        assertEquals 0, items.size

        controller.params.payee = "D E L T A"
        controller.request.method = "POST"
        controller.search()
        items = controller.modelAndView.model.itemList
        assertEquals 0, items.size
    }

    void testSearchByAmountRange() {
        createTestData()
        // TODO
        controller.params.payee = item2.payee
        controller.request.method = "POST"
        controller.search()

        def items = controller.modelAndView.model.itemList
        assertEquals 1, items.size
    }

    void testSearchByDateRange() {
        createTestData()
        // TODO
        controller.params.payee = item2.payee
        controller.request.method = "POST"
        controller.search()

        def items = controller.modelAndView.model.itemList
        assertEquals 1, items.size
    }

    void testSearchByUser() {
        createTestData()

        controller.session.putValue( "user",  item1.user )
        //controller.request.method = "POST"
        controller.myitems()

        def items = controller.modelAndView.model.itemList
        assertEquals 2, items.size

        controller.session.putValue( "user",  User.findByUserid("admin") )
        //controller.request.method = "POST"
        controller.myitems()

        items = controller.modelAndView.model.itemList
        assertEquals 0, items.size
    }


    void createTestData() {

        
        // initialize some domain objects for testing
        ExpenseType expenseType = new ExpenseType(name:"meal")
        //ExpenseCategory category = new ExpenseCategory(name: "travel")
        User user = new User(userid: "testuser", lastName: "bloggs", firstName: "Joe", email: "jb@email.com", password:"test")
        ExpenseEvent event = new ExpenseEvent(name: "Conference")
        item1 = new Item(desc:"Item 1",
            payee: "chilis",
            amount: 12.95,
            paymentType: PaymentType.CASH,
            expenseType: expenseType,
            //category: category,
            user: user,
            event: event
        )
        user.save()
        event.save()
        expenseType.save()
        // category.save()
        item1.save()



        //additional categories
        ExpenseType type2 = new ExpenseType(name:"airfare")
        type2.save()
        //(new ExpenseCategory(name: "entertainment")).save()
        (new User(userid: "admin", lastName: "admin", firstName: "admin", email: "admin@email.com", password: "test")).save()
        ExpenseEvent event2 = new ExpenseEvent(name: "Sales Meeting1")
        event2.save()

        item2 =new Item(desc:"Item 2",
            payee: "Delta",
            amount: 380.90,
            paymentType: PaymentType.CREDITCARD,
            expenseType: type2,
            //category: category,
            user: user,
            event: event2
        )
        item2.save()
    }
}
