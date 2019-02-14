package com.sapient.learning.repo

import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository
import com.sapient.learning.model.Word


@Repository
interface WordRepository:   CassandraRepository<Word, String> {

}