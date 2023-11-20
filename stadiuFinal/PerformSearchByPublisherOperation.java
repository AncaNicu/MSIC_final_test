package library;

import java.util.Scanner;

//clasa care se ocupa cu cautarea efectiva a cartilor dupa editura
public class PerformSearchByPublisherOperation implements PerformOperation {
	private static Scanner scanner = new Scanner(System.in);
    private static final ConfiguredLogger logger = new ConfiguredLogger();
	@Override
	public void performOperation() {
		SearchOperation searchOp = new SearchOperation();
		
		String searchedPublisher = "";

		logger.info("Searched publisher: ");
		searchedPublisher = scanner.nextLine();
		
		//efectueaza cautarea
		searchOp.searchBooksByPublisher(searchedPublisher);
	}

}
