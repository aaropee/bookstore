package com.example.bookstore.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
	
	List<Book> findByAuthor(@Param("author") String author);
	List<Book> findByTitle(String title);
	
}
