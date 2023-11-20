package library;

import java.util.Scanner;

//clasa care se ocupa cu cautarea efectiva a cartilor dupa domeniu
//se iau in considerare cartile care au in lista lor de domenii domeniul cautat
public class PerformSearchByDomainOperation implements PerformOperation {
	private static Scanner scanner = new Scanner(System.in);
    private static final ConfiguredLogger logger = new ConfiguredLogger();
	@Override
	public void performOperation() {
		SearchOperation searchOp = new SearchOperation();

		String searchedDomain = "";
		
		logger.info("Searched domain: ");
		searchedDomain = scanner.nextLine();
		//se efectueaza cautarea dupa domeniu
		searchOp.searchBooksByDomain(searchedDomain);
	}

}
