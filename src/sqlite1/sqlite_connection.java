package sqlite1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqlite_connection {
	private static Connection con;
	private void getConnection() throws ClassNotFoundException, SQLException{
		/*
		 * This method Establish a connection between java and sqlite 
		 * */
		Class.forName("org.sqlite.JDBC");
		String url = "jdbc:sqlite:sqlitecreation.db";
		con = DriverManager.getConnection(url);
	}
	
	public void addData(String [] movieDetails) throws SQLException, ClassNotFoundException {
		/*
		 * This method is used to add data in sqlite database
		 * */
		String addQuery = "INSERT INTO movies VALUES(?,?,?,?,?)";
		if(con == null) {
			getConnection();
			String displaQuery = "SELECT * FROM movies";
			Statement st = con.createStatement();
			try{
				st.executeQuery(displaQuery);
			}catch(Exception e){
				createTable();
			}
		}
		PreparedStatement ps = con.prepareStatement(addQuery);
		ps.setString(1, movieDetails[0]);
		ps.setString(2, movieDetails[1]);
		ps.setString(3, movieDetails[2]);
		ps.setString(4, movieDetails[3]);
		ps.setString(5, movieDetails[4]);
		ps.execute();
	}
	private void createTable() throws SQLException{
		/*
		 * This method is used to create table in sqlite database
		 * */
		String create_Table = "CREATE TABLE movies( movies_name varchar(20) PRIMARY KEY,"
				+"actor_name varchar(20),"+"actress_name varchar(20)," 
				+"director_name varcjar(20),"+"year_of_release varchar(5));";
		Statement state = con.createStatement();
		state.execute(create_Table);
	}
	public ResultSet displaMoviesDetails() throws SQLException, ClassNotFoundException {
		/*
		 * This method is used to display all the data without any parameter
		 * */
		String displaQuery = "SELECT * FROM movies";
		if(con == null) {
			getConnection();
		}
		Statement st = con.createStatement();
		ResultSet rs = null;
		try {
			return rs = st.executeQuery(displaQuery);
		}catch(Exception e) {
			
		}
		return rs;
	}
	public ResultSet displaMoviesDetails(String actor_name) throws SQLException, ClassNotFoundException {
		/*
		 * This method is used to display all the data with parameter (actor name)
		 * */
		String displaQuery = "SELECT * FROM movies WHERE actor_name = '"+actor_name+"';";
		if(con == null) {
			getConnection();
		}
		Statement st = con.createStatement();
		ResultSet rs = null;
		try {
			rs = st.executeQuery(displaQuery);
		}catch(Exception e) {
			
		}
		return rs;
	}
	/*public void deleteTable() throws ClassNotFoundException, SQLException{
		String s = "drop table movies";
		if(con == null) {
			getConnection();
		}
		Statement st = con.createStatement();
		st.execute(s);
	}*/
}
