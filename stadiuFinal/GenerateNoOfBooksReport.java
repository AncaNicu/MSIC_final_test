package library;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
//clasa care se ocupa cu generarea raportului pt nr de carti din bibl
public class GenerateNoOfBooksReport implements GenerateReport {
	//nr de carti din bibl
	private int noOfBooks = 0;
	@Override
	public void generateReport() {
        try {
        	XMLDocument xmlDocument = XMLDocument.getInstance();
			//se obtine documentul si se pastreaza cartile din xml sub forma de lista
        	Document document = xmlDocument.getDocument();
            NodeList booksList = document.getElementsByTagName("book");

            //nr de carti din bibl este nr de elemente de tip book din bibl
            noOfBooks = booksList.getLength();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public int getNoOfBooks()
	{
		return noOfBooks;
	}
}
