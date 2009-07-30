class Item {

    String desc
    Date createDate = new Date()
    String payee
    Float amount    
    ExpenseType expenseType
    //ExpenseCategory category
    ExpenseEvent event
    User user
    PaymentType paymentType
    boolean submitted = false
    boolean reimbursed = false
    boolean approved = false

    static constraints = {
        expenseType(nullable:true)
        //category(nullable:true)
        event(nullable:true)
        desc(nullable:false)
        payee(nullable:false)
        user(nullable:false)
        amount(nullable:false)
        createDate(nullable:false)
        paymentType(nullable:false)
    }

    // private Image image;
    
}
