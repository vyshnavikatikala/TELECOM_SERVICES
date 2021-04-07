package wsproj;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "wsproj.HelloService")
public class HelloServiceImpl implements HelloService{
	
@WebMethod
@Override
public String sayHello(String name) {
	// TODO Auto-generated method stub
	return "Welcome to Webservices....:"+name;
}
}
