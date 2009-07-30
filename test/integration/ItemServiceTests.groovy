class ItemServiceTests extends GroovyTestCase {
    def itemService;
    def Item  item1;
    void setUp() {
        createTestData()
    }

    void testSave() {
        def returnVal =  itemService.save(
        "desc",
            new Date(),
        "payee",
            new Float(10),
            new Long(1),
            new Long(1),
            new Long(1),
            new Long(1),
            true,
            true
        )

        return
        assertEquals "SUCCESS", returnVal
    }

    void testGet() {
        def returnedItem = itemService.get(item1.id)
        assertNotNull "itemService returned null object", returnedItem
        assertEquals item1.toString(), returnedItem
        println returnedItem
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
            user: user,
            event: event
        )
        user.save()
        event.save()
        expenseType.save()
        item1.save()
    }



}
