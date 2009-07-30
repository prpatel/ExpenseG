import groovy.xml.MarkupBuilder
class OnNotificationService {

    // tell it you need a spring/hibernate tx
    boolean transactional = true
    // tell it you'll be doing JMS stuff in this class
    static expose = ['jms']
    // tell it which Queue you'll be reading from
    static destination = "queue.notification"

    // default method name, configurable
    def onMessage = {
        println "GOT MESSAGE:\n $it"

        def item = new XmlSlurper().parseText(it)
        println item.desc
        println item.payee
        println item.amount
        Item item2 = new Item(desc: item.desc.toString(),
            payee: item.payee.toString(),
            amount: item.amount.toString(),
            paymentType: PaymentType.CASH,
            expenseType: ExpenseType.findByName('meal'),
            user: User.findByUserid('testuser'),
            event: ExpenseEvent.findByName('Conference')
        )
        def msg = validateAndSave(item2)
        println msg
        // send a respone msg back with success to another queue
        println buildResponseXML(item2.id, message)


    }

    String buildResponseXML(Long id, String returnMessage) {
        def writer = new StringWriter()
        def xml = new MarkupBuilder(writer)
        xml.response() {
            itemId(id)
            returnMessage(returnMessage)

        }
    }

    def validateAndSave = { object ->
        def validate = object.validate()
        def message = object.errors.allErrors.inject('') {str, error ->
            str + "Field error in object ${error.objectName} on field ${error.field}: rejected value [${error.rejectedValue}];"
        }
        if (validate && object.save()) {
            return "SUCCESS"
        } else {
            return message
        }
    }
}
