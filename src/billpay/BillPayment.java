package billpay;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface BillPayment{
	@WebMethod
	public void payBill(int amt);
}