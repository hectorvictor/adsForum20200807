package reposity14082020;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ConnectionManager {
	
	Connection conn = null;
	
	public ConnectionManager() throws SQLException {
		conn = DriverManager.getConnection("jdbc:h2:~/repository","sa","");
		conn.setAutoCommit(false);
	}
	
	public PreparedStatement preparedStatement (String sql) throws SQLException {
		return conn.prepareStatement(sql);
	}
	
	public void commit() throws SQLException {
		conn.commit();
	}
	
	public void roolback() throws SQLException {
		conn.rollback();
	}
}
