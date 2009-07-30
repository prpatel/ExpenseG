

class ExpenseEndpoint {

    def ItemService itemService
    def static namespace = "http://mypatelspace.com/schemas"
	
    def invoke = { request, response ->
        // Using the incoming document

        Item item2 = new Item(desc: request.Item.desc,
            payee: request.Item.payee,
            amount: request.Item.amount,
            paymentType: PaymentType.CASH,
            expenseType: ExpenseType.findByName('meal'),
            user: User.findByUserid('testuser'),
            event: ExpenseEvent.findByName('conference1')
        )
        itemService.persistItem(item2)

        // Typically you'd invoke some internal business services here

        // Preparing the response document
        response.Expense(xmlns: namespace) {
            status('complete')
        }
    }
}