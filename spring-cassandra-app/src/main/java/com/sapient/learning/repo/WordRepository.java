package com.sapient.learning.repo;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.sapient.learning.model.Word;

public interface WordRepository extends CassandraRepository<Word, String> {
	
	@Query("SELECT * FROM WORDS ORDER BY count LIMIT 10")
	public List<Word> findTopWords();

}
