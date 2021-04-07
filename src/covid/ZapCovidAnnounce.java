package covid;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "covid.CovidAnnouncement")
public class ZapCovidAnnounce implements CovidAnnouncement{
	
	@WebMethod
	@Override
	public void doPublicAnnouncement(String msg) {
		System.out.println("public announcement done....");
	}
}