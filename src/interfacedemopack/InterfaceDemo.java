package interfacedemopack;

import java.io.FileInputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import wsproj.HelloService;

public class InterfaceDemo {
	public static void main(String[] args) {
		Object tap=Airtel.getTelecomApp(HelloService.class,BillPayment.class,NumberPortability.class,CovidAnnouncement.class);
		BillPayment bpp=(BillPayment)tap;
		bpp.payBill(100);
		
		NumberPortability npb=(NumberPortability)tap;
		npb.portNumber(983020122, "vodafone");
		
		CovidAnnouncement caa=(CovidAnnouncement)tap;
		caa.doPublicAnnouncement("helllllllllllllllloooooooooooooo");
		
		HelloService osi=(HelloService)tap;
		String result=osi.sayHello("coda services..");
		
		System.out.println("Result...."+result);
	}
}

class Airtel{
	static TelecomApp tap;static BillPayment bp;
	static NumberPortability np;
	static CovidAnnouncement ca;
	static String c[]=new String[10];
	static Object o[]=new Object[10];
	static {
		int count=0;
		try {
		tap=new TelecomApp();		
		Properties prop=new Properties();
		prop.load(new FileInputStream("config.properties"));
		Enumeration en=prop.elements();
		while(en.hasMoreElements()) {
			String name=(String)en.nextElement();
				o[count]=Class.forName(name).newInstance();
				++count;
			}
		URL url=new URL("http://localhost:3000/ws/hello?wsdl");
		
		QName qname=new QName("http://wsproj/","HelloServiceImplService");
		
		Service service=Service.create(url,qname);
		String s="wsproj.HelloService";
		Class<?> classType=Class.forName(s);
		//HelloService osi=(HelloService) service.getPort(Class.forName(s));	
		o[count]=service.getPort(Class.forName(s));;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static Object getTelecomApp(Class... classes) {
		//I will mix telecomapp and bill payment
		
		Object obj=Proxy.newProxyInstance(tap.getClass().getClassLoader(), 
				classes,new MyInvocationHandler(o));
		return obj;
	}
}
class MyInvocationHandler implements InvocationHandler{
	Object obj[];
	public MyInvocationHandler(Object obj[]) {
		this.obj=obj;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
//		System.out.println("invoke method called...");
//		//System.out.println("Proxy...:"+proxy);
//		System.out.println("method...:"+method);
//		System.out.println("arguments...:"+args[0]);
		Object ret=null;
		for(Object o:obj) {
			if(o!=null) {
			Method m[]=o.getClass().getDeclaredMethods();
			for(Method mm:m) {
				mm.setAccessible(true);
			}
			try {
				ret=method.invoke(o, args);
			}catch(Exception e) {
				//e.printStackTrace();
			}
			}
		}
		return ret;
	}
}
class TelecomApp{
	
}
interface BillPayment{
	public void payBill(int amt);
}
class ZapPay implements BillPayment{
	@Override
	public void payBill(int amt) {
		System.out.println("bill payment logic written and executed...");
		System.out.println("bill payment done....");
	}
}

interface NumberPortability{
	public void portNumber(int number,String telecom);
}
class ZapNumberPorto implements NumberPortability{
	@Override
	public void portNumber(int number, String telecom) {
		System.out.println("Number portability logic written...");
	}
}

interface CovidAnnouncement{
	public void doPublicAnnouncement(String msg);
}
class ZapPublicAnnouncement implements CovidAnnouncement{
	@Override
	public void doPublicAnnouncement(String msg) {
		System.out.println("public announcement done....");
	}
}