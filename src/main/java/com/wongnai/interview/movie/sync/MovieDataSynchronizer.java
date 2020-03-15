package com.wongnai.interview.movie.sync;

import javax.transaction.Transactional;

import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.external.MoviesResponse;
import com.wongnai.interview.movie.search.InvertedIndex;
import com.wongnai.interview.movie.search.InvertedIndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wongnai.interview.movie.MovieRepository;
import com.wongnai.interview.movie.external.MovieDataService;

import java.util.*;

@Component
public class MovieDataSynchronizer {
	@Autowired
	private MovieDataService movieDataService;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private InvertedIndexRepository invertedindexRepository;

	@Transactional
	public void forceSync() {
		//TODO: implement this to sync movie into repository


		MoviesResponse result = movieDataService.fetchAll();
		HashMap<String, Set<Long>> index = new HashMap<>();

		for(int i=0; i<result.size(); i++){
			Movie movie = new Movie(result.get(i).getTitle());
			List<String> actors = new ArrayList<>(result.get(i).getCast());
			movie.setActors(actors);
			movie.setId((long) i);
			movieRepository.save(movie);

			// Create Inverted Index
			String[] words = result.get(i).getTitle().split(" ");
			for(int j=0; j<words.length; j++){
				String word = words[j].toUpperCase();
				if(index.get(word) == null){
					index.put(word, new HashSet<>());
				}
				index.get(word).add((long) i);
			}
		}

		// Save inverted index
		for(Map.Entry<String, Set<Long>> entry : index.entrySet()){
			InvertedIndex i = new InvertedIndex(entry.getKey());
			i.setIndex(entry.getValue());
			invertedindexRepository.save(i);
		}


	}
}
