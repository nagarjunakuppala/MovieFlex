package info.vdsi.nags.app.utilities;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConnectionUtil {

	@Autowired
	private static JdbcTemplate jdbcTemplate;
	
	public static Connection getConnection() {

		try {
			 Connection connection = jdbcTemplate.getDataSource().getConnection();
			 return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
