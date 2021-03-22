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
import com.example.bookstore.model.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner bookDemonstration(BookRepository brepo, CategoryRepository crepo, UserRepository urepo) {
		return (args) -> {
			log.info("Save a few books");
			
			// Empty populated database
			crepo.deleteAll();
			
			// Save some categories
			crepo.save(new Category("Encyclopedia"));
			crepo.save(new Category("Design"));
			crepo.save(new Category("Action"));
			crepo.save(new Category("Fiction"));
			
			// Save some books
			brepo.save(new Book("Äänityön Kivijalka", "Jukka Laaksonen", 2006, "951-98245-7-X", 32, crepo.findByName("Encyclopedia").get(0)));
			brepo.save(new Book("Universal Principles of Design", "William Lidwell", 2010, "1-59253-587-9", 25, crepo.findByName("Design").get(0)));
			
			// Save two users
//			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
//			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
//			urepo.save(user1);
//			urepo.save(user2);
			
			//Fetch all books
			for (Book book : brepo.findAll()) {
				log.info(book.toString());
			}
		};
	}
}