import groovy.xml.MarkupBuilder
class NotificationController {


    def index = { redirect(action:newItemTest, params:params) }

    def addItemJMS = {
            //build XML REQUEST msg
        def message = buildXML()

        // send to QUEUE
        sendJMSMessage("queue.notification", message)
        render "<h2>Sent JMS XML message!</h2>"
    }

    def newItemTest = {

        //build XML REQUEST msg
        def message = buildXML()

        // send to QUEUE
        sendJMSMessage("queue.notification", message)
        sleep(1000)

        // Get the list from the web page, and check new item is there
        def webPageTest = new CheckNewUserCreatedTest()
        webPageTest.initVirtualMethods()

        try {
            webPageTest.testSearch()
        } catch (functionaltestplugin.FunctionalTestException e) {
            render "Test failed, reason: "+e
        }

        render "<h2>The Create New Item Using JMS XML message worked!</h2>"
    }

    def camelExample = {
        sendJMSMessage("ItemRequestQueue", buildApprovalXML())
        render "Approval Message Sent"
        
    }

    String buildXML() {
        def writer = new StringWriter()
        def xml = new MarkupBuilder(writer)
        xml.item() {
            desc('Burks Item')
            payee('Chops')
            amount(39.99)
        }
        return writer.toString()
    }

    String buildApprovalXML() {
        def writer = new StringWriter()
        def xml = new MarkupBuilder(writer)
        xml.item() {
            id(101)
            desc('Item NEW ITEM')
            amount(39.99)
            approver("Mr. Smith")
        }
        return writer.toString()
    }

    def jmsTemplate

    def browseQueue = {
        
    }
}
