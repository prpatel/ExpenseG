import org.codehaus.groovy.grails.plugins.spring.ws.EndpointFunctionalTestCase
import groovy.xml.MarkupBuilder
class HolidayEndpointFunctionalTests extends EndpointFunctionalTestCase {

  def serviceURL = "http://localhost:8080/ExpenseG/services"

  def namespace = "http://mycompany.com/hr/schemas"

  void setUp(){
    super.setUp()
    webServiceTemplate.setDefaultUri(serviceURL)
  }

  void testSOAPDocumentService() {
    def request = this.createStringRequest()
    def response = webServiceTemplate.sendToEndpoint(request)
    println(response)
    def holidayResponse = new XmlSlurper().parseText(response)
    def status = holidayResponse.status
    assert status == "complete"
  }

  String createStringRequest(){
    def writer = new StringWriter()
    def request = new MarkupBuilder(writer)
    // Preparing the request document
    request.HolidayRequest(xmlns: namespace) {
      Holiday {
        StartDate("2006-07-03")
        EndDate("2006-07-07")
      }
      Employee {
        Number("42")
        FirstName("Russ")
        LastName("Miles")
      }
    }
    writer.toString()
  }
}