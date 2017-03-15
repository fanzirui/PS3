package pkgMain;

import pkgLibrary.Book;

public class BookException extends Exception{
	private String bookID;
	private Book errorBook;
	public BookException(String bookID) {
		super("Book"+bookID+"can not be found");
		this.bookID=bookID;
		errorBook=new Book(bookID);
	}
	
	

	public String getBookID() {
		return bookID;
	}

	public Book getErrorBook() {
		return errorBook;
	}
}