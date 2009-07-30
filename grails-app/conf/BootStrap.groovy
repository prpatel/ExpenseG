import grails.util.GrailsUtil
class BootStrap {

    def init = { servletContext ->

        switch(GrailsUtil.environment) {
            case "development":
            log.info( 'BootStrap development' )
            populateData()
            case "test":
            log.info( 'BootStrap test' )
            break
            case "production":
            log.info( 'BootStrap production' )
            populateData()

        }
 
    }
     
    def destroy = {
    }

    void populateData() {
        // initialize some domain objects for testing
        ExpenseType expenseType = new ExpenseType(name:"meal")
        ExpenseType type2 = new ExpenseType(name:"airfare")
        type2.save()
        //ExpenseCategory category = new ExpenseCategory(name: "travel")
        User user = new User(userid: "testuser", lastName: "bloggs", firstName: "Joe", email: "jb@email.com", password:"password")
        (new User(userid: "admin", lastName: "admin", firstName: "admin", email: "admin@email.com", password: "test")).save()
        ExpenseEvent event = new ExpenseEvent(name: "Conference")
        ExpenseEvent event2 = new ExpenseEvent(name: "Sales Meeting1")
        event2.save()
        user.save()
        event.save()
        expenseType.save()
        // category.save()

        Item item = new Item(desc:"Item 1",
            payee: "chilis",
            amount: 12.95,
            paymentType: PaymentType.CASH,
            expenseType: expenseType,
            user: user,
            event: event
        )
        item.save()

        Item item2 = new Item(desc:"Item 2",
            payee: "outback",
            amount: 14.95,
            paymentType: PaymentType.CASH,
            expenseType: expenseType,
            user: user,
            event: event
        )
        item2.save()

        Item item3 = new Item(desc:"Item 3",
            payee: "chekers",
            amount: 16.95,
            paymentType: PaymentType.CASH,
            expenseType: expenseType,
            user: user,
            event: event
        )
        item3.save()

        Report report = new Report(title : "Test Report", code : "Test Code")
        report.items = [ item, item2, item3 ]
        report.save()


        (new Item(desc:"Item 2",
                payee: "Delta",
                amount: 380.90,
                paymentType: PaymentType.CREDITCARD,
                expenseType: type2,
                //category: category,
                user: user,
                event: event2
            )).save()

    }
} 