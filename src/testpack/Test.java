package testpack;

import billpay.BillPayment;
import controlpack.Controller;
import controlpack.Telco;
import covid.CovidAnnouncement;
import numberport.NumberPortability;
import wsproj.HelloService;

public class Test {
	public static void main(String[] args)throws Exception {
		Telco telco=new Telco();
		Object obj=Controller.getTelecomAppObject(telco);
		
		BillPayment billPay=(BillPayment)obj;
		billPay.payBill(100);
		
		HelloService helloService=(HelloService)obj;
		String result=helloService.sayHello("ramu");
		System.out.println("Result..:"+result);
		
//		CovidAnnouncement ca=(CovidAnnouncement)obj;
//		ca.doPublicAnnouncement("maski masku.....");
//		
//		NumberPortability np=(NumberPortability)obj;
//		np.portNumber(89898943, "airtel");
	}
}
