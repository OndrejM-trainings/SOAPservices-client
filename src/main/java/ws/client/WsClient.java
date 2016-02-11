package ws.client;

import javax.enterprise.context.RequestScoped;
import javax.xml.ws.WebServiceRef;

import ws.client.gen.*;

@RequestScoped
public class WsClient {
	@WebServiceRef(GreetingWebService_Service.class)
	private GreetingWebService ws;

	public void callWebService() {
		System.out.println(ws.sayHello("Ondro"));
	}
}
