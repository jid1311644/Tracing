package gui.page.machinepage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import gui.MainGUI;
import gui.menu.MainButton;
import gui.menu.Menu;
import gui.page.assistpage.AssistantPage;
import gui.page.runningpage.RunningPage;
import machine.Machine;
import servers.dbserver.DatabaseServer;
import servers.webserver.Message;
import servers.webserver.WebServer;
import service.Service;

public class MachineDetailDialog extends JDialog {
	
	private JFrame frame;
	private Machine machine;
	
	private JTextArea jtaDetail;
	private JButton jbOnOff;
	
	private JDialog dialog;
	
	public MachineDetailDialog(JFrame f, Machine m, int pageID) {
		// TODO Auto-generated constructor stub
		super(f);
		this.frame = f;
		this.machine = m;
		dialog = this;
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		int w = dimension.width;
		int h = dimension.height;
		int x = (w - 700) / 2;
		int y = (h - 350) / 2;
		setBounds(x, y, 700, 350);
		setTitle("Machine Detail");
		setResizable(false);
		
		JLabel jlIcon = new JLabel(new ImageIcon("./icons/machine_icon.png"), JLabel.CENTER);
		jlIcon.setBounds(20, 20, 50, 50);
		
		JLabel jlID = new JLabel(machine.getMachineID());
		jlID.setForeground(Color.DARK_GRAY);
		jlID.setFont(new Font("ËÎÌו", 1, 20));
		jlID.setBounds(100, 35, 300, 20);
		
		jbOnOff = new JButton();
		if(m.isOff()) {
			jbOnOff.setIcon(new ImageIcon("./icons/machine_off.png"));
		}
		else {
			jbOnOff.setIcon(new ImageIcon("./icons/machine_on.png"));
		}
		jbOnOff.setBackground(new Color(252, 252, 252));
		jbOnOff.setBounds(600, 20, 50, 50);
		jbOnOff.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(m.isOff()) {
					Message msg = new WebServer(WebServer.START, 
							new Message(MainGUI.currentUser + "&,&" + machine.getMachineID())).sendResponseMsg();
					switch (msg.getResponseMsgType()) {
					case Message.OK:
						m.setOff(false);
						jbOnOff.setIcon(new ImageIcon("./icons/machine_on.png"));
						MainGUI.page.removeAll();
						MainGUI.page.repaint();
						Menu.setButton(-1);
						MainGUI.page.add(new HintPage("Start successful!"));
						dialog.dispose();
						break;
					case Message.ERROR:
						JOptionPane.showMessageDialog(dialog, "Port is occupied!",
								"", JOptionPane.ERROR_MESSAGE);
						break;
					default:
						break;
					}
				}
				else {
					Message msg = new WebServer(WebServer.END, 
							new Message(MainGUI.currentUser + "&,&" + machine.getMachineID())).sendResponseMsg();
					String s = "";
					switch (msg.getResponseMsgType()) {
					case Message.ERROR:
						s = "Already close!";
						break;
					case Message.OK:
						s = "Close successful!";
						break;
					default:
						break;
					}
					m.setOff(true);
					jbOnOff.setIcon(new ImageIcon("./icons/machine_off.png"));
					MainGUI.page.removeAll();
					MainGUI.page.repaint();
					Menu.setButton(-1);
					MainGUI.page.add(new HintPage(s));
					dialog.dispose();
				}
			}
		});
		
		LinkedList<Service> ss = new DatabaseServer().getServices(
				MainGUI.currentUser, machine.getMachineID());
		String sm = "";
		for(Service s: ss) {
			sm += "  **Service Name:" + s.getServiceName() + "	" + 
					"Port:" + s.getPort() + "\r\n" + 
					"Connect:" + s.getConnectTimeout() + "	" + 
					"Read:" + s.getReadTimeout() + "	" + 
					"Flush:" + s.getFlushInterval() + "\r\n";
		}
		jtaDetail = new JTextArea(sm);
		jtaDetail.setFont(new Font("ËÎÌו", 0, 18));
		jtaDetail.setForeground(Color.DARK_GRAY);
		jtaDetail.setMargin(new Insets(15, 10, 15, 10));
		jtaDetail.setEditable(false);
		JScrollPane jspDetail = new JScrollPane(jtaDetail);
		jspDetail.setBorder(BorderFactory.createLineBorder(
				new Color(106, 167, 232), 1, true));
		jspDetail.setBounds(25, 80, 650, 200);
		
		JPanel p = new JPanel(null);
		p.setBackground(Color.WHITE);
		p.add(jlIcon);
		p.add(jlID);
		p.add(jbOnOff);
		p.add(jspDetail);
		this.add(p);
		
	}

}
