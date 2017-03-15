package pkgLibrary;

import java.util.ArrayList;

import javax.xml.bind.annotation.*;

import pkgMain.BookException;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class Catalog {

	@XmlAttribute
	int id;	
	
	@XmlElement(name="book")
	ArrayList<Book> books;
	
	
	public int getId() {
		return id;
	}
	

	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Book> getBooks() {
		return books;
	}
	

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}
	
	public  Book GetBook( String bookID) throws BookException {
		Book B = null;
		for(Book b: books){
			if (b.getId().equals(bookID)){
				B=b;
				break;
			}
		}
		
		if(B==null){
			throw new BookException(bookID);
		}
		return B;

		
	}


	public void AddBook( String bookID) throws BookException {
		
		for(Book b: books){
			if (b.getId().equals(bookID)){
				throw new BookException(bookID);
			}
		
		}
		books.add(new Book(bookID));

	}
	
	
}
