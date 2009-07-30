import org.apache.camel.Exchange
import org.apache.camel.Processor
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.language.groovy.GroovyRouteBuilder
import groovy.xml.MarkupBuilder
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.language.groovy.GroovyRouteBuilder
import org.apache.activemq.camel.component.ActiveMQComponent

@Grab(group='org.apache.camel', module='camel-groovy', version='1.6.0')
@Grab(group='org.apache.camel', module='camel-mail', version='1.6.0')
@Grab(group='org.apache.camel', module='camel-core', version='1.6.0')
@Grab(group='org.apache.camel', module='camel-jms', version='1.6.0')
@Grab(group='org.apache.activemq',module='activemq-camel',version='5.2.0')
@Grab(group='org.apache.activemq',module='activemq-core',version='5.2.0')
class ApproveRoute extends GroovyRouteBuilder {
    void configure(){

        

        from("activemq:queue:ItemRequestQueue").process(new ProcessRequest()).to("activemq:queue:ItemResponseQueue")
    }
}

class ProcessRequest implements Processor {
    void process(Exchange exchange) throws Exception {

        def approveRequest = new XmlSlurper().parseText(exchange.in.body)
        println approveRequest.desc
// check to see if the amount is between 5-100 $
        def writer = new StringWriter()
        def xml = new MarkupBuilder(writer)
        xml.response() {
            id(approveRequest.id)
            status("APPROVED")
        }
        println writer.toString()
        exchange.in.body = writer.toString()
    }
}


def camelContext = new DefaultCamelContext()
camelContext.addComponent("activemq", ActiveMQComponent.activeMQComponent("tcp://localhost:61616"));
camelContext.addRoutes(new ApproveRoute());
camelContext.start();
