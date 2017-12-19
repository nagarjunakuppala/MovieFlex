package info.vdsi.nags.app.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import info.vdsi.nags.app.beans.MovieDetails;

public class JSONFileConversion {

	public static MovieDetails[] readJsonFile() {
		
		ObjectMapper objMapper = new ObjectMapper();
		MovieDetails[] movie = null;
		try {
			movie = objMapper.readValue(new File("movielist.json"), MovieDetails[].class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movie;
	}
	
	public static MovieDetails getMovieDetails(String movieName) {
		
		 ObjectMapper objectMapper = new ObjectMapper();
		 MovieDetails movies =null;
		try {
			movies= new MovieDetails();
			byte[] mapData = Files.readAllBytes(Paths.get("movielist.json"));
			 //add this line  
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);    

			List<MovieDetails> myObjects = objectMapper.readValue(mapData , objectMapper.getTypeFactory().constructCollectionType(List.class, MovieDetails.class));
			for(MovieDetails movie:myObjects) {
				if(movieName.equalsIgnoreCase(movie.getTitle())) {
					movies = movie;
				}
			}
			return movies;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movies;
	}
	
	public static List<MovieDetails> getMovieList() {
		
		 ObjectMapper objectMapper = new ObjectMapper();
		try {
			byte[] mapData = Files.readAllBytes(Paths.get("movielist.json"));
			 //add this line  
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);    

			List<MovieDetails> movies = objectMapper.readValue(mapData , objectMapper.getTypeFactory().constructCollectionType(List.class, MovieDetails.class));
			
			return movies;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void addMovieDetails(MovieDetails movie) {
		
	        try {
	        	ObjectMapper objectMapper = new ObjectMapper();
	            //String jsonInString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(movie);
	            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
	            List<MovieDetails> list= getMovieList();
	            list.add(movie);
	            objectMapper.writeValue(new File("movielist.json"), list);
	 
	        } catch (JsonGenerationException e) {
	            e.printStackTrace();
	        } catch (JsonMappingException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
	}
	public static void editMovieDetails(String movieName) {
		try {
        	ObjectMapper objectMapper = new ObjectMapper();
            //String jsonInString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(movie);
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            List<MovieDetails> list = editMovie(movieName);
            objectMapper.writeValue(new File("movielist.json"), list );
 
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static List<MovieDetails> editMovie(String movieName) {
		List<MovieDetails> mo = getMovieList();
		for(MovieDetails movie:mo) {
			if(movieName.equalsIgnoreCase(movie.getTitle())) {
				movie.setActors("Nagarjuna,Swathi");
				movie.setDirector("Nagarjuna");
				movie.setAwards("TFA");
			}
		}
		
		return mo;
		
	}
	
	public static void main(String[] args) {
		
		JSONFileConversion js = new JSONFileConversion();
		MovieDetails[] ms = js.readJsonFile();
		System.out.println(ms[0].getGenre());
		MovieDetails mo = js.getMovieDetails("The Shawshank Redemption");
		System.out.println(mo.getActors());
		System.out.println(mo.getAwards());
		System.out.println(mo.getPlot());
		System.out.println(mo.getDirector());
		MovieDetails mm = new MovieDetails();
		mm.setTitle("Test Movie");
		mm.setYear("2017");
		mm.setRated("rated");
		mm.setReleased("released");
		mm.setRunTime("runtime");
		mm.setGenre("genre");
		mm.setDirector("director");
		mm.setWriter("writer");
		mm.setPlot("plot");
		mm.setActors("actors");
		mm.setLanguage("language");
		mm.setCountry("country");
		mm.setAwards("awards");
		mm.setPoster("poster");
		mm.setMetaScore("metascore");
		mm.setImdbID("imdbid");
		mm.setImdbRating("imdbrating");
		mm.setImdbVotes("imdbvotes");
		mm.setType("type");
		
		//js.writeJsonFile(mm);
		
		System.out.println(getMovieList().size());
		editMovieDetails("Test Movie");
		System.out.println(getMovieList().size());
	}
	
}
