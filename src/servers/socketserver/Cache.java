package servers.socketserver;

import java.util.LinkedList;

import service.Service;

public class Cache {

	private static final int CACHE_SIZE = 100;
	
	public static LinkedList<Service> cacheService;
	private static LinkedList<String> cacheServiceName;
	private static LinkedList<String> cacheMachineID;
	

	public static void init() {
		cacheService = new LinkedList<>();
		cacheServiceName = new LinkedList<>();
		cacheMachineID = new LinkedList<>();
	}
	
	public static LinkedList<Service> get(String machineID) {
		LinkedList<Service> ss = new LinkedList<>();
		for(Service s: cacheService) {
			if(s.getMachineID().equals(machineID)) {
				ss.add(s);
			}
		}
		return ss;
	}
	
	public static void add(Service s) {
		boolean find = false;
		int i = 0;
		for(i = 0; i < cacheMachineID.size(); i++) {
			if(cacheMachineID.get(i).equals(s.getMachineID())) {
				if(cacheServiceName.get(i).equals(s.getServiceName())) {
					find = true;
					break;
				}
				else {
					continue;
				}
			}
		}
		if(find) {
			
			cacheServiceName.remove(i);
			cacheMachineID.remove(i);
			cacheService.remove(i);
			cacheServiceName.addFirst(s.getServiceName());
			cacheMachineID.addFirst(s.getMachineID());
			cacheService.addFirst(s);
		}
		else {
			if(cacheService.size() >= CACHE_SIZE) {
				cacheServiceName.removeLast();
				cacheMachineID.removeLast();
				cacheService.removeLast();
				cacheServiceName.addFirst(s.getServiceName());
				cacheMachineID.addFirst(s.getMachineID());
				cacheService.addFirst(s);
			}
			else {
				cacheServiceName.addFirst(s.getServiceName());
				cacheMachineID.addFirst(s.getMachineID());
				cacheService.addFirst(s);
			}
		}
	}
	
	public static void display() {
		System.out.println("Cache:\nServiceName	MachineID	Port	Connect	Read	Flush");
		for(int i = 0; i < cacheService.size(); i++) {
			cacheService.get(i).display();
		}
		System.out.println();
	}
	
}
