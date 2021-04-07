package billpay;

import javax.xml.ws.Endpoint;

public class Publish {
	public static void main(String[] args) {
		BillPayment billpay=new ZapBillPay();
		
		Endpoint.publish("http://localhost:3001/ws/billpay", billpay);
		
		System.out.println("Bill service deployed....");
	}
}
