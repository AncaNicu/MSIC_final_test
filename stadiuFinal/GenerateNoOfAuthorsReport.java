package library;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
//clasa care se ocupa cu generarea raportului cu nr de autori
public class GenerateNoOfAuthorsReport implements GenerateReport {
	//nr de autori care au scris carti in bibl
	private int noOfAuthors = 0;
	
	@Override
	public void generateReport() 
	{
    	//lista de autori diferiti
    	List<String> authors = new ArrayList<>();
        try {
        	XMLDocument xmlDocument = XMLDocument.getInstance();
			//se obtine documentul si se pastreaza cartile din xml sub forma de lista
        	Document document = xmlDocument.getDocument();
            NodeList booksList = document.getElementsByTagName("book");

            //se parcurge lista de carti
            for (int i = 0; i < booksList.getLength(); i++) 
            {
                Node book = booksList.item(i);

                if (book.getNodeType() == Node.ELEMENT_NODE) 
                {
                    Element bookElement = (Element) book;
                    
                    // obtine elementul pt autor al cartii crt.
                    Element authorElement = (Element) bookElement.getElementsByTagName("author").item(0);
                    
                    // obtine continutul lui author sub forma de string
                    String bookAuthor = authorElement.getTextContent().trim();
                    //daca lista de autori nu are deja acel autor, se adauga la lista
                    if (!authors.contains(bookAuthor)) 
                    {
                    	authors.add(bookAuthor);
                    }
                }
            }
            //nr de autori este nr de elemente din lista authors
            noOfAuthors = authors.size();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
	}

	public int getNoOfAuthors() {
		return noOfAuthors;
	}
}
