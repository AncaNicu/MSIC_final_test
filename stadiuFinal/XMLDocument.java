package library;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

//clasa pt a gestiona documentul xml
//vazuta ca o clasa Singleton
public class XMLDocument {
	//documentul XML (continutul lui)
	private Document document;
	//locatia documentului
	private String filePath; 
	
	//instanta clasei (caracteristica Singleton)
	private static XMLDocument instance = null;
	
	public XMLDocument(String filePath) {
	    try {
	        //se creeaza un nou DocumentBuilderFactory
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        //masuri pt a spori securitatea
	        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
	        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

	        //se obtine builder-ul de la factory
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        //se parcurge doc. XML
	        document = builder.parse(new File(filePath));

	        //se normalizeaza doc. pt a fi consistent si usor de lucrat cu el
	        document.getDocumentElement().normalize();
	        //seteaza data membru filePath
	        this.filePath = filePath;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
	//pt a putea obtine instanta clasei
	public static XMLDocument getInstance()
	{
		//daca nu a fost creata nicio instanta a XMLDocument => se creeaza
		if(instance == null)
		{
			//PT A NU MAI AVEA ISSUE-UL "Refactor your code to get this URI from a customizable parameter."
			//se creeaza un ob. Properaties si se incearca incarcarea
			//proprietatilor din fisierul config specificat
			Properties properties = new Properties();
			try (FileInputStream input = new FileInputStream("C:\\Users\\ASUS\\Desktop\\master1\\MSIC\\Proiect\\cuConfig\\src\\library\\config.properties")){
				properties.load(input);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			//se obtine file path din fisierul de config
            String filePath = properties.getProperty("xml.file.path");

            instance = new XMLDocument(filePath);
		}
		//se returneaza instanta nou creata sau deja existenta
		return instance;
	}

	//getteri pentru datele membru ale clasei
	public Document getDocument() {
		return document;
	}

	public String getFilePath() {
		return filePath;
	}
}
