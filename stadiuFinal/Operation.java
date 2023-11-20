package library;

import javax.xml.transform.TransformerException;
//interfata pentru ConcreteOperation
public interface Operation {
    boolean bookExists(String title, String author);
    void saveDocument() throws TransformerException;
    void displayBooks();
}
