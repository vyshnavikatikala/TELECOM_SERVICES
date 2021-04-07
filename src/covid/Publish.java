package covid;

import javax.xml.ws.Endpoint;

public class Publish {
	public static void main(String[] args) {
		CovidAnnouncement covid=new ZapCovidAnnounce();
		
		Endpoint.publish("http://localhost:3002/ws/covid", covid);
		
		System.out.println("Covid announcement service deployed....");
	}
}
