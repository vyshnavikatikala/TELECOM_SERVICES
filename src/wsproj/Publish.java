package wsproj;

import javax.xml.ws.Endpoint;

public class Publish {
	public static void main(String[] args) {
		HelloService hello=new HelloServiceImpl();
		
		Endpoint.publish("http://localhost:3000/ws/hello", hello);
		
		System.out.println("service deployed....");
	}
}
