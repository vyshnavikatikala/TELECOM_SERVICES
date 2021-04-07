package wsproj;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;


public class Client {
	public static void main(String[] args)throws Exception {
		URL url=new URL("http://localhost:3000/ws/hello?wsdl");
		
		QName qname=new QName("http://wsproj/","HelloServiceImplService");
		
		Service service=Service.create(url,qname);
		
		HelloService osi=service.getPort(HelloService.class);
		
		String result=osi.sayHello("coda services..");
		
		System.out.println("Result...."+result);
	}
	
}
