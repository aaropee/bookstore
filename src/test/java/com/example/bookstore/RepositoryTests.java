package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookRepository;
import com.example.bookstore.model.Category;
import com.example.bookstore.model.CategoryRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RepositoryTests {
	
	@Autowired
	private BookRepository brepo;
	
	@Autowired
	private CategoryRepository crepo;
	
	@Test
	public void createNewBook() {
		Book book = new Book("Tales From the Void", "Meitsi", 1990, "Skrrt-Skrrt-skrrt", 19.90, crepo.findByName("Action").get(0));
		brepo.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
	@Test
	public void createNewCategory() {
		Category cat = new Category("Self-help");
		crepo.save(cat);
		assertThat(cat.getId()).isNotNull();
		
	}
	
	@Test
	public void findByTitleReturnAuthor() {
		List<Book> books = brepo.findByTitle("Äänityön Kivijalka");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Jukka Laaksonen");
	
	}
	
	@Test
	public void deleteBook() {
		brepo.deleteAll();
		assertThat(brepo.count()).isEqualTo(0);
	}
	

}
