class HolidayEndpoint {
  def static namespace = "http://mycompany.com/hr/schemas"

  def invoke = { request, response ->

    // Using the incoming document
    println "Holiday Request Received!"
    println "Start Date: ${request.Holiday.StartDate}"
    println "End Date: ${request.Holiday.EndDate}"

    // Typically you'd invoke some internal business services here

    // Preparing the response document
    response.HolidayResponse(xmlns: namespace) {
      status('complete')
    }
  }
}