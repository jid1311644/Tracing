package gui.page.runningpage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.sql.rowset.Joinable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.menu.MainButton;
import gui.page.machinepage.MachineDetailDialog;
import machine.Machine;
import servers.socketserver.SocketServer;
import service.Service;

public class RunningLabel extends JPanel {
	
	private ImageIcon iservice = new ImageIcon("./icons/service.png");
	private ImageIcon imachine = new ImageIcon("./icons/machine_icon.png");
	private ImageIcon idetail = new ImageIcon("./icons/detail.png");
	private ImageIcon istate = new ImageIcon("./icons/state.png");
	private ImageIcon itime = new ImageIcon("./icons/time.png");
	private ImageIcon iport = new ImageIcon("./icons/port.png");
	
	private JButton jbDetail;
	private JLabel jlState;
	private JLabel jlTime;
	private JFrame frame;
	
	private Service service;
	private Machine machine;
	
	public RunningLabel(JFrame f, Service s) {
		// TODO Auto-generated constructor stub
		this.frame = f;
		this.service = s;
		
		this.setSize(900, 170);
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		JLabel iconService = new JLabel(iservice, JLabel.CENTER);
		iconService.setBounds(20, 30, 50, 50);
		JLabel nameService = new JLabel(service.getServiceName());
		nameService.setFont(new Font("ËÎÌו", 0, 20));
		nameService.setForeground(Color.DARK_GRAY);
		nameService.setBounds(80, 45, 300, 20);
		
		JLabel iconMachine = new JLabel(imachine, JLabel.CENTER);
		iconMachine.setBounds(450, 30, 50, 50);
		JLabel idMachine = new JLabel(service.getMachineID());
		idMachine.setFont(new Font("ËÎÌו", 0, 20));
		idMachine.setForeground(Color.DARK_GRAY);
		idMachine.setBounds(510, 45, 300, 20);
		
		for(Machine m: SocketServer.machines) {
			if(m.getMachineID().equals(service.getMachineID())) {
				machine = m;
				break;
			}
		}
		jbDetail = new JButton(new ImageIcon("./icons/detail.png"));
		jbDetail.setBackground(new Color(252, 252, 252));
		jbDetail.setBounds(800, 35, 40, 40);
		jbDetail.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MachineDetailDialog detail = new MachineDetailDialog(frame, machine, MainButton.RUNNING);
				detail.setModal(true);
				detail.setVisible(true);
			}
		});
		
		JLabel iconState = new JLabel(istate, JLabel.CENTER);
		iconState.setBounds(100, 110, 30, 30);
		String st = "";
		switch (service.getState()) {
		case Service.CLOSE:
			st = "Suspend";
			break;
		case Service.CONNECT:
			st = "Connect...";
			break;
		case Service.READ:
			st = "Read...";
			break;
		default:
			break;
		}
		jlState = new JLabel(st);
		jlState.setFont(new Font("Consolas", 0, 16));
		jlState.setForeground(Color.DARK_GRAY);
		jlState.setBounds(140, 117, 200, 16);
		
		JLabel iconTime = new JLabel(itime, JLabel.CENTER);
		iconTime.setBounds(350, 110, 30, 30);
		jlTime = new JLabel(service.getTime() + "s");
		jlTime.setFont(new Font("Consolas", 0, 16));
		jlTime.setForeground(Color.DARK_GRAY);
		jlTime.setBounds(390, 117, 200, 16);
		
		JLabel iconPort = new JLabel(iport, JLabel.CENTER);
		iconPort.setBounds(600, 110, 30, 30);
		JLabel jlPort = new JLabel(service.getPort() + "");
		jlPort.setFont(new Font("Consolas", 0, 16));
		jlPort.setForeground(Color.DARK_GRAY);
		jlPort.setBounds(640, 117, 200, 16);
		
		JLabel line = new JLabel();
		line.setOpaque(true);
		line.setBackground(Color.GRAY);
		line.setBounds(0, 167, 900, 1);

		this.add(iconService);
		this.add(nameService);
		this.add(iconMachine);
		this.add(idMachine);
		this.add(jbDetail);
		this.add(iconState);
		this.add(jlState);
		this.add(iconTime);
		this.add(jlTime);
		this.add(iconPort);
		this.add(jlPort);
		this.add(line);
		
	}

	public void setJlState(int state) {
		String st = "";
		switch (service.getState()) {
		case Service.CLOSE:
			st = "Suspend";
			break;
		case Service.CONNECT:
			st = "Connect...";
			break;
		case Service.READ:
			st = "Read...";
			break;
		default:
			break;
		}
		this.jlState.setText(st);
	}

	public void setJlTime(double time) {
		this.jlTime.setText(time + "s");
	}

	public Service getService() {
		return service;
	}

	
	
}
