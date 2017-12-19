package info.vdsi.nags.app.beans;

import java.util.List;

public class MovieNamesResBody {

	private String movieName;
	private List<String> movieNames;
	private String msg;
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public List<String> getMovieNames() {
		return movieNames;
	}
	public void setMovieNames(List<String> movieNames) {
		this.movieNames = movieNames;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
	
