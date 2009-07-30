class ExpenseType {

    String name;

    static constraints = {
        name(nullable:false)
    }

    public String toString() {
        return name;
    }

}
