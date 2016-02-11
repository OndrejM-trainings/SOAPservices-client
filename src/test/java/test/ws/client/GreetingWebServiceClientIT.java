package test.ws.client;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ws.client.gen.GreetingWebService;
import ws.client.gen.GreetingWebService_Service;

public class GreetingWebServiceClientIT {

	private GreetingWebService ws;
	
	@Before
	public void setUp() {
		GreetingWebService_Service ws_service = new GreetingWebService_Service();
		ws = ws_service.getGreetingWebServicePort();
	}
	
	@Test
	public void canSayHelloToRoland() {
		String greeting = ws.sayHello("Roland");
		assertEquals("Greeting", "Hello, Roland!", greeting);
	}

}
