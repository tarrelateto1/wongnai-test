package com.wongnai.interview.movie;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.tools.packager.Log;
import com.wongnai.interview.movie.external.MovieData;
import com.wongnai.interview.movie.external.MovieDataService;
import com.wongnai.interview.movie.external.MoviesResponse;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.regex.qual.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY;

@RestController
@RequestMapping("/movies")
@Slf4j
public class MoviesController {
	/**
	 * Inject movie search service and use to handle search request.
	 * <p>
	 * You can specify an implementation using @Qualifier("beanName"), for example:
	 *
	 * <pre>
	 * {@literal @}Qualifier("databaseMovieSearchService")
	 * {@literal @}Qualifier("simpleMovieSearchService")
	 * {@literal @}Qualifier("invertedIndexMovieSearchService")
	 * </pre>
	 */
	@Autowired
	@Qualifier("simpleMovieSearchService")
	private MovieSearchService movieSearchService;

	@RequestMapping(method = RequestMethod.GET)
	public String helloWorld() {
		return "Hello World!";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public List<Movie> searchTitleWithKeyword(@RequestParam("q") String keyword) {
		return movieSearchService.search(keyword);
	}

	//test
	public static final String MOVIE_DATA_URL
			= "https://raw.githubusercontent.com/prust/wikipedia-movie-data/master/movies.json";

	@Autowired
	private RestOperations restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MovieDataService movieDataService;


	@Autowired
	private MovieRepository movieRepository;


	@RequestMapping(value = "/test",method = RequestMethod.GET)
	public List<Movie> test()  {
//		ข้อ 1
//		String response = restTemplate.getForObject(MOVIE_DATA_URL,String.class);
//try {
//	MoviesResponse movies = objectMapper.readValue(response, MoviesResponse.class);
//	return movies;
//} catch (Exception ex){
//	return ex;
//}
		// ข้อ สอง
//		List<Movie> movieList = new ArrayList<Movie>();
//		String queryText = "Glorious";
//		for(MovieData movieData : movieDataService.fetchAll()){
//			if(movieData.getTitle().matches(".*" + queryText+" .*")){
//				Movie movie = new Movie(movieData.getTitle(),movieData.getCast());
//				movieList.add(movie);
//			}
//		}
//		return movieList;

return null;
	}
}
