package gui.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gui.MainGUI;
import gui.page.assistpage.AssistantPage;
import gui.page.machinepage.MachinePage;
import gui.page.runningpage.RunningPage;
import gui.user.SignInUp;
import servers.socketserver.SocketServer;

public class Menu extends JPanel implements MouseListener {

	private ImageIcon home_small = new ImageIcon("./icons/home.png");
	private ImageIcon home_big = new ImageIcon("./icons/home_b.png");
	private ImageIcon machine_small = new ImageIcon("./icons/machine.png");
	private ImageIcon machine_big = new ImageIcon("./icons/machine_b.png");
	private ImageIcon assist_small = new ImageIcon("./icons/assist.png");
	private ImageIcon assist_big = new ImageIcon("./icons/assist_b.png");
	private ImageIcon logout_small = new ImageIcon("./icons/logout.png");
	private ImageIcon logout_big = new ImageIcon("./icons/logout_b.png");
	
	private static MainButton btnRunning;
	private static MainButton btnMachine;
	private static MainButton btnAssist;
	private JLabel jlLogout;
	
	private int pageID;
	private JFrame frame;
	
	public Menu(int pageID, JFrame f) {
		// TODO Auto-generated constructor stub
		this.pageID = pageID;
		this.frame = f;
		
		switch (pageID) {
		case MainButton.RUNNING:
			btnRunning = new MainButton(MainButton.RUNNING, true);
			btnMachine = new MainButton(MainButton.MACHINE, false);
			btnAssist = new MainButton(MainButton.ASSIST, false);
			break;
		case MainButton.MACHINE:
			btnRunning = new MainButton(MainButton.RUNNING, false);
			btnMachine = new MainButton(MainButton.MACHINE, true);
			btnAssist = new MainButton(MainButton.ASSIST, false);
			break;
		case MainButton.ASSIST:
			btnRunning = new MainButton(MainButton.RUNNING, false);
			btnMachine = new MainButton(MainButton.MACHINE, false);
			btnAssist = new MainButton(MainButton.ASSIST, true);
			break;
		default:
			break;
		}
		
		this.setSize(new Dimension(241, 800));
		this.setLayout(null);
		this.setBackground(new Color(250, 250, 250));
		
		btnRunning.setBounds(0, 0, 240, 80);
		btnRunning.addMouseListener(this);
		btnMachine.setBounds(0, 80, 240, 80);
		btnMachine.addMouseListener(this);
		btnAssist.setBounds(0, 160, 240, 80);
		btnAssist.addMouseListener(this);
		
		JLabel jlIcon = new JLabel(new ImageIcon("./icons/menu_icon.png"), JLabel.CENTER);
		jlIcon.setBounds(0, 500, 240, 150);
		
		jlLogout = new JLabel(logout_small, JLabel.CENTER);
		jlLogout.setBounds(20, 690, 60, 60);
		jlLogout.addMouseListener(this);
		
		JLabel line = new JLabel();
		line.setOpaque(true);
		line.setBackground(Color.GRAY);
		line.setBounds(240, 20, 1, 725);
		
		this.add(btnRunning);
		this.add(btnMachine);
		this.add(btnAssist);
		this.add(jlIcon);
		this.add(jlLogout);
		this.add(line);
		
	}

	public static MainButton getBtnRunning() {
		return btnRunning;
	}

	public static void setBtnRunning(MainButton btnRun) {
		Menu.btnRunning = btnRun;
	}
	
	public static MainButton getBtnMachine() {
		return btnMachine;
	}

	public static void setBtnMachine(MainButton btnMachine) {
		Menu.btnMachine = btnMachine;
	}

	public static MainButton getBtnAssist() {
		return btnAssist;
	}

	public static void setBtnAssist(MainButton btnAssist) {
		Menu.btnAssist = btnAssist;
	}
	
	public static void setButton(int btnID) {
		switch (btnID) {
		case MainButton.RUNNING:
			btnRunning.setBackground(new Color(209, 232, 255));
			btnRunning.setChoose(true);
			btnMachine.setBackground(new Color(250, 250, 250));
			btnMachine.setChoose(false);
			btnAssist.setBackground(new Color(250, 250, 250));
			btnAssist.setChoose(false);
			break;
		case MainButton.MACHINE:
			btnRunning.setBackground(new Color(250, 250, 250));
			btnRunning.setChoose(false);
			btnMachine.setBackground(new Color(209, 232, 255));
			btnMachine.setChoose(true);
			btnAssist.setBackground(new Color(250, 250, 250));
			btnAssist.setChoose(false);
			break;
		case MainButton.ASSIST:
			btnRunning.setBackground(new Color(250, 250, 250));
			btnRunning.setChoose(false);
			btnMachine.setBackground(new Color(250, 250, 250));
			btnMachine.setChoose(false);
			btnAssist.setBackground(new Color(209, 232, 255));
			btnAssist.setChoose(true);
			break;
		default:
			btnRunning.setBackground(new Color(250, 250, 250));
			btnRunning.setChoose(false);
			btnMachine.setBackground(new Color(250, 250, 250));
			btnMachine.setChoose(false);
			btnAssist.setBackground(new Color(250, 250, 250));
			btnAssist.setChoose(false);
			break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnRunning)) {
			if(!btnRunning.isChoose()) {
				setButton(MainButton.RUNNING);
				
				MainGUI.page.removeAll();
				MainGUI.page.repaint();
				MainGUI.page.add(new RunningPage(frame));
			}
		}
		else if(e.getSource().equals(btnMachine)) {
			if(!btnMachine.isChoose()) {
				setButton(MainButton.MACHINE);
				
				MainGUI.page.removeAll();
				MainGUI.page.repaint();
				MainGUI.page.add(new MachinePage(frame));
			}
		}
		else if(e.getSource().equals(btnAssist)) {
			if(!btnAssist.isChoose()) {
				setButton(MainButton.ASSIST);
				
				MainGUI.page.removeAll();
				MainGUI.page.repaint();
				MainGUI.page.add(new AssistantPage());
			}
		}
		else if(e.getSource().equals(jlLogout)) {
			if(SocketServer.running.size() > 0) {
				int n = JOptionPane.showConfirmDialog(frame, "Something is running. Logout now?"
						, "", JOptionPane.YES_NO_OPTION);
				if(n == 0) {
					SignInUp gui = new SignInUp();
					gui.setVisible(true);
					frame.dispose();
				}
			}
			else {
				int n = JOptionPane.showConfirmDialog(frame, "Logout now?"
						, "", JOptionPane.YES_NO_OPTION);
				if(n == 0) {
					SignInUp gui = new SignInUp();
					gui.setVisible(true);
					frame.dispose();
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnRunning)) {
			btnRunning.setIcon(home_small);
			if(!btnRunning.isChoose()) {
				btnRunning.setBackground(new Color(209, 232, 255));
			}
		}
		else if(e.getSource().equals(btnMachine)) {
			btnMachine.setIcon(machine_small);
			if(!btnMachine.isChoose()) {
				btnMachine.setBackground(new Color(209, 232, 255));
			}
		}
		else if(e.getSource().equals(btnAssist)) {
			btnAssist.setIcon(assist_small);
			if(!btnAssist.isChoose()) {
				btnAssist.setBackground(new Color(209, 232, 255));
			}
		}
		else if(e.getSource().equals(jlLogout)) {
			jlLogout.setIcon(logout_small);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnRunning)) {
			btnRunning.setIcon(home_big);
			if(!btnRunning.isChoose()) {
				btnRunning.setBackground(new Color(229, 243, 251));
			}
		}
		else if(e.getSource().equals(btnMachine)) {
			btnMachine.setIcon(machine_big);
			if(!btnMachine.isChoose()) {
				btnMachine.setBackground(new Color(229, 243, 251));
			}
		}
		else if(e.getSource().equals(btnAssist)) {
			btnAssist.setIcon(assist_big);
			if(!btnAssist.isChoose()) {
				btnAssist.setBackground(new Color(229, 243, 251));
			}
		}
		else if(e.getSource().equals(jlLogout)) {
			jlLogout.setIcon(logout_big);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnRunning)) {
			btnRunning.setIcon(home_big);
			if(!btnRunning.isChoose()) {
				btnRunning.setBackground(new Color(229, 243, 251));
			}
		}
		else if(e.getSource().equals(btnMachine)) {
			btnMachine.setIcon(machine_big);
			if(!btnMachine.isChoose()) {
				btnMachine.setBackground(new Color(229, 243, 251));
			}
		}
		else if(e.getSource().equals(btnAssist)) {
			btnAssist.setIcon(assist_big);
			if(!btnAssist.isChoose()) {
				btnAssist.setBackground(new Color(229, 243, 251));
			}
		}
		else if(e.getSource().equals(jlLogout)) {
			jlLogout.setIcon(logout_big);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnRunning)) {
			btnRunning.setIcon(home_small);
			if(!btnRunning.isChoose()) {
				btnRunning.setBackground(new Color(250, 250, 250));
			}
		}
		else if(e.getSource().equals(btnMachine)) {
			btnMachine.setIcon(machine_small);
			if(!btnMachine.isChoose()) {
				btnMachine.setBackground(new Color(250, 250, 250));
			}
		}
		else if(e.getSource().equals(btnAssist)) {
			btnAssist.setIcon(assist_small);
			if(!btnAssist.isChoose()) {
				btnAssist.setBackground(new Color(250, 250, 250));
			}
		}
		else if(e.getSource().equals(jlLogout)) {
			jlLogout.setIcon(logout_small);
		}
	}

}
