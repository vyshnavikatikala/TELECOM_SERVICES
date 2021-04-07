package controlpack;

import java.io.FileInputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import wsproj.HelloService;

public class Controller {

	public static Object getTelecomAppObject(Telco telco) {
		int count=0;
		if(telco==null) {
			telco=new Telco();
		}
		try {
			Properties prop=new Properties();
			prop.load(new FileInputStream("service-config.properties"));
			Enumeration counten=prop.elements();
			int noofservices=0;
			while(counten.hasMoreElements()) {
				noofservices=noofservices+1;
				System.out.println(counten.nextElement());
			}
			System.out.println("No of services...:"+noofservices);
			Class c[]=new Class[noofservices];
			Object o[]=new Object[noofservices];
			Enumeration en=prop.elements();
			while(en.hasMoreElements()) {
				String serviceConfigFile=(String)en.nextElement();
				System.out.println("service files...:"+serviceConfigFile);
				Properties servicesProp=new Properties();
				servicesProp.load(new FileInputStream(serviceConfigFile));
				String urlstr=servicesProp.getProperty("url");
				String namespace=servicesProp.getProperty("namespace");
				String servicename=servicesProp.getProperty("servicename");
				String interfacename=servicesProp.getProperty("interfacename");
				System.out.println(urlstr+":"+namespace+":"+servicename+":"+interfacename);
				URL url=new URL(urlstr);
				QName qname=new QName(namespace,servicename);				
				Service service=Service.create(url,qname);				
				o[count]=service.getPort(Class.forName(interfacename));	
				c[count]=Class.forName(interfacename);
				//list.add(Class.forName(interfacename));
				//list.add(interfacename);
				++count;
				}
			
			Object obj=Proxy.newProxyInstance(telco.getClass().getClassLoader(), 
					c,new MyInvocationHandler(o));
			//System.out.println("The object...:"+obj);
			return obj;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
class MyInvocationHandler implements InvocationHandler{
	Object obj[];
	public MyInvocationHandler(Object obj[]) {
		this.obj=obj;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object ret=null;
		for(Object o:obj) {
			if(o!=null) {
			Method m[]=o.getClass().getDeclaredMethods();
			for(Method mm:m) {
				mm.setAccessible(true);
			}
			try {
				System.out.println("method invoked...");
				ret=method.invoke(o, args);
			}catch(Exception e) {
				//e.printStackTrace();
			}
			}
		}
		return ret;
	}
}