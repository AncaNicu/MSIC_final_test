package library;

import java.util.Scanner;

//clasa care se ocupa cu inserarea efectiva a cartilor in xml
public class PerformInsertOperation implements PerformOperation{
	
	private static Scanner scanner = new Scanner(System.in);
    private static final ConfiguredLogger logger = new ConfiguredLogger();
	@Override
	public void performOperation() {
		InsertOperation insertOp = new InsertOperation();
		
		//datele cartii nou adaugate
		String title = "";
		String author ="";
		String publisher = "";
		String[] keywords;
		String[] domains;
		String year = "";
		
		//se citesc datele cartii
		logger.info("Title: ");
		title = scanner.nextLine();
		logger.info("Author: ");
		author = scanner.nextLine();
		logger.info("Keywords (separated by comma): ");
		keywords = scanner.nextLine().split(", ");
		
		//pt validarea anului
		boolean validYear = false;
		ValidateYear yearValidator = new ValidateYear();
		int yearAsNr = -1;
		
		//cat timp nu s-a citit un an valid
		while(!validYear)
		{
			logger.info("Year: ");
			//anul e citit ca un string
			year = scanner.nextLine();
			//se valideaza anul
			validYear = yearValidator.validate(year);
			if(!validYear)
			{
				logger.info("Please enter an positive integer for the year field!");
			}
			//daca anul e valid, se transforma din string in int
			else
			{
				yearAsNr = Integer.parseInt(year);
			}
		}

		logger.info("Domains (separated by comma): ");
		domains = scanner.nextLine().split(", ");
		logger.info("Publisher: ");
		publisher = scanner.nextLine();
		//se efectueaza inserarea cartii noi
		insertOp.insertNewBook(title, author, keywords, yearAsNr, domains, publisher);
	}

}
