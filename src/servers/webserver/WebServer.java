package servers.webserver;

import java.util.LinkedList;

import gui.page.runningpage.RunningLabel;
import gui.page.runningpage.RunningPage;
import machine.Machine;
import servers.dbserver.DatabaseServer;
import servers.socketserver.Cache;
import servers.socketserver.Port;
import servers.socketserver.SocketServer;
import service.Service;
import user.User;

public class WebServer {

	public static final int LOGIN = 0;
	public static final int SIGNIN = 1;
	public static final int ADD_MACHINE = 2;
	public static final int DELETE_MACHINE = 3;
	public static final int START = 4;
	public static final int END = 5;
	
	private Message requestMsg;
	private Message responseMsg;

	public WebServer(int operate, Message msg) {
		// TODO Auto-generated constructor stub
		this.requestMsg = msg;
		switch (operate) {
		case LOGIN:
			responseMsg = login();
			break;
		case SIGNIN:
			responseMsg = signIn();
			break;
		case ADD_MACHINE:
			responseMsg = addMachine();
			break;
		case DELETE_MACHINE:
			responseMsg = deleteMachine();
			break;
		case START:
			responseMsg = start();
			break;
		case END:
			responseMsg = end();
			break;
		default:
			break;
		}
	}

	private Message end() {
		// TODO Auto-generated method stub
		String name = requestMsg.getRequestMsg().split("&,&")[0];
		String mid = requestMsg.getRequestMsg().split("&,&")[1].trim();
		boolean f = false;
		for(Service s: SocketServer.running) {
			if(s.getMachineID().equals(mid)) {
				f = true;
				s.setState(Service.CLOSE);
			}
		}
		if(f) {
			return new Message("Close successful!", Message.OK);
		}
		else {
			return new Message("Already close!", Message.ERROR);
		}
	}

	private Message start() {
		// TODO Auto-generated method stub
		
		String name = requestMsg.getRequestMsg().split("&,&")[0];
		String mid = requestMsg.getRequestMsg().split("&,&")[1].trim();
		LinkedList<Service> ss = Cache.get(mid);
		if(ss.size() == 0) {
			ss = new DatabaseServer().getServices(name, mid);
		}
		boolean f = false;
		for(Service s: ss) {
			if(Port.isStart(s.getPort())) {
				f = true;
				break;
			}
		}
		if(!f) {
			for(Service s: ss) {
				Port.startPort(s.getPort());
				Cache.add(s);
			}
			for(Service s: ss) {
				SocketServer.running.add(s);
				RunningLabel rl = new RunningLabel(null, s);
				RunningPage.labelServices.add(rl);
				Running runService = new Running(s, rl);
				new Thread(runService).start();
			}
			return new Message("Running...", Message.OK);
		}
		else {
			return new Message("Port is occupied!", Message.ERROR);
		}
	}

	private Message login() {
		// TODO Auto-generated method stub
		String name = requestMsg.getRequestMsg().split("&,&")[0];
		String psw = requestMsg.getRequestMsg().split("&,&")[1].trim();
		if(name.equals("") || psw.equals("")) {
			return new Message("Name and password cannot be empty.", Message.WARNING);
		}
		else {
			DatabaseServer database = new DatabaseServer();
			User u = database.getUser(name);
			if(u.getUsername().equals("") || u.getPassword().equals("")) {
				return new Message("Username or password error!", Message.ERROR);
			}
			else if(u.getPassword().equals(new Encrypt(name, psw).Encrypt())) {
				return new Message("Login successful!", Message.OK);
			}
			else {
				return new Message("Username or password error!", Message.ERROR);
			}
		}
	}
	
	private Message signIn() {
		// TODO Auto-generated method stub
		String name = requestMsg.getRequestMsg().split("&,&")[0];
		String psw = requestMsg.getRequestMsg().split("&,&")[1].trim();
		if(name.equals("") || psw.equals("")) {
			return new Message("Name and password cannot be empty.", Message.WARNING);
		}
		else {
			DatabaseServer database = new DatabaseServer();
			if(database.addUser(new User(name, new Encrypt(name, psw).Encrypt()))) {
				return new Message("Registered successfully and login.", Message.OK);
			}
			else {
				return new Message("Username already exists!", Message.ERROR);
			}
		}
	}
	
	private Message addMachine() {
		// TODO Auto-generated method stub
		String[] m = requestMsg.getRequestMsg().split("\r\n");
		String name = m[0].split("&,&")[0];
		String machineID = m[0].split("&,&")[1];
		int numOfServices = Integer.parseInt(m[0].split("&,&")[2]);
		
		DatabaseServer database = new DatabaseServer();
		if(!database.addMachine(name, new Machine(machineID, numOfServices))) {
			return new Message("MachineID already exists!", Message.ERROR);
		}
		else {
			for(int i = 0; i < numOfServices; i++) {
				String[] sm = m[i+1].split("&,&");
				String serviceName = sm[0];
				int port = Integer.parseInt(sm[1]);
				int cTimeout = Integer.parseInt(sm[2]);
				int rTimeout = Integer.parseInt(sm[3]);
				int flush = Integer.parseInt(sm[4]);
				if(!database.addService(name, new Service(serviceName, machineID, port, cTimeout, rTimeout, flush))) {
					return new Message("Add service exception!", Message.ERROR);
				}
			}
			return new Message("Add successful!", Message.OK);
		}
	}

	private Message deleteMachine() {
		// TODO Auto-generated method stub
		String name = requestMsg.getRequestMsg().split("&,&")[0];
		String machineID = requestMsg.getRequestMsg().split("&,&")[1];
		DatabaseServer database = new DatabaseServer();
		if(database.deleteMachine(name, machineID)) {
			return new Message("Delete successful!", Message.OK);
		}
		else {
			return new Message("Delete machine exception!", Message.ERROR);
		}
	}

	public Message sendResponseMsg() {
		return responseMsg;
	}
	
}
