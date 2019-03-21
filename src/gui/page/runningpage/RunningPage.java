package gui.page.runningpage;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import gui.page.machinepage.MachineLabel;
import servers.socketserver.SocketServer;
import service.Service;

public class RunningPage extends JPanel {
	
	public static ArrayList<RunningLabel> labelServices;
	
	private JFrame frame;
	
	public RunningPage(JFrame f) {
		// TODO Auto-generated constructor stub
		this.frame = f;
		
		this.setSize(1060, 800);
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		JLabel pageTitle = new JLabel(new ImageIcon("./icons/run_list.png"), JLabel.CENTER);
		pageTitle.setBounds(70, 20, 300, 60);
		JLabel line = new JLabel();
		line.setOpaque(true);
		line.setBackground(Color.GRAY);
		line.setBounds(20, 89, 1010, 1);
		this.add(pageTitle);
		this.add(line);
		
		for(Iterator<RunningLabel> i = labelServices.iterator(); i.hasNext(); ) {
			RunningLabel l = i.next();
			if(l.getService().getState() == Service.CLOSE) {
				i.remove();
			}
		}
		JPanel p = new JPanel(null);
		p.setPreferredSize(new Dimension(900, labelServices.size() * 170));
		p.setBackground(Color.WHITE);
		int i = 0;
		for(RunningLabel label: labelServices) {
			label.setBounds(60, 170 * (i++), 900, 170);
			p.add(label);
		}

		JScrollPane jsp = new JScrollPane(p);
		jsp.setBounds(0, 90, 1020, 650);
		jsp.setBorder(null);
		this.add(jsp);
		
	}
	
}
