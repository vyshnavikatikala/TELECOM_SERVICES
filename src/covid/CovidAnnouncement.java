package covid;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface CovidAnnouncement{
	@WebMethod
	public void doPublicAnnouncement(String msg);
}