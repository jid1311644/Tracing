package test;

import java.net.Socket;
import java.util.LinkedList;

import machine.Machine;
import servers.dbserver.DatabaseServer;
import servers.socketserver.Cache;
import servers.socketserver.Port;
import servers.socketserver.SocketServer;
import servers.webserver.Encrypt;
import servers.webserver.Message;
import servers.webserver.WebServer;
import service.Service;
import user.User;

public class Test {
	
	public int i = 0;
	
	public void set() {
		i = 1;
	}
	
	public static void main(String[] args) throws InterruptedException {
//		DatabaseServer d = new DatabaseServer();
		
//		User user = d.getUser("admin");
//		System.out.println(user.getUsername() + " " + user.getPassword());
		
//		LinkedList<Machine> m = d.getMachines("admin");
//		for(int i = 0; i < m.size(); i++) {
//			System.out.println(m.get(i).getMachineID());
//		}
		
//		LinkedList<Service> s = d.getServices("admin", "machine1");
//		for(int i = 0; i < s.size(); i++) {
//			s.get(i).display();
//		}
		
//		System.out.println(d.addUser(new User("user2", "123456")));
//		System.out.println(d.addMachine("user2", new Machine("machine1")));
		
//		Service s = new Service("service3", "machine1", 100, 101, 102, 103);
//		System.out.println(d.addService("admin", s));
		
//		WebServer webServer = new WebServer(WebServer.LOGIN, new Message("admin&,&adminpsw"));
//		Message m = webServer.sendResponseMsg();
//		System.out.println(m.getResponseMsg() + " " + m.getResponseMsgType());

//		String rm = "user1&,&machine1&,&2\r\n"
//				+ "service1&,&100&,&101&,&-1&,&16\r\n"
//				+ "service2&,&200&,&201&,&-1&,&26\r\n";
//		WebServer webServer = new WebServer(WebServer.DELETE_MACHINE,  new Message("user1&,&machine1"));
//		Message m = webServer.sendResponseMsg();
//		System.out.println(m.getResponseMsg() + " " + m.getResponseMsgType());
		
//		Cache.init();
//		Cache.add(new Service("service1", "machine1", 100, 101, 102, 103));
//		for(int i = 0; i < Cache.cacheService.size(); i++) {
//			Cache.cacheService.get(i).display();
//		}
//		System.out.println();
//		Cache.add(new Service("service2", "machine1", 200, 201, 202, 203));
//		for(int i = 0; i < Cache.cacheService.size(); i++) {
//			Cache.cacheService.get(i).display();
//		}
//		System.out.println();
//		Cache.add(new Service("service3", "machine1", 300, 301, 302, 303));
//		for(int i = 0; i < Cache.cacheService.size(); i++) {
//			Cache.cacheService.get(i).display();
//		}
//		System.out.println();
//		Cache.add(new Service("service2", "machine1", 200, 201, 202, 203));
//		for(int i = 0; i < Cache.cacheService.size(); i++) {
//			Cache.cacheService.get(i).display();
//		}
//		System.out.println();
//		Cache.add(new Service("service4", "machine1", 200, 201, 202, 203));
//		for(int i = 0; i < Cache.cacheService.size(); i++) {
//			Cache.cacheService.get(i).display();
//		}
//		System.out.println();
		
//		SocketServer ss = new SocketServer();
//		Service s = new Service("service1", "machine1", 100, 2000, -1, 505);
//		Service s0 = new Service("service3", "machine1", 100, 2000, 5000, 1077);
//		ss.startService(s);
//		ss.startService(s0);
//		Thread.sleep(5000);
//		s.setState(Service.CLOSE);
//		
//		Port.display();
//		Cache.display();
		
//		System.out.println(new Encrypt("admin", "adminpsw").Encrypt());
//		System.out.println(new Encrypt("user1", "user1psw").Encrypt());
//		System.out.println(new Encrypt("user2", "123456").Encrypt());
//		System.out.println(new Encrypt("user3", "psw").Encrypt());
	
//		String s = "123456&,&23565\r\n1s23ad13s21\r\n";
//		String[] ss = s.split("\r\n");
//		System.out.println(ss.length);
//		for(int i = 0; i < ss.length; i++) {
//			System.out.println(ss[i]);
//		}
		
//		Test test = new Test();
//		Test t = test;
//		System.out.println(test.equals(test));
//		
//		Test t2 = new Test();
//		t2.set(t.i);
//		System.out.println(t.i + " " + t2.i);

//		Test t = new Test();
//		Test t1 = new Test();
//		Test t2 = new Test();
//		
//		LinkedList<Test> l = new LinkedList<>();
//		l.add(t);
//		l.add(t1);
//		l.add(t2);
//		
//		System.out.println(l.indexOf(t1));
	}
	
	

}
