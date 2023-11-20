package library;

import java.util.Scanner;

//clasa care se ocupa cu efectuarea actualizarii unei carti
public class PerformUpdateOperation implements PerformOperation{
	private static Scanner scanner = new Scanner(System.in);
    private static final ConfiguredLogger logger = new ConfiguredLogger();
	@Override
	public void performOperation() {
		UpdateOperation updateOp = new UpdateOperation();
		
		//noile date ale cartii
		String title = "";
		String author ="";
		String publisher = "";
		String[] keywords;
		String[] domains;
		int yearAsNr = -1;
		
		//titlul si autorul cartii pe care dorim s-o actualizam
		String targetTitle = "";
		String targetAuthor = "";
		
		//pt a vedea daca year e ok
		boolean okInput = false;

		//citeste datele cartii
		logger.info("Target title: ");
		targetTitle = scanner.nextLine();
		logger.info("Target author: ");
		targetAuthor = scanner.nextLine();
		
		logger.info("\n");

		logger.info("New title: ");
		title = scanner.nextLine();
		logger.info("New author: ");
		author = scanner.nextLine();
		logger.info("New keywords (separated by comma): ");
		keywords = scanner.nextLine().split(", ");
		
		//cat timp year nu e un nr intreg poz
		while(!okInput)
		{
			//citeste year ca pe un string
			logger.info("New year: ");
			String year = scanner.nextLine();
			
			//valideaza year
			ValidateYear yearValidator = new ValidateYear();
			okInput = yearValidator.validate(year);
			
			//daca year e valid => se salveaza ca nr in yearAsNr
			if(okInput)
			{
				yearAsNr = Integer.parseInt(year);
			}
			else
			{
				logger.info("Please enter a positive integer for year field!");
			}
		}
		logger.info("New domains (separated by comma): ");
		domains = scanner.nextLine().split(", ");
		logger.info("New publisher: ");
		publisher = scanner.nextLine();

		//actualizeaza cartea cu noile date
		updateOp.updateBookByTitleAndAuthor(targetTitle + "," + targetAuthor, title, author, keywords, yearAsNr, domains, publisher);

	}

}
