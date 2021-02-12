package com.example.bookstore;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookRepository;
import com.example.bookstore.model.Category;
import com.example.bookstore.model.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner bookDemonstration(BookRepository brepo, CategoryRepository crepo) {
		return (args) -> {
			log.info("Save a few books");
			
			crepo.save(new Category("Encyclopedia"));
			crepo.save(new Category("Design"));
			crepo.save(new Category("Action"));
			crepo.save(new Category("Fiction"));
			
			brepo.save(new Book("Äänityön Kivijalka", "Jukka Laaksonen", 2006, "951-98245-7-X", 32, crepo.findByName("Encyclopedia").get(0)));
			brepo.save(new Book("Universal Principles of Design", "William Lidwell", 2010, "1-59253-587-9", 25, crepo.findByName("Design").get(0)));
			
			//Fetch all books
			for (Book book : brepo.findAll()) {
				log.info(book.toString());
			}
		};
	}
}