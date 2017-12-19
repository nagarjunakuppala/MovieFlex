package info.vdsi.nags.app.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;

import info.vdsi.nags.app.beans.UserData;

public class SaveUserDetails {

	public UserData saveUserDetails(JdbcTemplate jdbcTemplate,UserData user) {
		
		String query = "insert into userregistration values(?,?,?,?,?,?,?,?)";
		//KeyHolder holder = new GeneratedKeyHolder();
		try {
			Connection connection = jdbcTemplate.getDataSource().getConnection();
			Integer id = getPrimayKey(connection);
			if(null==id || id ==0)
				id=+1;
				
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getFirstName());
			ps.setString(5, user.getLastName());
			ps.setString(6, user.getEmail());
			ps.setString(7, user.getCity());
			ps.setString(8, (user.isAdminInfo()==true?"Y":"N"));
			
			ps.executeUpdate();
			
				user.setUserId(id);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		/*jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, employee.getEmpName());
				ps.setString(2, employee.getAddress());
				ps.setDouble(3, employee.getSalary());
				return ps;
			}
		}, holder);*/
		//int empId = holder.getKey().intValue();
		//employee.setEmpId(empId);
		
		return user;
		
	}
	
	public Integer getPrimayKey(Connection connection) throws SQLException {
		
		PreparedStatement ps = connection.prepareStatement("select max(userid)+1 from userregistration");
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			return rs.getInt(1);
		}
		
		return null;
	}
	
}
