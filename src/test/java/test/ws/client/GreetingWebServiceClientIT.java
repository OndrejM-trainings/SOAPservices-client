package test.ws.client;

import com.sun.xml.internal.ws.developer.WSBindingProvider;
import java.net.*;
import java.util.Arrays;
import java.util.logging.*;
import javax.xml.namespace.QName;
import javax.xml.ws.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ws.client.gen.*;

public class GreetingWebServiceClientIT {

    private GreetingWebService ws;

    @Before
    public void setUp() throws MalformedURLException {
        System.setProperty("com.sun.xml.ws.transport.local.LocalTransportPipe.dump", "true");
        GreetingWebService_Service ws_service = new GreetingWebService_Service(
                new URL("https://localhost:8181/endpoint/GreetingWebService?wsdl"),
                new QName("http://endpoint.ws/", "GreetingWebService"));
        ws = ws_service.getGreetingWebServicePort();
    }

    @Test
    public void canSayHelloToRoland() {
        String greeting = ws.sayHello("Roland");
        assertEquals("Greeting", "Hello, Roland!", greeting);
    }

    @Test
    public void canVoid() {
        ((BindingProvider)ws).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "user");
        ws.goodBye();
    }

    @Test
    public void canSendGreeting() {
        Greeting g = new Greeting();
        g.setGreetingWord("Hello");
        final Person sender = new Person();
        sender.setFirstName("Pavel");
        sender.setGender(Gender.MALE);
        g.setSender(sender);
        
        final Person recv = new Person();
        recv.setFirstName("Jana");
        recv.setGender(Gender.FEMALE);
        g.setReceiver(recv);
        
        System.out.println("Result: " + ws.sendGreeting(g)); 
    }
    
    @Test
    public void catGetException () {
        ws.goodBye();
        try {
            ws.getNumberOfHellosWithoutGoodBye();
        } catch (Exception_Exception ex) {
            Logger.getLogger(GreetingWebServiceClientIT.class.getName()).log(Level.SEVERE, 
                    ex.getMessage(), ex);
        }
    }
    
    public static void main(String[] args) throws MalformedURLException {
        final GreetingWebServiceClientIT test = new GreetingWebServiceClientIT();
        test.setUp();
        test.canSendGreeting();
    }

}
