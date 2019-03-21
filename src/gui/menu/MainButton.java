package gui.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainButton extends JPanel {

	public final static int RUNNING = 0;
	public final static int MACHINE = 1;
	public final static int ASSIST = 2;

	private ImageIcon home_small = new ImageIcon("./icons/home.png");
	private ImageIcon home_big = new ImageIcon("./icons/home_b.png");
	private ImageIcon machine_small = new ImageIcon("./icons/machine.png");
	private ImageIcon machine_big = new ImageIcon("./icons/machine_b.png");
	private ImageIcon assist_small = new ImageIcon("./icons/assist.png");
	private ImageIcon assist_big = new ImageIcon("./icons/assist_b.png");
	
	private JLabel icon = new JLabel();
	private JLabel text = new JLabel();
	
	private int buttonID;
	private boolean isChoose;
	
	public MainButton(int buttonID, boolean isChoose) {
		// TODO Auto-generated constructor stub
		this.buttonID = buttonID;
		this.isChoose = isChoose;
		
		this.setLayout(null);
		this.setSize(new Dimension(240, 80));
		
		icon.setHorizontalAlignment(JLabel.CENTER);
		icon.setBounds(0, 0, 80, 79);
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setFont(new Font("Consola", 0, 18));
		text.setForeground(Color.DARK_GRAY);
		text.setBounds(80, 0, 140, 79);
		switch (buttonID) {
		case RUNNING:
			text.setText("Running");
			icon.setIcon(home_small);
			if(isChoose) {
				this.setBackground(new Color(209, 232, 255));
			}
			else {
				this.setBackground(new Color(250, 250, 250));
			}
			break;
		case MACHINE:
			text.setText("Machines");
			icon.setIcon(machine_small);
			if(isChoose) {
				this.setBackground(new Color(209, 232, 255));
			}
			else {
				this.setBackground(new Color(250, 250, 250));
			}
			break;
		case ASSIST:
			text.setText("Assistant");
			icon.setIcon(assist_small);
			if(isChoose) {
				this.setBackground(new Color(209, 232, 255));
			}
			else {
				this.setBackground(new Color(250, 250, 250));
			}
			break;
		default:
			break;
		}
		
		JLabel line = new JLabel();
		line.setOpaque(true);
		line.setBackground(Color.GRAY);
		line.setBounds(20, 79, 200, 1);
		
		this.add(icon);
		this.add(text);
		this.add(line);
	}
	
	public boolean isChoose() {
		return isChoose;
	}
	
	public void setChoose(boolean isChoose) {
		this.isChoose = isChoose;
	}
	
	public void setIcon(Icon image) {
		icon.setIcon(image);
	}
	
	
}
