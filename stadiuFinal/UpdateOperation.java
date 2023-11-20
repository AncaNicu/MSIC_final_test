package library;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

//clasa pt gestionarea operatiei de actualizare a unei carti din xml
public class UpdateOperation extends ConcreteOperation{

	//pt a actualiza o carte avand date titlul si autorul sau
    public void updateBookByTitleAndAuthor(String targetData, String updatedTitle, String updatedAuthor, 
    		String[] updatedKeywords, int updatedYear, String[] updatedDomains, String updatedPublisher) 
    {
    	//pres ca nu exista deja o carte (updatedTitle si updatedAuthor) in afara de cea cu (targetTitle si targetAuthor)
    	boolean bookExists = false;
    	
        ConfiguredLogger logger = new ConfiguredLogger();
        
        //face split la targetData in targetTitle si targetAuthor
        String[] parts = targetData.split(",");
        String targetTitle = parts[0].trim();
        String targetAuthor = parts[1].trim();
    	
        try {
        	//daca in biblioteca e o carte (diferita de cea pe care vrem s-o
        	//actualizam) cu ac title si autor ca cele noi => nu se poate face update
        	if((!targetTitle.equals(updatedTitle) || !targetAuthor.equals(updatedAuthor)) 
        			&& bookExists(updatedTitle, updatedAuthor))
        	{
        		logger.info("A book with the same author and title already exists!");
        	}
        	else
        	{
        		//se obtine documentul si se pastreaza cartile din xml sub forma de lista
        		Document document = xmlDocument.getDocument();
            	NodeList books = document.getElementsByTagName("book");
            	
            	//se parcurge lista de carti
                for (int i = 0; i < books.getLength(); i++) 
                {
                    Element book = (Element) books.item(i);
                    //se obtin datele titlu si autor ale cartii crt si se transforma in stringuri
                    Element titleElement = (Element) book.getElementsByTagName("title").item(0);
                    String currentTitle = titleElement.getTextContent().trim();
                    Element authorElement = (Element) book.getElementsByTagName("author").item(0);
                    String currentAuthor = authorElement.getTextContent().trim();
                    
                    //cartea crt are titlul si autorul date => actualizam cartea cu noile date
                    if (currentTitle.equals(targetTitle) && currentAuthor.equals(targetAuthor)) 
                    {
                    	bookExists = true;
                    	
                        // obtine elementele componente ale cartii
                        Element publisherElement = (Element) book.getElementsByTagName("publisher").item(0);
                        Element keywordsElement = (Element) book.getElementsByTagName("keywords").item(0);
                        Element yearElement = (Element) book.getElementsByTagName("year").item(0);
                        Element domainsElement = (Element) book.getElementsByTagName("domains").item(0);
                        
                        //actualizeaza datele cartii
                        authorElement.setTextContent(updatedAuthor);
                        publisherElement.setTextContent(updatedPublisher);
                        keywordsElement.setTextContent(String.join(", ", updatedKeywords));
                        titleElement.setTextContent(updatedTitle);
                        domainsElement.setTextContent(String.join(", ", updatedDomains));
                        yearElement.setTextContent(Integer.toString(updatedYear));

                        break; //daca s-a gasit cartea => parasim bucla
                    }
                    
                }
                
                //daca exista cartea pe care vrem s-o actualizam => se salveaza documentul
                if(bookExists)
                {
                	saveDocument();
                	logger.info("Book has been updated successfully!");
                }
                else
                {
                	logger.info("Book doesn't exists!");
                }
                
        	}
            
        }catch(Exception e)
        {
        	logger.info("Updating Error!!!");
        }
    }
}