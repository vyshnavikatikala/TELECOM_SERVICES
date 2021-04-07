package numberport;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface NumberPortability{
	@WebMethod
	public void portNumber(int number,String telecom);
}