package sys_practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AWS {
	public static Connection getRemoteConnection() throws SQLException{
	   try {
	      Class.forName("com.mysql.cj.jdbc.Driver");
	      String dbName = "ebdb";
	      String userName = "admin";
	      String password = "AraikenR4!";
	      String hostname = "syspractice.crew3xxz5di7.ap-northeast-1.rds.amazonaws.com";
	      String port = "3306";
	      String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName +
	    		  "?user=" + userName + "&password=" + password;
	      Connection con = DriverManager.getConnection(jdbcUrl);
	      //con.close();
	      
	      return con;
	    }
	    catch (ClassNotFoundException e) {
	    	//logger.warn(e.toString());
	    }
	   return null;
	}
}
