package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;

@RestController
public class BookController {
	
	private static final String HttpsStatus = null;
	@Autowired
	private BookService bookService;
	//@RequestMapping(value ="/books",method=RequestMethod.GET)
	//@ResponseBody
      @GetMapping("/books")
       public ResponseEntity <List<Book>> getBooks(){
	   
	   List<Book> list =bookService.getAllBooks();
	   if(list.size()<=0) {
		   return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	   }
	   
		return ResponseEntity.of(Optional.of(list));
	}
	
   
   
	@GetMapping("/books/{bid}")
	public ResponseEntity <Book> getBook(@PathVariable("bid") int bid) {
		Book book =bookService.getBookByBid(bid);
		if(book==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.of(Optional.of(book));
	}
	
	//Add book /post
	@PostMapping("/books")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		Book b=null;
		try{
		    b = this.bookService.addBook(book);
			System.out.println(book);
			 return ResponseEntity.of(Optional.of(b));
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			
		}
		
	}
	
	
	//delete book
	@DeleteMapping("/books/{bid}")
	public  ResponseEntity<Void> deleteBook(@PathVariable("bid")int bid) {
		try {
			this.bookService.deleteBook(bid);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/books/{bid}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable("bid") int bid){
		try {
			this.bookService.updateBook(book,bid);
			return ResponseEntity.ok().body(book);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		}
	}
	
	
	
	


