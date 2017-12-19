package info.vdsi.nags.app.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import info.vdsi.nags.app.beans.MovieDetails;
import info.vdsi.nags.app.beans.MovieNamesResBody;
import info.vdsi.nags.app.beans.MovieReviews;
import info.vdsi.nags.app.beans.ReviewRequestBody;

public class AddMovieDetails {

	
	public void addMovies(List<MovieDetails> moviesList, JdbcTemplate jdbcTemplate) {
		try {
			Connection con =  jdbcTemplate.getDataSource().getConnection();;
			Integer id;
			id = getPrimayKey(con);
		
			if(null==id || id ==0)
				id=+1;
		
			PreparedStatement pstmt = con.prepareStatement("insert into movies (id, imdbid, title, year,rated, released, runtime, genre, director, writer, actors, plot, languages, country, awards, poster, metascore, imdbrating, imdbvotes, type) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			con.setAutoCommit(false);
			for(MovieDetails mo:moviesList) {
				pstmt.setInt(1, id);
				pstmt.setString(2, mo.getImdbID());
				pstmt.setString(3, mo.getTitle());
				pstmt.setString(4, mo.getYear());
				pstmt.setString(5, mo.getRated());
				pstmt.setString(6, mo.getReleased());
				pstmt.setString(7, mo.getRunTime());
				pstmt.setString(8, mo.getGenre());
				pstmt.setString(9, mo.getDirector());
				pstmt.setString(10, mo.getWriter());
				pstmt.setString(11, mo.getActors());
				pstmt.setString(12, mo.getPlot());
				pstmt.setString(13, mo.getLanguage());
				pstmt.setString(14, mo.getCountry());
				pstmt.setString(15, mo.getAwards());
				pstmt.setString(16, mo.getPoster());
				pstmt.setString(17, mo.getMetaScore());
				pstmt.setString(18, mo.getImdbRating());
				pstmt.setString(19, mo.getImdbVotes());
				pstmt.setString(20, mo.getType());
				id++;
				pstmt.addBatch();
			}
			int []result = pstmt.executeBatch();
			System.out.println(result);
			con.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addNewMovie(MovieDetails movie, JdbcTemplate jdbcTemplate) {
		try {
			Connection con =  jdbcTemplate.getDataSource().getConnection();
			Integer id;
			id = getPrimayKey(con);
		
			if(null==id || id ==0)
				id=+1;
		
			PreparedStatement pstmt = con.prepareStatement("insert into movies (id, imdbid, title, year,rated, released, runtime, genre, director, writer, actors, plot, languages, country, awards, poster, metascore, imdbrating, imdbvotes, type) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			con.setAutoCommit(false);
				pstmt.setInt(1, id);
				pstmt.setString(2, movie.getImdbID());
				pstmt.setString(3, movie.getTitle());
				pstmt.setString(4, movie.getYear());
				pstmt.setString(5, movie.getRated());
				pstmt.setString(6, movie.getReleased());
				pstmt.setString(7, movie.getRunTime());
				pstmt.setString(8, movie.getGenre());
				pstmt.setString(9, movie.getDirector());
				pstmt.setString(10, movie.getWriter());
				pstmt.setString(11, movie.getActors());
				pstmt.setString(12, movie.getPlot());
				pstmt.setString(13, movie.getLanguage());
				pstmt.setString(14, movie.getCountry());
				pstmt.setString(15, movie.getAwards());
				pstmt.setString(16, movie.getPoster());
				pstmt.setString(17, movie.getMetaScore());
				pstmt.setString(18, movie.getImdbRating());
				pstmt.setString(19, movie.getImdbVotes());
				pstmt.setString(20, movie.getType());
			int result = pstmt.executeUpdate();
			System.out.println(result);
			con.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Integer getPrimayKey(Connection connection) {
		try {
			PreparedStatement ps = connection.prepareStatement("select max(id)+1 from movies");
			ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			return rs.getInt(1);
		}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	public List<MovieDetails> getAllMovies(JdbcTemplate template) {
		
		try {
			Connection con =  template.getDataSource().getConnection();;
			PreparedStatement ps = con.prepareStatement("select title,imdbid, year, imdbrating, imdbvotes, released from movies order by imdbrating desc");
			ResultSet rs = ps.executeQuery();
			MovieDetails mo;
			List<MovieDetails> movieList = new ArrayList<MovieDetails>();
		while(rs.next()) {
			mo = new MovieDetails();
			 mo.setTitle(rs.getString("title"));
			 mo.setImdbID(rs.getString("imdbid"));
			 mo.setYear(rs.getString("year"));
			 mo.setImdbRating(rs.getString("imdbrating"));
			 mo.setImdbVotes(rs.getString("imdbvotes"));
			 mo.setReleased(rs.getString("released"));
			 movieList.add(mo);
		}
		return movieList;
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	public MovieDetails getMovieDetails(JdbcTemplate template, String imdbID) {
		try {
			Connection con =  template.getDataSource().getConnection();;
			PreparedStatement ps = con.prepareStatement("select * from movies where imdbid=? or title=?");
			ps.setString(1, imdbID);
			ps.setString(2, imdbID);
			ResultSet rs = ps.executeQuery();
			MovieDetails mo = new MovieDetails();
			while(rs.next()) {
				mo = new MovieDetails();
				mo.setTitle(rs.getString("title"));
				mo.setImdbID(rs.getString("imdbid"));
				mo.setYear(rs.getString("year"));
				mo.setImdbRating(rs.getString("imdbrating"));
				mo.setImdbVotes(rs.getString("imdbvotes"));
				mo.setReleased(rs.getString("released"));
				mo.setActors(rs.getString("actors"));
				mo.setPlot(rs.getString("plot"));
				mo.setLanguage(rs.getString("languages"));
				mo.setDirector(rs.getString("director"));
				mo.setAwards(rs.getString("awards"));
				mo.setCountry(rs.getString("country"));
				mo.setGenre(rs.getString("genre"));
				mo.setWriter(rs.getString("writer"));
				mo.setPoster(rs.getString("poster"));
				mo.setMetaScore(rs.getString("metascore"));
				mo.setType(rs.getString("type"));
				mo.setRated(rs.getString("rated"));
				mo.setRunTime(rs.getString("runtime"));
				mo.setReviews(getReviewDetails(template, imdbID));
			}
			return mo;
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	public List<MovieReviews> getReviewDetails(JdbcTemplate template, String imdbID){
		
		try {
			Connection con =  template.getDataSource().getConnection();;
			PreparedStatement ps = con.prepareStatement("select * from moviereviews where imdbid=?");
			ps.setString(1, imdbID);
			ResultSet rs = ps.executeQuery();
			MovieReviews  mr = null;
			List<MovieReviews> reviewList = new ArrayList<MovieReviews>();
			while(rs.next()) {
				mr = new MovieReviews();
				mr.setTitle(rs.getString("title"));
				mr.setImdbID(rs.getString("imdbid"));
				mr.setReviewTitle(rs.getString("reviewtitle"));
				mr.setReview(rs.getString("reviewcontent"));
				mr.setRating(rs.getString("rating"));
				mr.setReviewedBy(rs.getString("reviewedby"));
				mr.setReviewDate(rs.getString("reviewdate"));
				reviewList.add(mr);
			}
			return reviewList;
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

	public int saveUserReview(JdbcTemplate template, MovieReviews review) {
		
		try {
			Connection con =  template.getDataSource().getConnection();
			Integer id;
			id = getMoviereviewsPrimayKey(con);
		
			if(null==id || id ==0)
				id=+1;
			PreparedStatement ps = con.prepareStatement("insert into moviereviews (id, imdbid, title, reviewtitle, reviewcontent,rating, reviewedby,reviewdate) values(?,?,?,?,?,?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, review.getImdbID());
			ps.setString(3, review.getTitle());
			ps.setString(4, review.getReviewTitle());
			ps.setString(5, review.getReview());
			ps.setString(6, review.getRating());
			ps.setString(7, review.getReviewedBy());
			ps.setString(8, review.getReviewDate());
			int rs = ps.executeUpdate();
			
			return rs;
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return 1;
	}
	
	public Integer getMoviereviewsPrimayKey(Connection connection) {
		try {
			PreparedStatement ps = connection.prepareStatement("select max(id)+1 from moviereviews");
			ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			return rs.getInt(1);
		}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	public List<String> getMovieNames(JdbcTemplate template, String movie) {
		try {
			Connection con =  template.getDataSource().getConnection();
			PreparedStatement ps = con.prepareStatement("select title from movies where title like ?");
			ps.setString(1, "%" + movie + "%");
			ResultSet rs = ps.executeQuery();
			List<String> movieNames = new ArrayList<String>();
			
			while(rs.next()) {
			movieNames.add(rs.getString("title"));
			}
			return movieNames;
		}catch(SQLException ex) {
			ex.printStackTrace();
		} 
		
		return null;
	}
	
}

