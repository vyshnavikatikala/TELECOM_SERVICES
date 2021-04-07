package numberport;

import javax.xml.ws.Endpoint;

public class Publish {
	public static void main(String[] args) {
		NumberPortability number=new ZapNumberPort();
		
		Endpoint.publish("http://localhost:3003/ws/number", number);
		
		System.out.println("Number Portability service deployed....");
	}
}
