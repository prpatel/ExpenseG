class CreateNewItems {

    static main( args ){
        println "Hello World!"
    }

    void createItem() {
        ExpenseType expenseType = new ExpenseType(name:"meal")
        //ExpenseCategory category = new ExpenseCategory(name: "travel")
        User user = new User(userid: "testuser", lastName: "bloggs", firstName: "Joe", email: "jb@email.com", password:"test")
        ExpenseEvent event = new ExpenseEvent(name: "Conference")
        item1 = new Item(desc:"Item 1",
            payee: "chilis",
            amount: 12.95,
            paymentType: PaymentType.CASH,
            expenseType: expenseType,
            user: user,
            event: event
        )
        user.save()
        event.save()
        expenseType.save()
        item1.save()

    }


}