package ws.client;

import javax.enterprise.context.*;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class OnStart {
	
	@Inject
	private WsClient wsClient;
	
	public void beforeRequest(@Observes @Initialized(RequestScoped.class) Object event) {
		wsClient.callWebService();
	}

}
