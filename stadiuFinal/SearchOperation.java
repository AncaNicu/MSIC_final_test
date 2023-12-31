package library;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//clasa pt a cauta carti dupa anumite criterii
public class SearchOperation extends ConcreteOperation{
	//folosit pt a afisa cu loggerul
	private static final ConfiguredLogger logger = new ConfiguredLogger();
	
    //pt a cauta o carte dupa autor
    public void searchBooksByAuthor(String searchedAuthor)
    {
    	//lista de carti care au acel autor
    	List<Element> matchingBooks = new ArrayList<>();
        try {
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
                    
                    // obtine continutul lui author sub forma de string si verifica daca coincide cu parametrul fct
                    String bookAuthor = authorElement.getTextContent().trim();
                    
                    if (bookAuthor.equals(searchedAuthor)) 
                    {
                    	matchingBooks.add(bookElement);
                    }
                }
            }
            
            //daca nu s-a gasit nicio carte scrisa de acel autor, se af msg de eroare
            if(matchingBooks.isEmpty())
            {
            	logger.info("There is no book written by " + searchedAuthor + "!");
            }
            else
            {
            	displaySearchedBooks(matchingBooks);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    //pt a cauta o carte dupa editura
    public void searchBooksByPublisher(String searchedPublisher)
    {
    	//lista de carti care au acea editura
    	List<Element> matchingBooks = new ArrayList<>();
        try {
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
                    
                    // obtine elementul publisher al cartii crt
                    Element publisherElement = (Element) bookElement.getElementsByTagName("publisher").item(0);
                    
                    // transforma publisher in string si verif daca coincide cu parametrul fct
                    String bookPublisher = publisherElement.getTextContent().trim();
                    
                    if (bookPublisher.equals(searchedPublisher)) 
                    {
                    	matchingBooks.add(bookElement);
                    }
                }
            }
            
            //daca nu s-a gasit nicio carte aparuta la acea editura, se af msg de eroare
            if(matchingBooks.isEmpty())
            {
            	logger.info("There is no book published by " + searchedPublisher + "!");
            }
            else
            {
            	displaySearchedBooks(matchingBooks);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    //pt a cauta cartile care au anul intre 2 ani dati
    public void searchBooksBetweenYears(int year1, int year2)
    {
    	//lista de carti care au anul intre 2 ani dati
    	List<Element> matchingBooks = new ArrayList<>();
        try {
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
                    
                    // obtine elementul year al cartii crt
                    Element yearElement = (Element) bookElement.getElementsByTagName("year").item(0);
                    
                    // transforma year in string si verif daca year e cuprins intre anii dati ca param
                    String bookYear = yearElement.getTextContent().trim();
                    
                    if (Integer.parseInt(bookYear) >= year1 && Integer.parseInt(bookYear) <= year2) 
                    {
                    	matchingBooks.add(bookElement);
                    }
                }
            }
            
            //daca nu s-a gasit nicio carte aparuta intre year1 si year2, se af msg de eroare
            if(matchingBooks.isEmpty())
            {
            	logger.info("There is no book published between " + year1 + " and " + year2 + "!");
            }
            else
            {
            	displaySearchedBooks(matchingBooks);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    //pt a cauta cartile cu anumite cuvinte cheie
    //se iau in considerare cartile care au in keywords cel putin unul dintre cuvintele din searchedKeywords
    public void searchBooksByKeywords(String[] searchedKeywords)
    {
    	//lista de carti care au cel putin un searchedKeyword in lista lor de cuv cheie
    	List<Element> matchingBooks = new ArrayList<>();
        try {
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
                    
                    // obtine elementul keywords pt cartea crt
                    Element keywordsElement = (Element) bookElement.getElementsByTagName("keywords").item(0);
                    
                    // transforma continutul acelui element intr-un string, pe care il desparte dupa virgule
                    String[] bookKeywords = keywordsElement.getTextContent().trim().split(", ");
                    
                    //parcurge lista de cuvinte cheie cautate
                    for (String searchedKeyword : searchedKeywords) 
                    {
                    	//daca lista de cuvinte cheie ale cartii crt contine cuvantul crt cautat
                        if (Arrays.asList(bookKeywords).contains(searchedKeyword)) 
                        {
                            matchingBooks.add(bookElement);
                            break; // iese din bucla cand gaseste un keyword
                        }
                    }
                }
            }
            
            //daca nu s-a gasit nicio carte care sa aiba cel putin unul dintre cuvintele cautate, se af msg de eroare
            if(matchingBooks.isEmpty())
            {
            	logger.info("There is no book that has any of the searched keywords!");
            }
            else
            {
            	displaySearchedBooks(matchingBooks);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    //pt a cauta cartile dupa domeniu
    //se iau in considerare cartile care au searchedDomain in lista lor de domenii
    public void searchBooksByDomain(String searchedDomain)
    {
    	//lista de carti care au acel autor
    	List<Element> matchingBooks = new ArrayList<>();
        try {
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
                    
                    // obtine elementul domains al cartii crt
                    Element domainsElement = (Element) bookElement.getElementsByTagName("domains").item(0);

                    String[] bookDomains = domainsElement.getTextContent().trim().split(", ");

                    // verif daca lista de domenii contine domeniul cautat
                    if (Arrays.asList(bookDomains).contains(searchedDomain)) 
                    {
                        matchingBooks.add(bookElement);
                    }
                }
            }
            
            //daca nu s-a gasit nicio carte care sa aiba domeniul cautat in lista ei de domenii, se af msg de eroare
            if(matchingBooks.isEmpty())
            {
            	logger.info("There is no " + searchedDomain + " book!");
            }
            else
            {
            	displaySearchedBooks(matchingBooks);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    
    //pt a afisa cartile cautate sub forma de tabel
    public void displaySearchedBooks(List<Element> matchingBooks) {
    	//pt a afisa cartile sub forma de tabel
    	//header reprezinta antetul tabelului
        String header = String.format("%-20s%-20s%-10s%-30s%-20s%-20s%n", "Title", "Author", "Year", "Keywords", "Domains", "Publisher");
        logger.info(header);
    	
        //se parcurge lista de carti si se afiseaza datele fiecarei carti
        //un rand reprezinta datele unei carti
    	for (int i = 0; i < matchingBooks.size(); i++) 
        {
            Element bookElement = matchingBooks.get(i);
            
            // extrage detaliile cartii crt
            String title = bookElement.getElementsByTagName("title").item(0).getTextContent();
            String author = bookElement.getElementsByTagName("author").item(0).getTextContent();
            String year = bookElement.getElementsByTagName("year").item(0).getTextContent();
            String keywords = bookElement.getElementsByTagName("keywords").item(0).getTextContent();
            String domains = bookElement.getElementsByTagName("domains").item(0).getTextContent();
            String publisher = bookElement.getElementsByTagName("publisher").item(0).getTextContent();

            String row = String.format("%-20s%-20s%-10s%-30s%-20s%-20s%n", title, author, year, keywords, domains, publisher);
            logger.info(row);

        }
    }
}