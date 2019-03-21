package servers.socketserver;

import java.util.LinkedList;

import gui.MainGUI;
import machine.Machine;
import servers.dbserver.DatabaseServer;
import service.Service;

public class SocketServer {
	
	public static LinkedList<Service> running;
	public static LinkedList<Machine> machines;

	public SocketServer() {
		// TODO Auto-generated constructor stub
		running = new LinkedList<>();
		machines = new DatabaseServer().getMachines(MainGUI.currentUser);
		
		Port.init();
		Cache.init();
	}
	
	public static boolean isRunning(Machine m) {
		for(Service s: running) {
			if(s.getMachineID().equals(m.getMachineID())) {
				return true;
			}
		}
		return false;
	}
	
}

