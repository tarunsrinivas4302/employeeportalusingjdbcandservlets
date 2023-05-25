package com.empportal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class employeeDAO {

	private static String retervial = "Select * from employeeinfo1";
	private static String insert = "insert into employeeinfo1 values(?,?,?,?,?,?,?,?)";
	private static String login = "select * from employeeinfo1 where username = ? and password = ?";
	private static String delete = "delete from employeeinfo1 where id=?";
	private static String elebyid = "select * from employeeinfo1 where id = ? ";

	// DataBase Connection
	public static Connection getDBConnection() throws ClassNotFoundException, SQLException {
		Connection con = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "oracle1");
		return con;
	}

	// Registration Servlet Logic

	public static int saveRegDetails(EmployeeDTO emp) {
		int status = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = employeeDAO.getDBConnection();
			pstmt = con.prepareStatement(insert);

			pstmt.setInt(1, emp.getId1());
			pstmt.setString(2, emp.getFirstname());
			pstmt.setString(3, emp.getLastname());
			pstmt.setInt(4, emp.getAge1());
			pstmt.setString(5, emp.getFirstname());
			pstmt.setString(6, emp.getPassword());
			pstmt.setLong(7, emp.getPhonenumber());
			pstmt.setString(8, emp.getCountry());

			status = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return status;
	}

	// View Servlet Logic

	public static List<EmployeeDTO> getEmployeeDetails() throws ClassNotFoundException, SQLException {
		List<EmployeeDTO> employeeList = new ArrayList<EmployeeDTO>();
		Connection con = employeeDAO.getDBConnection();
		PreparedStatement pstmt = con.prepareStatement(retervial);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			EmployeeDTO emp = new EmployeeDTO();
			emp.setId1(rs.getInt(1));
			emp.setFirstname(rs.getString(2));
			emp.setLastname(rs.getString(3));
			emp.setAge1(rs.getInt(4));
			emp.setUsername(rs.getString(5));
			emp.setPassword(rs.getString(6));
			emp.setPhonenumber(rs.getLong(7));
			emp.setCountry(rs.getString(8));

			employeeList.add(emp);

		}
		return employeeList;
	}

//	Login Servlet Logic

	public static boolean validate(EmployeeDTO emp) throws ClassNotFoundException, SQLException {
		boolean status = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getDBConnection();
			String query = "SELECT * FROM employeeinfo1 WHERE LOWER(username) = LOWER(?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, emp.getUsername());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String storedUsername = rs.getString("username");
				String storedPassword = rs.getString("password");
				String providedUsername = emp.getUsername();
				String providedPassword = emp.getPassword();

				if (storedUsername.equalsIgnoreCase(providedUsername) && storedPassword.equals(providedPassword)) {
					status = true;
					break;
				}
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}

		return status;
	}

//	get employee details by id

	public static EmployeeDTO getEmployeeDetailsById(int id) {
		EmployeeDTO emp = new EmployeeDTO();
		Connection con = null;
		try {
			con = getDBConnection();
			PreparedStatement pstmt = con.prepareStatement(elebyid);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				emp.setId1(rs.getInt(1));
				emp.setFirstname(rs.getString(2));
				emp.setLastname(rs.getString(3));
				emp.setAge1(rs.getInt(4));
				emp.setUsername(rs.getString(5));
				emp.setPassword(rs.getString(6));
				emp.setPhonenumber(rs.getLong(7));
				emp.setCountry(rs.getString(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}

//	update employee details

	public static int updateAndSaveEmployeeDetails(EmployeeDTO emp) throws SQLException {
		int rowsAffected = 0;
		Connection con = null;
		try {
			con = getDBConnection();
			String Sql = "update employeeinfo1 set id=?,firstname=?,lastname=?,age=?,username=?,password=?,PHONENUMBER=?,country=? where id=?";
			PreparedStatement statement = con.prepareStatement(Sql);
			statement.setInt(1, emp.getId1());
			statement.setString(2, emp.getFirstname());
			statement.setString(3, emp.getLastname());
			statement.setInt(4, emp.getAge1());
			statement.setString(5, emp.getUsername());
			statement.setString(6, emp.getPassword());
			statement.setLong(7, emp.getPhonenumber());
			statement.setString(8, emp.getCountry());
			statement.setInt(9, emp.getId1());

			rowsAffected = statement.executeUpdate();

			if (rowsAffected == 0) {

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return rowsAffected;
	}

//Delete Servlet..

	public static int deleteEmployeeById(int id) throws SQLException, ClassNotFoundException {
		int status = 0;
		Connection con = null;
		con = getDBConnection();
		PreparedStatement ps = con.prepareStatement(delete);
		ps.setInt(1, id);
		status = ps.executeUpdate();
		return status;
	}




}