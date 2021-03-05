package com.example.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookRepository;
import com.example.bookstore.model.CategoryRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository brepo;
	
	@Autowired
	private CategoryRepository crepo;
	
	// Login page
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
	// Listing all books
	@RequestMapping(value = {"/", "booklist"})
	public String listAllBooks(Model model) {
		model.addAttribute("books", brepo.findAll());
		return "booklist";
	}

	// Adding new books
	@RequestMapping(value = "/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepo.findAll());
		return "addbook"; //addbook.html
	}
	
	// Save a new book
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		brepo.save(book);
		return "redirect:booklist";
	}
	
	// Delete existing book
	// Don't allow user's without ADMIN - role to do that
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteBook(@PathVariable("id")Long id, Model model) {
		brepo.deleteById(id);
		return "redirect:../booklist";
	}
	
	// Edit existing book
	@RequestMapping(value="/edit/{id}")
	public String editBook(@PathVariable("id") Long id, Model model) {
		model.addAttribute("book", brepo.findById(id));
		model.addAttribute("categories", crepo.findAll());
		return "editbook";
	}
	

	
}
