package numberport;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "numberport.NumberPortability")
public class ZapNumberPort implements NumberPortability{
	
	@WebMethod
	@Override
	public void portNumber(int number, String telecom) {
		System.out.println(number+" :Number portability logic written..."+telecom);
	}
}