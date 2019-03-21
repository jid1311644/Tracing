package gui.page.assistpage;

import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import servers.socketserver.Cache;
import servers.socketserver.Port;
import service.Service;

public class AssistantPage extends JPanel {

	private ImageIcon port = new ImageIcon("./icons/port_assist.png");
	private ImageIcon cache = new ImageIcon("./icons/cache_assist.png");
	
	private JTable jtPort;
	private JTable jtCache;
	public static MyTableModel modelPort;
	private MyTableModel modelCache;
	
	public AssistantPage() {
		// TODO Auto-generated constructor stub
		this.setSize(1060, 800);
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		JLabel portTitle = new JLabel(port, JLabel.CENTER);
		portTitle.setBounds(0, 20, 400, 60);
		String[][] rowp = new String[20][2];
		String[] columnp = {"PID", "State"};
		modelPort = new MyTableModel(rowp, columnp);
		Vector<Object> dataPort = getPortData();
		Vector<Object> namePort = new Vector<>();
		namePort.add(columnp[0]);
		namePort.add(columnp[1]);
		modelPort.setDataVector(dataPort, namePort);
		jtPort = new JTable();
		jtPort.setModel(modelPort);
		jtPort.setFont(new Font("Consolas", 0, 14));
		jtPort.setForeground(Color.DARK_GRAY);
		jtPort.setRowHeight(25);
		jtPort.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtPort.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtPort.getColumnModel().getColumn(1).setPreferredWidth(200);
		JScrollPane jspPort = new JScrollPane();
		jspPort.setViewportView(jtPort);
		jspPort.setBounds(70, 110, 300, 600);
		
		JLabel line = new JLabel();
		line.setOpaque(true);
		line.setBackground(Color.GRAY);
		line.setBounds(420, 50, 1, 670);
		
		JLabel cacheTitle = new JLabel(cache, JLabel.CENTER);
		cacheTitle.setBounds(421, 20, 600, 60);
		String[][] rowc = new String[20][2];
		String[] columnc = {"Machine", "Service"};
		modelCache = new MyTableModel(rowc, columnc);
		Vector<Object> dataCache = getCacheData();
		Vector<Object> nameCache = new Vector<>();
		nameCache.add(columnc[0]);
		nameCache.add(columnc[1]);
		modelCache.setDataVector(dataCache, nameCache);
		jtCache = new JTable();
		jtCache.setModel(modelCache);
		jtCache.setFont(new Font("Consolas", 0, 14));
		jtCache.setForeground(Color.DARK_GRAY);
		jtCache.setRowHeight(25);
		jtCache.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtCache.getColumnModel().getColumn(0).setPreferredWidth(250);
		jtCache.getColumnModel().getColumn(1).setPreferredWidth(250);
		JScrollPane jspCache = new JScrollPane();
		jspCache.setViewportView(jtCache);
		jspCache.setBounds(470, 110, 500, 600);
		
		this.add(portTitle);
		this.add(jspPort);
		this.add(line);
		this.add(cacheTitle);
		this.add(jspCache);
		
	}
	
	private Vector<Object> getCacheData(){
		Vector<Object> data = new Vector<>();
		int size = Cache.cacheService.size();
		for(Service s: Cache.cacheService) {
			Vector<Object> vectorRow = new Vector<>();
			vectorRow.add(s.getMachineID());
			vectorRow.add(s.getServiceName());
			data.add(vectorRow);
		}

		if(size < 23) {
			for(int i = 0; i < 23 - size; i++) {
				Vector<Object> vectorRow = new Vector<>();
				vectorRow.add("");
				vectorRow.add("");
				data.add(vectorRow);
			}
		}
		
		return data;
	}
	
	private Vector<Object> getPortData() {
		Vector<Object> data = new Vector<>();
		int size = Port.getPortID().size();
		String[] ids = new String[size];
		String[] states = new String[size];
		int i = 0;
		for(int id: Port.getPortID()) {
			ids[i++] = id + "";
		}
		i = 0;
		for(boolean s: Port.getPortState()) {
			if(s) {
				states[i++] = "Running";
			}
			else {
				states[i++] = "Suspend";
			}
		}
		
		for(i = 0; i < size; i++) {
			Vector<Object> vectorRow = new Vector<>();
			vectorRow.add(ids[i]);
			vectorRow.add(states[i]);
			data.add(vectorRow);
		}
		if(size < 23) {
			for(i = 0; i < 23 - size; i++) {
				Vector<Object> vectorRow = new Vector<>();
				vectorRow.add("");
				vectorRow.add("");
				data.add(vectorRow);
			}
		}
		
		return data;
		
	}
	
	public static MyTableModel getModelPort() {
		return modelPort;
	}

	public class MyTableModel extends DefaultTableModel {
		public MyTableModel(Object[][] data, Object[] columnNames) {
			// TODO Auto-generated constructor stub
			super(data, columnNames);
		}
		//重写DefaultTableModel的isCellEditable方法，作用是单元格只可以选中不可以编辑
		public boolean isCellEditable(int row, int column){
			return false;
		}
		
	}
	
}
