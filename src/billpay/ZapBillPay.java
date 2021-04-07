package billpay;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "billpay.BillPayment")
public class ZapBillPay implements BillPayment{
	@WebMethod
	@Override
	public void payBill(int amt) {
		System.out.println("bill payment logic written and executed...");
		System.out.println("bill payment done....");
	}
}