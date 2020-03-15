package com.wongnai.interview.movie;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.tools.packager.Log;
import com.wongnai.interview.movie.external.MovieData;
import com.wongnai.interview.movie.external.MovieDataService;
import com.wongnai.interview.movie.external.MoviesResponse;
import com.wongnai.interview.movie.search.DatabaseMovieSearchService;
import com.wongnai.interview.movie.search.InvertedIndexRepository;
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
	@Qualifier("invertedIndexMovieSearchService")
	private MovieSearchService movieSearchService;

	@RequestMapping(method = RequestMethod.GET)
	public String helloWorld() {
		return "Hello World!";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public List<Movie> searchTitleWithKeyword(@RequestParam("q") String keyword) {
		return movieSearchService.search(keyword);
	}

}
