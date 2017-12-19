package info.vdsi.nags.app.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import info.vdsi.nags.app.beans.MovieDetails;
import info.vdsi.nags.app.beans.MovieNamesResBody;
import info.vdsi.nags.app.beans.MovieReviews;
import info.vdsi.nags.app.beans.ReviewResponseBody;
import info.vdsi.nags.app.model.AddMovieDetails;
import info.vdsi.nags.app.utilities.JSONFileConversion;

@RestController
public class MovieDataController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping("/admin/addMovies")
	public ModelAndView addMoviesList() {
		AddMovieDetails md = new AddMovieDetails();
		List<MovieDetails> movieList = JSONFileConversion.getMovieList();
		
		md.addMovies(movieList, jdbcTemplate);
		ModelAndView view = new ModelAndView();
		view.setViewName("showModifyMovieDetails");
		
		return view;
	}
	
	@RequestMapping(value="/admin/addMovie", method=RequestMethod.POST)
	public ModelAndView addMovieDetails(@Valid @ModelAttribute("movie") MovieDetails movie, BindingResult result, Model model) {
		AddMovieDetails md = new AddMovieDetails();
		
		md.addNewMovie(movie, jdbcTemplate);
		ModelAndView view = new ModelAndView();
		view.setViewName("showModifyMovieDetails");
		
		return view;
	}
	
	@RequestMapping(value="/user/movieDetails", method=RequestMethod.GET)
	public ModelAndView getMovieDetails( @RequestParam(value="title", required=true) String title) {
		AddMovieDetails md = new AddMovieDetails();
		MovieDetails movie = md.getMovieDetails(jdbcTemplate, title);
		ModelAndView view = new ModelAndView();
		view.addObject("movie",movie);
		view.setViewName("showMovieInfo");
		
		return view;
	}
	
	@RequestMapping(value="/user/writeReview", method=RequestMethod.POST)
	public ResponseEntity writeReviewData( @Valid @RequestBody MovieReviews reviewBody, Errors errors) {
		
		ReviewResponseBody result = new ReviewResponseBody();
		Date todaysDate = new Date();
	    DateFormat df2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	    reviewBody.setReviewedBy("kuppalanagarjuna");
		reviewBody.setReviewDate(df2.format(todaysDate));
		AddMovieDetails md = new AddMovieDetails();
		if ("".equals(reviewBody.getReview())|| null==reviewBody.getReview()) {
            result.setMsg("Review is Mandatory");
            return ResponseEntity.badRequest().body(result);
        }
		
		int noOfRows = md.saveUserReview(jdbcTemplate,reviewBody);
		
		if(noOfRows !=0) {
			result.setMsg("Thankyou for writing review !!");
		}else {
			result.setMsg("Something went wrong. Please try again.");
		}
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value="/admin/getMovieInfo", method=RequestMethod.POST)
	public ResponseEntity searchMovie( @Valid @RequestBody MovieNamesResBody resBody, Errors errors) {
		
		AddMovieDetails md = new AddMovieDetails();
		MovieDetails movie  = new MovieDetails();
		movie = md.getMovieDetails(jdbcTemplate, resBody.getMovieName());
		
		
		return ResponseEntity.ok(movie);
	}
	
	@RequestMapping(value="/admin/getMovieNames", method=RequestMethod.POST)
	public ResponseEntity showMovieNames( @Valid @RequestBody MovieNamesResBody resBody, Errors errors) {
		
		MovieNamesResBody result = new MovieNamesResBody();
		AddMovieDetails md = new AddMovieDetails();
		List<String> movies = new ArrayList<String>();
		if ("".equals(resBody.getMovieName())|| null==resBody.getMovieName()) {
            result.setMsg("Please enter some keywords..");
            return ResponseEntity.badRequest().body(result);
        }
		
		 movies = md.getMovieNames(jdbcTemplate,resBody.getMovieName());
		
		if(null==movies) {
			result.setMsg("No movie is available !!");
		}
		result.setMovieNames(movies);
		result.setMovieName(resBody.getMovieName());
		return ResponseEntity.ok(result);
	}
	
	
}
