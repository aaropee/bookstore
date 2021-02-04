package com.example.bookstore;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner bookDemonstration(BookRepository repo) {
		return (args) -> {
			log.info("Save a few books");
			repo.save(new Book("Äänityön Kivijalka", "Jukka Laaksonen", 2006, "951-98245-7-X", 32));
			repo.save(new Book("Universal Principles of Design", "William Lidwell", 2010, "1-59253-587-9", 25));
			
			//Fetch all books
			for (Book book : repo.findAll()) {
				log.info(book.toString());
			}
		};
	}
}