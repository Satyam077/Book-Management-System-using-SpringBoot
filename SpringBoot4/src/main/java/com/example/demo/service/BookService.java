package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import com.example.demo.entity.Book;

@Component
public class BookService {
	
	//get all book
	private static List<Book> list = new  ArrayList<>();
	
	static {
		list.add(new Book(12,"python","Guido Van Rossum"));
		list.add(new Book(13,"C","Denis Ritchie"));
		list.add(new Book(14,"C++","abc"));
		list.add(new Book(15,"Html","xyz"));
	}
	
	public List<Book> getAllBooks(){
		return list;
	}
	
	//get book by bid
	public Book getBookByBid(int bid){
		Book book = null;
		try {
		book=list.stream().filter(e ->e.getBid()==bid).findFirst().get();
		return book;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return book;
	}
	
	//add book
	public Book addBook(Book b) {
		list.add(b);
		return b;
	}
	
	//delete book
		public void deleteBook(int bid) {
		// TODO Auto-generated method stub
		list= list.stream().filter(book ->book.getBid() !=bid).collect(Collectors.toList());
	}

		//Update book 
		public void updateBook(Book book, int bid) {
			list.stream().map(b->{
				
				if(b.getBid()==bid) {
					b.setBname(book.getBname());
					b.setAuthorName(book.getAuthorName());
				}
				return b;
			}).collect(Collectors.toList());
			
		}

	

}
