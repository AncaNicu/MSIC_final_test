package library;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;

//clasa pt implementarea concreta a unei operatii
public class ConcreteOperation implements Operation{

	//documentul XML
	protected XMLDocument xmlDocument;
	//loggerul configurat pt a putea afisa diverse mesaje
    private ConfiguredLogger logger;
    
    //constructor pt initializarea datelor membru
    public ConcreteOperation()
    {
    	xmlDocument = XMLDocument.getInstance();
        logger = new ConfiguredLogger();
    }
    
    //pt a vedea daca o carte deja exista in biblioteca (are acelasi titlu si acelasi autor)
    @Override
    public boolean bookExists(String title, String author) {
        try {
        	//obtine documentul xml
        	Document document = xmlDocument.getDocument();
        	//obtine toate cartile din xml si le pastreaza sub forma de lista
            NodeList booksList = document.getElementsByTagName("book");

            //parcurge lista de carti
            for (int i = 0; i < booksList.getLength(); i++) 
            {
                Node book = booksList.item(i);

                if (book.getNodeType() == Node.ELEMENT_NODE) 
                {
                    Element bookElement = (Element) book;
                    
                    // obtine elementele title si author 
                    Element titleElement = (Element) bookElement.getElementsByTagName("title").item(0);
                    Element authorElement = (Element) bookElement.getElementsByTagName("author").item(0);
                    
                    // obtine datele ca stringuri
                    String bookTitle = titleElement.getTextContent().trim();
                    String bookAuthor = authorElement.getTextContent().trim();
                    
                    //daca titlul si autorul cartii crt coincid cu parametrii functiei => cartea exista
                    if (bookTitle.equals(title) && bookAuthor.equals(author)) 
                    {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    //pt a salva documentul
    @Override
    public void saveDocument() {
        try {
        	//REZOLVA "Disable access to external entities in XML parsing."
            // Transformer e fol. pt. a transforma date XML
            SAXTransformerFactory transformerFactory = (SAXTransformerFactory) TransformerFactory.newInstance();
            transformerFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            //se creeaza o noua instanta a lui TransformerHandler
            TransformerHandler transformerHandler = transformerFactory.newTransformerHandler();
            //se seteaza OutputProperty pt a indenta xml-ul
            transformerHandler.getTransformer().setOutputProperty(OutputKeys.INDENT, "yes");

            //se obtin documentul si filePath-ul
            Document document = xmlDocument.getDocument();
            String filePath = xmlDocument.getFilePath();

            // creeaza sursa DOM
            DOMSource source = new DOMSource(document);

            // creeaza StreamResult cu locatia fisierului
            StreamResult result = new StreamResult(new File(filePath));

            //seteaza rezultatul pt TransformerHandler
            transformerHandler.setResult(result);
            //se efectueaza transformarea
            transformerHandler.getTransformer().transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //afiseaza toate cartile
    @Override
    public void displayBooks()
	{
    	try
    	{
    		//obtine documentul xml si transforma toate elementele carti intr-o lista
    		Document document = xmlDocument.getDocument();
            NodeList booksList = document.getElementsByTagName("book");

            //parcurge lista de carti si le afiseaza asa cum sunt afisate si in xml
            for (int i = 0; i < booksList.getLength(); i++) 
            {
    			Node book = booksList.item(i);
    			
    			if(book.getNodeType() == Node.ELEMENT_NODE)
    			{
    				logger.info("Book " + i);
    				
    				//pt a obtine datele cartii
    				NodeList bookDetails = book.getChildNodes();
    				for(int j = 0; j < bookDetails.getLength(); j++)
    				{
    					Node detail = bookDetails.item(j);
    					
    					if(detail.getNodeType() == Node.ELEMENT_NODE)
    					{
    						Element detailElement = (Element) detail;
    						logger.info("\t" + detailElement.getTagName() + ": " + detailElement.getTextContent());
    					}
    				}
    			}
            }
    	}catch(Exception e){
    		
    		logger.info("There was an error!");
    	}
	}
}