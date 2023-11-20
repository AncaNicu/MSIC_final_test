package library;
//clasa care se ocupa cu generarea efectiva a raportului cu nr de autori
public class PerformGenerateNoOfAuthorsReport implements PerformOperation {

	@Override
	public void performOperation() {
		GenerateNoOfAuthorsReport report = new GenerateNoOfAuthorsReport();
		ConfiguredLogger logger = new ConfiguredLogger();
		//se genereaza raportul si se afiseaza rezultatul
		report.generateReport();
		logger.info("There are " + report.getNoOfAuthors() + " authors.");
	}

}
