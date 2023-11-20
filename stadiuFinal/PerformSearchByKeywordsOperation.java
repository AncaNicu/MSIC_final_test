package library;

import java.util.Scanner;

//clasa care se ocupa cu cautarea dupa cuvintele cheie
//se iau in considerare cartile care au c.p. un keyword din lista de searchedKeywords
public class PerformSearchByKeywordsOperation implements PerformOperation {
	private static Scanner scanner = new Scanner(System.in);
    private static final ConfiguredLogger logger = new ConfiguredLogger();
	@Override
	public void performOperation() {
		SearchOperation searchOp = new SearchOperation();

		String[] keywords;

		//cuv cheie cautate trebuie introduse sub forma de stringuri despratite prin virgula
		logger.info("Searched keywords (separated by comma): ");
		keywords = scanner.nextLine().split(", ");
		//se efectueaza cautarea dupa cuv cheie
		searchOp.searchBooksByKeywords(keywords);
	}

}
