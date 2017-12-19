package info.vdsi.nags.app.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;

import info.vdsi.nags.app.beans.UserLoginData;

public class GetUserData {

public String getUserName(JdbcTemplate jdbcTemplate, String userName) {
		
	try {
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		PreparedStatement ps = connection.prepareStatement("select username from userregistration where username=?");
		ps.setString(1, userName);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			return rs.getString(1);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return null;
	}

public UserLoginData getPassword(JdbcTemplate jdbcTemplate, String userName) {
	
	try {
		UserLoginData data = new UserLoginData();
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		PreparedStatement ps = connection.prepareStatement("select pwd,adminrole from userregistration where (username=? or email=?)");
		ps.setString(1, userName);
		ps.setString(2, userName);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			 data.setPassword(rs.getString(1));
			 data.setAdmininfo(rs.getString(2));
		}
		return data;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return null;
	}

	
}
