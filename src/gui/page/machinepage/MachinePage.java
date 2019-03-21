package gui.page.machinepage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import gui.MainGUI;
import gui.menu.MainButton;
import gui.menu.Menu;
import gui.page.assistpage.AssistantPage;
import machine.Machine;
import servers.dbserver.DatabaseServer;
import servers.socketserver.Port;
import servers.socketserver.SocketServer;
import servers.webserver.Message;
import servers.webserver.WebServer;
import service.Service;

public class MachinePage extends JPanel implements MouseListener {
	
	private JButton jbAddMachine;
	private MachineLabel[] labelMachines;
	
	private JFrame frame;
	
	public MachinePage(JFrame f) {
		// TODO Auto-generated constructor stub
		this.frame = f;
		
		this.setSize(1060, 800);
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		JLabel jlTitle = new JLabel(new ImageIcon("./icons/my_machine.png"), JLabel.CENTER);
		jlTitle.setBounds(70, 0, 300, 70);
		
		jbAddMachine = new JButton(new ImageIcon("./icons/add_machine.png"));
		jbAddMachine.setBackground(new Color(250, 250, 250));
		jbAddMachine.setFocusPainted(false);
		jbAddMachine.setBounds(390, 25, 30, 30);
		jbAddMachine.addMouseListener(this);

		this.add(jlTitle);
		this.add(jbAddMachine);
		
		for(Machine mm: SocketServer.machines) {
			if(SocketServer.isRunning(mm)) {
				mm.setOff(false);
			}
			else {
				mm.setOff(true);
			}
		}
		labelMachines = new MachineLabel[SocketServer.machines.size()];
		JPanel p = new JPanel(null);
		p.setPreferredSize(new Dimension(960, (SocketServer.machines.size() + 1) * 125));
		p.setBackground(Color.WHITE);
		for(int i = 0; i < SocketServer.machines.size(); i++) {
			Machine m = SocketServer.machines.get(i);
			labelMachines[i] = new MachineLabel(m);
			labelMachines[i].setBounds(80 + 480 * (i % 2), 250 * (i / 2), 400, 200);
			labelMachines[i].getJlOn().addMouseListener(this);
			labelMachines[i].getJlOff().addMouseListener(this);
			labelMachines[i].getJlMsg().addMouseListener(this);
			labelMachines[i].getJlDelete().addMouseListener(this);
			p.add(labelMachines[i]);
		}
		JScrollPane jsp = new JScrollPane(p);
		jsp.setBounds(0, 90, 1020, 650);
		jsp.setBorder(null);
		this.add(jsp);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(jbAddMachine)) {
			AddMachineDialog add = new AddMachineDialog(frame);
			add.setModal(true);
			add.setVisible(true);
		}
		else {
			for(int i = 0; i < SocketServer.machines.size(); i++) {
				if(e.getSource().equals(labelMachines[i].getJlOn())) {
					SocketServer.machines.get(i).setOff(false);
					labelMachines[i].getJlOn().setVisible(false);
					labelMachines[i].getJlOff().setVisible(true);
					labelMachines[i].getJlMachineState().setText("Running...");
					break;
				}
				else if(e.getSource().equals(labelMachines[i].getJlOff())) {
					SocketServer.machines.get(i).setOff(true);
					labelMachines[i].getJlOn().setVisible(true);
					labelMachines[i].getJlOff().setVisible(false);
					labelMachines[i].getJlMachineState().setText("Sleep");
					break;
				}
				else if(e.getSource().equals(labelMachines[i].getJlMsg())) {
					MachineDetailDialog detail = new MachineDetailDialog(frame, 
							SocketServer.machines.get(i), MainButton.MACHINE);
					detail.setModal(true);
					detail.setVisible(true);
					break;
				}
				else if(e.getSource().equals(labelMachines[i].getJlDelete())) {
					Machine m = SocketServer.machines.get(i);
					int n = JOptionPane.showConfirmDialog(this, "Confirm delete " + m.getMachineID() + " ?"
							, "", JOptionPane.YES_NO_OPTION);
					if(n == 0) {
						Message msg = new WebServer(WebServer.DELETE_MACHINE, new Message(
								MainGUI.currentUser + "&,&" + m.getMachineID())).sendResponseMsg();
						switch (msg.getResponseMsgType()) {
						case Message.OK:
							for(Iterator<Machine> im = SocketServer.machines.iterator(); im.hasNext(); ) {
								if(im.next().getMachineID().equals(m.getMachineID())) {
									im.remove();
									break;
								}
							}
							MainGUI.page.removeAll();
							MainGUI.page.repaint();
							Menu.setButton(-1);
							MainGUI.page.add(new HintPage("Delete successful!"));
							break;
						default:
							break;
						}
					}
					break;
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
