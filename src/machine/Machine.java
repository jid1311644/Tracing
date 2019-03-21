package machine;

public class Machine {

	private String machineID;
	private int numServices = 0;
	private boolean isOff = true;
	
	public Machine(String machineID) {
		// TODO Auto-generated constructor stub
		this.machineID = machineID;
	}
	
	public Machine(String machineID, int num) {
		// TODO Auto-generated constructor stub
		this.machineID = machineID;
		this.numServices = num;
	}

	public String getMachineID() {
		return machineID;
	}

	public int getNumServices() {
		return numServices;
	}

	public void setNumServices(int numServices) {
		this.numServices = numServices;
	}

	public boolean isOff() {
		return isOff;
	}

	public void setOff(boolean isOff) {
		this.isOff = isOff;
	}
	
}
