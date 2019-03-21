package gui.page.machinepage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import machine.Machine;

public class MachineLabel extends JPanel {
	
	private ImageIcon on = new ImageIcon("./icons/machine_start.png");
	private ImageIcon off = new ImageIcon("./icons/machine_stop.png");
	private ImageIcon msg = new ImageIcon("./icons/machine_msg.png");
	private ImageIcon delete = new ImageIcon("./icons/machine_delete.png");
	
	private JLabel jlMachineState;
	private JLabel jlOn;
	private JLabel jlOff;
	private JLabel jlMsg;
	private JLabel jlDelete;
	
	private Machine machine;
	
	public MachineLabel(Machine m) {
		// TODO Auto-generated constructor stub
		this.machine = m;
		
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		JLabel jlBack = new JLabel(new ImageIcon("./icons/machine_back.png"), JLabel.CENTER);
		jlBack.setBounds(0, 0, 400, 200);
		JLabel jlIcon = new JLabel(new ImageIcon("./icons/machine_icon.png"), JLabel.CENTER);
		jlIcon.setBounds(20, 20, 50, 50);
		JLabel jlMachineID = new JLabel(machine.getMachineID());
		jlMachineID.setForeground(Color.DARK_GRAY);
		jlMachineID.setFont(new Font("ËÎÌו", 0, 18));
		jlMachineID.setBounds(80, 35, 300, 20);
		JLabel jlState = new JLabel(new ImageIcon("./icons/machine_state.png"), JLabel.CENTER);
		jlState.setBounds(20, 80, 50, 50);
		String s = "Sleep";
		if(!machine.isOff()) {
			s = "Running...";
		}
		jlMachineState = new JLabel(s);
		jlMachineState.setForeground(Color.DARK_GRAY);
		jlMachineState.setFont(new Font("Consolas", 0, 18));
		jlMachineState.setBounds(80, 95, 300, 20);
		
		jlOn = new JLabel(on, JLabel.CENTER);
		jlOn.setBounds(40, 140, 40, 40);
		jlOff = new JLabel(off, JLabel.CENTER);
		jlOff.setBounds(40, 140, 40, 40);
		jlMsg = new JLabel(msg, JLabel.CENTER);
		jlMsg.setBounds(260, 140, 40, 40);
		jlDelete = new JLabel(delete, JLabel.CENTER);
		jlDelete.setBounds(320, 140, 40, 40);
		jlOff.setVisible(false);
		jlOn.setVisible(false);
//		if(machine.isOff()) {
//			jlOn.setVisible(true);
//			jlOff.setVisible(false);
//		}
//		else {
//			jlOn.setVisible(false);
//			jlOff.setVisible(true);
//		}
		jlMsg.setVisible(true);
		jlDelete.setVisible(true);
		
		this.add(jlIcon);
		this.add(jlMachineID);
		this.add(jlState);
		this.add(jlMachineState);
		this.add(jlOn);
		this.add(jlOff);
		this.add(jlMsg);
		this.add(jlDelete);
		this.add(jlBack);
		
	}

	public JLabel getJlMachineState() {
		return jlMachineState;
	}

	public JLabel getJlOn() {
		return jlOn;
	}

	public JLabel getJlOff() {
		return jlOff;
	}

	public JLabel getJlMsg() {
		return jlMsg;
	}

	public JLabel getJlDelete() {
		return jlDelete;
	}

}
