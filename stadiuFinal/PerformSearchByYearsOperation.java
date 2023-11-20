package library;

import java.util.Scanner;

//clasa care efectueaza cautarea de carti intre 2 ani dati
public class PerformSearchByYearsOperation implements PerformOperation {
	private static Scanner scanner = new Scanner(System.in);
    private static final ConfiguredLogger logger = new ConfiguredLogger();
	@Override
	public void performOperation() {
		SearchOperation searchOp = new SearchOperation();

		//anii sub forma de String si de int
		String year1 = "";
		String year2 = "";
		
		int year1AsNr = -1;
		int year2AsNr = -1;
		
		//pt a vedea daca anii sunt valizi
		boolean okInput = false;
		ValidateYears yearsValidator = new ValidateYears();
		while(!okInput)
		{
			//citeste anii ca pe niste stringuri
            logger.info("Year 1: ");
            year1 = scanner.nextLine();
            logger.info("Year 2: ");
            year2 = scanner.nextLine();
			
            //valideaza year1 si year2
            okInput = yearsValidator.validate(year1 + "," + year2);
            //daca anii sunt valizi, se pastreaza ca intregi
            if(okInput)
            {
            	year1AsNr = Integer.parseInt(year1);
            	year2AsNr = Integer.parseInt(year2);
            }
            else
            {
            	logger.info("Year1 and Year2 should be positive integers and Year1 < Year2!");
            }
		}
		
		//se face efectiv cautarea dupa ani
		searchOp.searchBooksBetweenYears(year1AsNr, year2AsNr);
	}

}
