
import org.codehaus.groovy.grails.plugins.spring.ws.EndpointFunctionalTestCase
import groovy.xml.MarkupBuilder
import org.codehaus.groovy.grails.plugins.spring.ws.EndpointFunctionalTestCase

class ExpenseEndpointFunctionalTests extends EndpointFunctionalTestCase {
	
    def serviceURL = "http://localhost:8080/ExpenseG/services"

    def namespace = "http://mypatelspace.com/schemas"

    void setUp(){
        super.setUp()
        webServiceTemplate.setDefaultUri(serviceURL)
    }

    void testSOAPDocumentService() {
        def request = this.createStringRequest()
        def response = webServiceTemplate.sendToEndpoint(request)
        println(response)
        def fileExpenseResponse = new XmlSlurper().parseText(response)
        def status = fileExpenseResponse.status
        assert status == "complete"
    }

    String createStringRequest(){
        def writer = new StringWriter()
        def request = new MarkupBuilder(writer)
        // Preparing the request document
        request.ExpenseRequest(xmlns: namespace) {
            Item {
                desc('lunch')
                payee('bk')
                amount(15.99)
            }
        }
        writer.toString()
    }
}