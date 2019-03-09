CREATE KEYSPACE vocabulary 
	WITH replication={'class':'SimpleStrategy', 'replication_factor':1};
	
USE vocabulary;

CREATE TABLE words(
   word text PRIMARY KEY,
   count int
);