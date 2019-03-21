package gui.page.machinepage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
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
import javax.swing.border.Border;

import gui.MainGUI;
import gui.menu.MainButton;
import gui.menu.Menu;
import gui.page.assistpage.AssistantPage;
import machine.Machine;
import servers.socketserver.SocketServer;
import servers.webserver.Message;
import servers.webserver.WebServer;
import service.Service;

public class AddMachineDialog extends JDialog {

	private Machine machine;
	private LinkedList<Service> services;
	private ArrayList<String> serviceNames;
	private ArrayList<Integer> servicePorts;
	private String ms = "";
	
	private JTextField jtfID;
	private JButton jbAdd;
	private JTextArea jtaServices;
	private JButton jbOK;
	
	private JFrame frame;
	private AddMachineDialog dialog;
	
	public AddMachineDialog(JFrame f) {
		// TODO Auto-generated constructor stub
		super(f);
		frame = f;
		dialog = this;
		services = new LinkedList<>();
		serviceNames = new ArrayList();
		servicePorts = new ArrayList();
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		int w = dimension.width;
		int h = dimension.height;
		int x = (w - 700) / 2;
		int y = (h - 400) / 2;
		setBounds(x, y, 700, 400);
		setTitle("Add Machine");
		setResizable(false);
		
		JLabel icon = new JLabel(new ImageIcon("./icons/machine_icon.png"), JLabel.CENTER);
		icon.setBounds(20, 20, 50, 50);
		
		jtfID = new JTextField("");
		jtfID.setBackground(Color.WHITE);
		jtfID.setFont(new Font("ËÎÌו", 0, 20));
		jtfID.setForeground(Color.DARK_GRAY);
		jtfID.setBorder(BorderFactory.createLineBorder(
				new Color(102, 115, 255), 1, true));
		jtfID.setBounds(100, 30, 300, 30);
		
		jbAdd = new JButton("Add Service");
		jbAdd.setForeground(new Color(102, 167, 232));
		jbAdd.setFont(new Font("Consolas", 0, 16));
		jbAdd.setBackground(Color.WHITE);
		jbAdd.setFocusPainted(false);
		jbAdd.setBounds(500, 30, 150, 30);
		jbAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String id = jtfID.getText();
				if(!id.trim().equals("")) {
					jtfID.setEditable(false);
					machine = new Machine(id.trim());
					AddServiceDialog add = new AddServiceDialog(dialog);
					add.setModal(true);
					add.setVisible(true);
				}
			}
		});
		
		jtaServices = new JTextArea("");
		jtaServices.setFont(new Font("ËÎÌו", 0, 18));
		jtaServices.setForeground(Color.DARK_GRAY);
		jtaServices.setMargin(new Insets(15, 10, 15, 10));
		jtaServices.setEditable(false);
		JScrollPane jspDetail = new JScrollPane(jtaServices);
		jspDetail.setBorder(BorderFactory.createLineBorder(
				new Color(106, 167, 232), 1, true));
		jspDetail.setBounds(25, 80, 650, 200);
		
		jbOK = new JButton("Add Machine");
		jbOK.setForeground(Color.WHITE);
		jbOK.setFont(new Font("Consolas", 0, 18));
		jbOK.setBackground(new Color(102, 167, 232));
		jbOK.setFocusPainted(false);
		jbOK.setBounds(250, 300, 200, 40);
		jbOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(machine != null && services.size() > 0) {
					machine.setNumServices(services.size());
					String msg = MainGUI.currentUser + "&,&" + machine.getMachineID() +
							"&,&" + machine.getNumServices() + "\r\n";
					for(Service s: services) {
						msg += s.getServiceName() + "&,&" + s.getPort() + "&,&" + s.getConnectTimeout() + 
								"&,&" + s.getReadTimeout() + "&,&" + s.getFlushInterval() + "\r\n";
					}
					Message response = new WebServer(WebServer.ADD_MACHINE, new Message(msg)).sendResponseMsg();
					switch (response.getResponseMsgType()) {
					case Message.OK:
						SocketServer.machines.add(machine);
						MainGUI.page.removeAll();
						MainGUI.page.repaint();
						Menu.setButton(-1);
						MainGUI.page.add(new HintPage("Add successful!"));
						dialog.dispose();
						break;
					case Message.ERROR:
						JOptionPane.showMessageDialog(dialog, "Machine has already existed!",
								"", JOptionPane.ERROR_MESSAGE);
						dialog.dispose();
						break;
					default:
						break;
					}
				}
				else {
					JOptionPane.showMessageDialog(frame, "Incomplete Information!",
							"", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JPanel p = new JPanel(null);
		p.setBackground(Color.WHITE);
		p.add(icon);
		p.add(jtfID);
		p.add(jbAdd);
		p.add(jspDetail);
		p.add(jbOK);
		this.add(p);
		
	}

	public Machine getMachine() {
		return machine;
	}

	public LinkedList<Service> getServices() {
		return services;
	}

	public ArrayList<String> getServiceNames() {
		return serviceNames;
	}

	public ArrayList<Integer> getServicePorts() {
		return servicePorts;
	}

	public void setJtaServices(String jtaServicesMsg) {
		String sm = this.jtaServices.getText();
		this.jtaServices.setText(sm + jtaServicesMsg);
	}
	
}
