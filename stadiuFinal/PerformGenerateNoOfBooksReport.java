package library;
//clasa care se ocupa cu generarea efectiva a raportului cu nr de carti
public class PerformGenerateNoOfBooksReport implements PerformOperation {

	@Override
	public void performOperation() {
		GenerateNoOfBooksReport report = new GenerateNoOfBooksReport();
		ConfiguredLogger logger = new ConfiguredLogger();
		//se genereaza raportul si se afiseaza rezultatul
		report.generateReport();
		logger.info("There are " + report.getNoOfBooks() + " books in the library.");
	}

}
