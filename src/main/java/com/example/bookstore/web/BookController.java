package com.example.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository repo;
	
	// Listing all books
	@RequestMapping(value = {"/", "booklist"})
	public String listAllBooks(Model model) {
		model.addAttribute("books", repo.findAll());
		return "booklist";
	}

	// Adding new books
	@RequestMapping(value = "/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		return "addbook"; //addbook.html
	}
	
	// Save a new book
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		repo.save(book);
		return "redirect:booklist";
	}
	
	// Delete existing book
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id")Long id, Model model) {
		repo.deleteById(id);
		return "redirect:../booklist";
	}
	
	// Edit existing book
	@RequestMapping(value="/edit/{id}")
	public String editBook(@PathVariable("id") Long id, Model model) {
		model.addAttribute("book", repo.findById(id));
		return "editbook";
	}
	
}
