package gui.page.machinepage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import service.Service;

public class AddServiceDialog extends JDialog implements KeyListener {
	
	private Service service;

	private ImageIcon name = new ImageIcon("./icons/service_40.png");
	private ImageIcon port = new ImageIcon("./icons/port_40.png");
	private ImageIcon ct = new ImageIcon("./icons/connect_40.png");
	private ImageIcon rt = new ImageIcon("./icons/read_40.png");
	private ImageIcon flush = new ImageIcon("./icons/flush_40.png");
	
	private JTextField jtfName;
	private JTextField jtfPort;
	private JTextField jtfCT;
	private JTextField jtfRT;
	private JTextField jtfFlush;
	
	private JButton jbOK;
	
	private AddMachineDialog dialog;
	
	public AddServiceDialog(AddMachineDialog d) {
		// TODO Auto-generated constructor stub
		super(d);
		this.dialog = d;
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		int w = dimension.width;
		int h = dimension.height;
		int x = (w - 600) / 2;
		int y = (h - 480) / 2;
		setBounds(x, y, 600, 480);
		setTitle("Add Service");
		setResizable(false);
		
		JLabel iconName = new JLabel(name, JLabel.CENTER);
		iconName.setBounds(20, 20, 200, 40);
		JLabel textName = new JLabel("Service Name", JLabel.CENTER);
		textName.setFont(new Font("Consolas", 0, 16));
		textName.setForeground(Color.DARK_GRAY);
		textName.setBounds(20, 60, 200, 16);
		jtfName = new JTextField("");
		jtfName.setBackground(Color.WHITE);
		jtfName.setFont(new Font("ËÎÌå", 0, 18));
		jtfName.setForeground(Color.DARK_GRAY);
		jtfName.setBorder(BorderFactory.createLineBorder(
				new Color(102, 115, 255), 1, true));
		jtfName.setBounds(200, 27, 300, 40);
		
		JLabel iconPort = new JLabel(port, JLabel.CENTER);
		iconPort.setBounds(20, 90, 200, 40);
		JLabel textPort = new JLabel("Port ID", JLabel.CENTER);
		textPort.setFont(new Font("Consolas", 0, 16));
		textPort.setForeground(Color.DARK_GRAY);
		textPort.setBounds(20, 130, 200, 16);
		jtfPort = new JTextField("");
		jtfPort.setBackground(Color.WHITE);
		jtfPort.setFont(new Font("Consolas", 0, 18));
		jtfPort.setForeground(Color.DARK_GRAY);
		jtfPort.setBorder(BorderFactory.createLineBorder(
				new Color(102, 115, 255), 1, true));
		jtfPort.setBounds(200, 97, 300, 40);
		jtfPort.addKeyListener(this);
		
		JLabel iconCT = new JLabel(ct, JLabel.CENTER);
		iconCT.setBounds(20, 160, 200, 40);
		JLabel textCT = new JLabel("Connect Timeout", JLabel.CENTER);
		textCT.setFont(new Font("Consolas", 0, 16));
		textCT.setForeground(Color.DARK_GRAY);
		textCT.setBounds(20, 200, 200, 16);
		jtfCT = new JTextField("");
		jtfCT.setBackground(Color.WHITE);
		jtfCT.setFont(new Font("Consolas", 0, 18));
		jtfCT.setForeground(Color.DARK_GRAY);
		jtfCT.setBorder(BorderFactory.createLineBorder(
				new Color(102, 115, 255), 1, true));
		jtfCT.setBounds(200, 167, 300, 40);
		jtfCT.addKeyListener(this);
		JLabel msCT = new JLabel("(ms)", JLabel.CENTER);
		msCT.setFont(new Font("Consolas", 0, 18));
		msCT.setForeground(Color.DARK_GRAY);
		msCT.setBounds(500, 176, 40, 18);
		
		JLabel iconRT = new JLabel(rt, JLabel.CENTER);
		iconRT.setBounds(20, 230, 200, 40);
		JLabel textRT = new JLabel("Read Timeout", JLabel.CENTER);
		textRT.setFont(new Font("Consolas", 0, 16));
		textRT.setForeground(Color.DARK_GRAY);
		textRT.setBounds(20, 270, 200, 16);
		jtfRT = new JTextField("");
		jtfRT.setBackground(Color.WHITE);
		jtfRT.setFont(new Font("Consolas", 0, 18));
		jtfRT.setForeground(Color.DARK_GRAY);
		jtfRT.setBorder(BorderFactory.createLineBorder(
				new Color(102, 115, 255), 1, true));
		jtfRT.setBounds(200, 237, 300, 40);
		jtfRT.addKeyListener(this);
		JLabel msRT = new JLabel("(ms)", JLabel.CENTER);
		msRT.setFont(new Font("Consolas", 0, 18));
		msRT.setForeground(Color.DARK_GRAY);
		msRT.setBounds(500, 246, 40, 18);
		JLabel hint = new JLabel("('-xx' means stopping nonautomaticly.)", JLabel.CENTER);
		hint.setFont(new Font("Consolas", 0, 14));
		hint.setForeground(Color.DARK_GRAY);
		hint.setBounds(200, 280, 305, 14);
		
		JLabel iconFlush = new JLabel(flush, JLabel.CENTER);
		iconFlush.setBounds(20, 300, 200, 40);
		JLabel textFlush = new JLabel("Flush Interval", JLabel.CENTER);
		textFlush.setFont(new Font("Consolas", 0, 16));
		textFlush.setForeground(Color.DARK_GRAY);
		textFlush.setBounds(20, 340, 200, 16);
		jtfFlush = new JTextField("");
		jtfFlush.setBackground(Color.WHITE);
		jtfFlush.setFont(new Font("Consolas", 0, 18));
		jtfFlush.setForeground(Color.DARK_GRAY);
		jtfFlush.setBorder(BorderFactory.createLineBorder(
				new Color(102, 115, 255), 1, true));
		jtfFlush.setBounds(200, 307, 300, 40);
		jtfFlush.addKeyListener(this);
		JLabel msFlush = new JLabel("(ms)", JLabel.CENTER);
		msFlush.setFont(new Font("Consolas", 0, 18));
		msFlush.setForeground(Color.DARK_GRAY);
		msFlush.setBounds(500, 316, 40, 18);
		
		jbOK = new JButton("Add Service");
		jbOK.setForeground(Color.WHITE);
		jbOK.setFont(new Font("Consolas", 0, 18));
		jbOK.setBackground(new Color(102, 167, 232));
		jbOK.setFocusPainted(false);
		jbOK.setBounds(200, 380, 200, 40);
		jbOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sname = jtfName.getText();
				String sport = jtfPort.getText();
				String sct = jtfCT.getText();
				String srt = jtfRT.getText();
				String sflush = jtfFlush.getText();
				if(sname.equals("") || sport.equals("") || sct.equals("") ||
						srt.equals("") || sflush.equals("")) {
					
				}
				else {
					int i = dialog.getServiceNames().indexOf(sname);
					int j = dialog.getServicePorts().indexOf(Integer.parseInt(sport));
					if(i == -1 && j == -1) {
						dialog.getServiceNames().add(sname);
						dialog.getServicePorts().add(Integer.parseInt(sport));
						dialog.getServices().add(new Service(sname, dialog.getMachine().getMachineID(), Integer.parseInt(sport), 
								Integer.parseInt(sct), Integer.parseInt(srt), Integer.parseInt(sflush)));
						dialog.setJtaServices("  **Service Name:" + sname + "	" + "Port:" + sport + "\r\n" + 
								"Connect:" + sct + "	" + "Read:" + srt + "	" + "Flush:" + sflush + "\r\n");
						JOptionPane.showMessageDialog(dialog, "Add service successful!",
								"", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(dialog, "Service has already existed!",
								"", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		JPanel p = new JPanel(null);
		p.setBackground(Color.WHITE);
		p.add(iconName);
		p.add(textName);
		p.add(jtfName);
		p.add(iconPort);
		p.add(textPort);
		p.add(jtfPort);
		p.add(iconCT);
		p.add(textCT);
		p.add(jtfCT);
		p.add(msCT);
		p.add(iconRT);
		p.add(textRT);
		p.add(jtfRT);
		p.add(msRT);
		p.add(hint);
		p.add(iconFlush);
		p.add(textFlush);
		p.add(jtfFlush);
		p.add(msFlush);
		p.add(jbOK);
		this.add(p);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(jtfPort) || e.getSource().equals(jtfCT)
				|| e.getSource().equals(jtfFlush)) {
			int keyChar = e.getKeyChar();
			byte[] bs = (e.getKeyChar() + "").getBytes();
			for(int i = 0; i < bs.length; i++) {	//½ûÖ¹ÊäÈëºº×Ö
				if(bs[i] >= (byte)0x81 && bs[i] <= (byte)0xfe) {
					e.consume();
				}
			}
			if(keyChar >= 48 && keyChar <= 57)	{	//Êý×Ö
				
			}
			else {
				e.consume();
			}
		}
		else if(e.getSource().equals(jtfRT)) {
			int keyChar = e.getKeyChar();
			byte[] bs = (e.getKeyChar() + "").getBytes();
			for(int i = 0; i < bs.length; i++) {	//½ûÖ¹ÊäÈëºº×Ö
				if(bs[i] >= (byte)0x81 && bs[i] <= (byte)0xfe) {
					e.consume();
				}
			}
			if(jtfRT.getText().length() == 0) {
				if(keyChar >= 48 && keyChar <= 57 || keyChar == 45)	{	//Êý×Ö»ò¸ººÅ
					
				}
				else {
					e.consume();
				}
			}
			else {
				if(keyChar >= 48 && keyChar <= 57)	{	//Êý×Ö
					
				}
				else {
					e.consume();
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
