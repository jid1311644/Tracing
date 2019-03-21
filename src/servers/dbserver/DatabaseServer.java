package servers.dbserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import machine.Machine;
import service.Service;
import user.User;

public class DatabaseServer {
	
	private static final String URL = "jdbc:mysql://localhost:3306/tracing?serverTimezone=GMT%2B8&useSSL=false";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Jellal20143647";

	public static final int USER_TBALE = 0;
	public static final int MACHINE_TBALE = 1;
	public static final int SERVICE_TBALE = 2;

	
	
	public DatabaseServer() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Success loading MySql Driver!");
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error loading MySql Driver!");
			e.printStackTrace();
		}
	}
	
	public User getUser(String username) {
		String name = "";
		String psw = "";
		try {
			Connection c = DriverManager.getConnection(
					this.URL, this.USERNAME, this.PASSWORD);
			Statement st = c.createStatement();
			String sql = "select * from tracing.user where username='"
					+ username +"'";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				name = rs.getString("username");
				psw = rs.getString("password");
			}
			rs.close();
			st.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new User(name, psw);
	}
	
	public boolean addUser(User user) {
		boolean f = true;
		try {
			Connection c = DriverManager.getConnection(
					this.URL, this.USERNAME, this.PASSWORD);
			String sql = "insert into tracing.user values(?,?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.executeUpdate();
			ps.close();
			c.close();
		}catch (Exception e) {
			// TODO: handle exception
			f = false;
		}
		return f;
	}
	
	public LinkedList<Machine> getMachines(String username) {
		LinkedList<Machine> machines = new LinkedList<>();
		String id = "";
		int n = 0;
		try {
			Connection c = DriverManager.getConnection(
					this.URL, this.USERNAME, this.PASSWORD);
			Statement st = c.createStatement();
			String sql = "select * from tracing.machine where username='"
					+ username +"'";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				id = rs.getString("machine_id");
				n = rs.getInt("num_of_services");
				machines.add(new Machine(id, n));
			}
			rs.close();
			st.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return machines;
	}
	
	public boolean addMachine(String username, Machine m) {
		boolean f = true;
		try {
			Connection c = DriverManager.getConnection(
					this.URL, this.USERNAME, this.PASSWORD);
			String sql = "insert into tracing.machine values(?,?,?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, m.getMachineID());
			ps.setString(2, username);
			ps.setInt(3, m.getNumServices());
			ps.executeUpdate();
			ps.close();
			c.close();
		}catch (Exception e) {
			// TODO: handle exception
			f = false;
		}
		return f;
	}
	
	public boolean deleteMachine(String username, String machineID) {
		boolean f = true;
		Connection c;
		try {
			c = DriverManager.getConnection(
					this.URL, this.USERNAME, this.PASSWORD);
			String sql = "delete from tracing.service where machine_id='" + machineID
					+ "' and username='" + username + "'";
			PreparedStatement ps1 = c.prepareStatement(sql);
			ps1.executeUpdate();
			ps1.close();
			
			sql = "delete from tracing.machine where machine_id='" + machineID
					+ "' and username='" + username + "'";
			PreparedStatement ps2 = c.prepareStatement(sql);
			ps2.executeUpdate();
			ps2.close();
			
			c.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			f = false;
			e.printStackTrace();
		}
		
		return f;
	}
	
	public LinkedList<Service> getServices(String username, String machineID) {
		LinkedList<Service> services = new LinkedList<>();
		String name = "";
		int port = -1;
		int cTimeout = -1;
		int rTimeout = -1;
		int flush = -1;
		try {
			Connection c = DriverManager.getConnection(
					this.URL, this.USERNAME, this.PASSWORD);
			Statement st = c.createStatement();
			String sql = "select * from tracing.service where username='"
					+ username +"' and machine_id='" + machineID + "'";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				name = rs.getString("service_name");
				port = rs.getInt("port");
				cTimeout = rs.getInt("connect_timeout");
				rTimeout = rs.getInt("read_timeout");
				flush = rs.getInt("flush_interval");
				services.add(new Service(name, machineID, port, cTimeout, rTimeout, flush));
			}
			rs.close();
			st.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return services;
	}
	
	public boolean addService(String username, Service s) {
		boolean f = true;
		try {
			Connection c = DriverManager.getConnection(
					this.URL, this.USERNAME, this.PASSWORD);
			String sql = "insert into tracing.service values(?,?,?,?,?,?,?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, s.getServiceName());
			ps.setString(2, s.getMachineID());
			ps.setString(3, username);
			ps.setInt(4, s.getConnectTimeout());
			ps.setInt(5, s.getReadTimeout());
			ps.setInt(6, s.getFlushInterval());
			ps.setInt(7, s.getPort());
			ps.executeUpdate();
			ps.close();
			c.close();
		}catch (Exception e) {
			// TODO: handle exception
			f = false;
		}
		return f;
	}
	
}
