package library;

import java.util.Scanner;

//clasa care se ocupa cu cautarea efectiva a cartilor dupa autor
public class PerformSearchByAuthorOperation implements PerformOperation {

	private static Scanner scanner = new Scanner(System.in);
    private static final ConfiguredLogger logger = new ConfiguredLogger();
	@Override
	public void performOperation() {
		SearchOperation searchOp = new SearchOperation();
		
		String searchedAuthor = "";

		logger.info("Searched author: ");
		searchedAuthor = scanner.nextLine();
		//se efectueaza cautarea dupa autor
		searchOp.searchBooksByAuthor(searchedAuthor);
	}

}
