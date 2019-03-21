package servers.socketserver;

import java.util.LinkedList;

public class Port {

	private static LinkedList<Integer> portID;
	private static LinkedList<Boolean> portState;
	
	public static void init() {
		portID = new LinkedList<>();
		portState = new LinkedList<>();
	}
	
	public static boolean isStart(int id) {
		int i = -1;
		if((i = portID.indexOf(id)) == -1) {
			return false;
		}
		else {
			return portState.get(i);
		}
	}
	
	public static boolean startPort(int id) {
		int i = portID.indexOf(id);
		if(i == -1) {
			portID.add(id);
			portState.add(true);
			return true;
		}
		else {
			if(!portState.get(i)) {
				portState.set(i, true);
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	public static boolean endPort(int id) {
		int i = portID.indexOf(id);
		if(i == -1) {
			return false;
		}
		else {
			portState.set(i, false);
			return true;
		}
	}
	
	public static LinkedList<Integer> getPortID() {
		return portID;
	}

	public static LinkedList<Boolean> getPortState() {
		return portState;
	}

	public static void display() {
		System.out.println("Port:\nPID	State");
		for(int i = 0; i < portID.size(); i++) {
			System.out.println(portID.get(i) + "	" + portState.get(i));
		}
		System.out.println();
	}
	
}
