package servers.webserver;

import javax.crypto.Mac;

import gui.page.assistpage.AssistantPage;
import gui.page.runningpage.RunningLabel;
import machine.Machine;
import servers.socketserver.Cache;
import servers.socketserver.Port;
import servers.socketserver.SocketServer;
import service.Service;

public class Running implements Runnable {
	
	public volatile Service service;
	private RunningLabel label;
	private int time = 0;
	
	public Running(Service s, RunningLabel l) {
		// TODO Auto-generated constructor stub
		this.service = s;
		this.service.setState(Service.CONNECT);
		this.service.setTime(time);
		this.label = l;
		this.label.setJlState(Service.CONNECT);
		this.label.setJlTime(time);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		service.setState(Service.CONNECT);
		label.setJlState(Service.CONNECT);
		while(service.getState() != Service.CLOSE) {
			if(time % service.getFlushInterval() == 0) {
				service.setTime(time / 1000.0);
				label.setJlTime(time / 1000.0);
				service.displayState();
			}
			if(time <= service.getConnectTimeout()) {
				service.setState(Service.CONNECT);
				label.setJlState(Service.CONNECT);
			}
			else {
				if(service.getReadTimeout() == -1) {
					service.setState(Service.READ);
					label.setJlState(Service.READ);
				}
				else {
					if(time <= service.getConnectTimeout() + service.getReadTimeout()) {
						service.setState(Service.READ);
						label.setJlState(Service.READ);
					}
					else {
						service.setState(Service.CLOSE);
						label.setJlState(Service.CLOSE);
						SocketServer.running.remove(service);
						Port.endPort(service.getPort());
						AssistantPage.getModelPort().setValueAt("Suspend", Port.getPortID().indexOf(service.getPort()), 1);
					}
				}
			}
			try {
				Thread.sleep(1);
				time += 1;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(SocketServer.running.indexOf(service) != -1) {
			service.setState(Service.CLOSE);
			label.setJlState(Service.CLOSE);
			SocketServer.running.remove(service);
		}
		Port.endPort(service.getPort());
		AssistantPage.getModelPort().setValueAt("Suspend", Port.getPortID().indexOf(service.getPort()), 1);
		service.displayState();
		System.out.println();
	}

}
