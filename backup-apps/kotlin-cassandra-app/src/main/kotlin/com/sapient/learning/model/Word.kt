package com.sapient.learning.model

import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table

@Table(value = "Words")
data class Word(
	@PrimaryKey
	val word: String,
	var count: Int
)