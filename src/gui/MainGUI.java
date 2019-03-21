package gui;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gui.menu.MainButton;
import gui.menu.Menu;
import gui.page.assistpage.AssistantPage;
import gui.page.machinepage.HintPage;
import gui.page.machinepage.MachinePage;
import gui.page.runningpage.RunningLabel;
import gui.page.runningpage.RunningPage;
import gui.user.SignInUp;
import servers.socketserver.SocketServer;

public class MainGUI extends JFrame {
	
	public static String currentUser;
	public static JPanel page;
	
	public MainGUI() {
		// TODO Auto-generated constructor stub
		super();
		setTitle("分布式监控管理系统");
		setSize(1300, 800);
		int w = Toolkit.getDefaultToolkit().getScreenSize().width;
		int h = Toolkit.getDefaultToolkit().getScreenSize().height;
		int x = (w - 1300) / 2;
		int y = (h - 800) / 2;
		setLocation(x, y);
		setResizable(false);
		
		Menu menu = new Menu(MainButton.RUNNING, this);
		menu.setBounds(0, 0, 241, 800);
		
		new SocketServer();
		RunningPage.labelServices = new ArrayList<>();
		page = new JPanel(null);
		page.setBounds(241, 0, 1060, 800);
		page.setBackground(Color.WHITE);
		page.add(new RunningPage(this));
		
		JPanel main = new JPanel(null);
		main.setBackground(Color.WHITE);
		main.add(menu);
		main.add(page);
		this.getContentPane().add(main);
		
	}
	
	   protected void processWindowEvent(final WindowEvent e) {
	        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
	        	if(SocketServer.running.size() > 0) {
					int n = JOptionPane.showConfirmDialog(this, "Something is running. Logout now?"
							, "", JOptionPane.YES_NO_OPTION);
					if(n == 0) {
						System.exit(0);
					}
				}
				else {
					int n = JOptionPane.showConfirmDialog(this, "Exit now?"
							, "", JOptionPane.YES_NO_OPTION);
					if(n == 0) {
						System.exit(0);
					}
				}
	        }
	        else {
	        	super.processWindowEvent(e);
	        }
	    }
	
	public static void main(String[] args) {
		new SignInUp().setVisible(true);
	}

}
