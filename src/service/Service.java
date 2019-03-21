package service;

import machine.Machine;

public class Service extends Machine{
	
	public static final int CONNECT = 0;
	public static final int READ = 1;
	public static final int CLOSE = -1;
	
	private String serviceName;
	private String machineID;
	private int port;
	private int connectTimeout;
	private int readTimeout;
	private int flushInterval;
	
	private int state = CLOSE;
	private double time;

	public Service(String serviceName, String machineID, int port,
			int connectTimeout, int readTimeout, int flushInterval) {
		super(machineID);
		// TODO Auto-generated constructor stub
		this.serviceName = serviceName;
		this.machineID = machineID;
		this.port = port;
		this.connectTimeout = connectTimeout;
		this.readTimeout = readTimeout;
		this.flushInterval = flushInterval;
		this.state = CLOSE;
		this.time = 0;
	}

	public String getServiceName() {
		return serviceName;
	}

	public String getMachineID() {
		return machineID;
	}

	public int getPort() {
		return port;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public int getFlushInterval() {
		return flushInterval;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public void display() {
		System.out.print(serviceName + "	");
		System.out.print(machineID + "	");
		System.out.print(port + "	");
		System.out.print(connectTimeout + "	");
		System.out.print(readTimeout + "	");
		System.out.print(flushInterval + "");
		System.out.println();
	}
	
	public void displayState() {
		System.out.print(serviceName + "	");
		System.out.print(machineID + "	");
		System.out.print(port + "	");
		System.out.print(time + "s	");
		switch (state) {
		case CLOSE:
			System.out.print("close");
			break;
		case CONNECT:
			System.out.print("connect...");
			break;
		case READ:
			System.out.print("read...");
			break;
		default:
			break;
		}
		System.out.println();
	}
	
}
