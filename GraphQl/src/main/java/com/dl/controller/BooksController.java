package com.dl.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.dl.Respo.AuthorRepository;
import com.dl.Respo.BookRepository;
import com.dl.model.Author;
import com.dl.model.Books;




@Controller
public class BooksController {
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	public BooksController(BookRepository bookRepository, AuthorRepository authorRepository) {
		super();
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
	}
	
	
	
	//get all books 
	   @QueryMapping
	    public List<Books> book() {
	        return bookRepository.findAll();
	    }
	   
	   //get books by id 
	   @QueryMapping
	   public Optional<Books> bookById(@Argument Integer id) {
		   return bookRepository.findById(id);
	   }
	   
	   //get all authors
	   @QueryMapping
	   public List<Author> Authors(){
		return authorRepository.findAll();
		   
	   }
	   
	   //Get Author by id    
	   @QueryMapping
	   public Optional<Author> authorById(@Argument int id){
		return authorRepository.findById(id);
		   
	   }
	   // to create the book 
	   @MutationMapping
		public Books createBook(@Argument String BookName, @Argument int PageNo ,@Argument int authorId) {
		   
		   Author author = authorRepository.findById(authorId)
	                .orElseThrow(() -> new RuntimeException("Author not found"));
		   
		   
		   Books book = new Books();
	        book.setBookName(BookName);
	        book.setPageNo(PageNo);
	        book.setAuthor(author);
	        return bookRepository.save(book);
		} 
	   //update book by id 
	   @MutationMapping
	   public Books  updateBookById(@Argument String BookName, @Argument int PageNo ,@Argument int authorId,@Argument Integer id) {
		   
		   Books existingbook = bookRepository.findById(id).orElseThrow(()->new RuntimeException("Book not found"));
		   Author author = authorRepository.findById(authorId)
	                .orElseThrow(() -> new RuntimeException("Author not found"));
		   
		   existingbook.setBookName(BookName);
		   existingbook.setPageNo(PageNo);
		   existingbook.setAuthor(author);
		   
		   bookRepository.save(existingbook);
		   return existingbook;
	   }
	
	   //Books delete by id 
	   @MutationMapping
	   public void deleteById(@Argument Integer id) {
		   bookRepository.deleteById(id);
		   
	   }
	   //Author delete by id 
	   @MutationMapping
	   public void deleteAuthorById(@Argument int id ) {
		   authorRepository.deleteById(id);
	   }
	   
	   //create Author 
	   @MutationMapping
	   public Author createAuthor(@Argument String Name) {
		   
		   Author author = new Author();
		   author.setName(Name);
		   
		return authorRepository.save(author);
		   
		   
	   }
	   //update Author by Id
	   @MutationMapping
	   public Author updateAuthorById(@Argument String Name,@Argument int id) {
		   
		   Author existingauthor=authorRepository.findById(id).orElseThrow(()->new RuntimeException("Author not found"));
		   existingauthor.setName(Name);
		   authorRepository.save(existingauthor);
		   
		   return existingauthor;
		   
	   }
	   
		public Page<Books> getBookByPaginationAndSort(String field, int offset, int pageSize) {
			return bookRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(Sort.Direction.ASC,field)));
		}  

}
