package pkgEmpty;

import static org.junit.Assert.*;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import pkgLibrary.Book;
import pkgLibrary.Catalog;
import pkgMain.BookException;

public class testCatalog {

	@Test(expected=BookException.class)
	public void test1() throws BookException {
		Book bk = new Book("bk001");
		Catalog cat = new Catalog();
		cat = ReadCatalog();
		cat.GetBook(bk.getId());
	}
	
	@Test
	public void test2() throws BookException {
		Book bk = new Book("bk108");
		Catalog cat = new Catalog();
		cat = ReadCatalog();
		assertTrue(cat.GetBook(bk.getId())!=null);
	}
		
	@Test(expected=BookException.class)
	public void test3() throws BookException {
		Book bk = new Book("bk101");
		Catalog cat = new Catalog();
		cat = ReadCatalog();
		cat.AddBook(bk.getId());
	}
	
	@Test
	public void test4() throws BookException {
		Book bk = new Book("bk199");
		Catalog cat = new Catalog();
		cat = ReadCatalog();
		cat.AddBook(bk.getId());
		WriteXMLFile(cat);
	}
	
	
	private static Catalog ReadCatalog() {
		Catalog cat = ReadXMLFile();
		
		System.out.println("cat ID " + cat.getId());
		System.out.println("Book count: " + cat.getBooks().size());

		return cat;		
	}

	private static Catalog IncreasePrice(Catalog cat, double PriceIncrease)
	{
		for (Book b : cat.getBooks()) {
			double newPrice = (b.getPrice() * PriceIncrease) + b.getPrice();			
			b.setPrice(Math.round(newPrice * 100.0) / 100.0);
		}
		
		return cat;
	}
	
	private static void WriteXMLFile(Catalog cat) {
		try {

			String basePath = new File("").getAbsolutePath();
			basePath = basePath + "/src/main/resources/XMLFiles/Books.xml";
			File file = new File(basePath);

			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(cat, file);
			jaxbMarshaller.marshal(cat, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	private static Catalog ReadXMLFile() {

		Catalog cat = null;

		String basePath = new File("").getAbsolutePath();
		basePath = basePath + "/src/main/resources/XMLFiles/Books.xml";
		File file = new File(basePath);

		System.out.println(file.getAbsolutePath());
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			cat = (Catalog) jaxbUnmarshaller.unmarshal(file);
			System.out.println(cat);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return cat;

	}
}
