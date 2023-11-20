package library;

import java.util.InputMismatchException;
import java.util.Scanner;

//clasa pt meniu
public class Menu {
	
	private static Scanner scanner = new Scanner(System.in);
    private static final ConfiguredLogger logger = new ConfiguredLogger();
    
	public static void main(String[] args) {
        int choice = -1;
        //cat timp utilizatorul nu a optat pt Quit
        //se af meniul, se preia alegerea utilizatorului si se gestioneaza optiunea
        while (choice != 10) {
            displayMenu();
            choice = getUserChoice();
            //optiunile valide sunt intregi intre 1 si 10
            if(choice > 10 || choice < 1)
            {
            	logger.info("Invalid option! Please anter an integer between 1 and 10!");
            }
            else
            {
                handleChoice(choice);	
            }
        }
        scanner.close();
	}
    
	//fct pt afisarea meniului
    private static void displayMenu() {
        logger.info("Choose an action by pressing the corresponding key:");
        logger.info("\t1. Add a book");
        logger.info("\t2. Update a book");
        logger.info("\tSearch a book:");
        logger.info("\t\t3. by author");
        logger.info("\t\t4. by keywords");
        logger.info("\t\t5. by year");
        logger.info("\t\t6. by publisher");
        logger.info("\t\t7. by domain");
        logger.info("\t8. See number of authors");
        logger.info("\t9. See number of books in library");
        logger.info("\t10. Quit");

        logger.info("Choose an action: ");
    }
    
    //fct pt a prelua optiunea utilizatorului
    private static int getUserChoice() {
        int choice = -1;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            logger.info("Invalid input. Please enter a number.");
            scanner.next(); // consuma inputul invalid pt a evita o bucla infinita
        }
        return choice;
    }

    //fct pt gestionarea optiunii utilizatorului
    private static void handleChoice(int choice)
    {		
		PerformOperation op;
    	switch(choice)
		{
			case 1: 
				op = new PerformInsertOperation();
				op.performOperation();
				break;
			case 2: 
				op = new PerformUpdateOperation();
				op.performOperation();
				break;
			case 3: 
				op = new PerformSearchByAuthorOperation();
				op.performOperation();
				break;
			case 4: 
				op = new PerformSearchByKeywordsOperation();
				op.performOperation();
				break;
			case 5:
				op = new PerformSearchByYearsOperation();
				op.performOperation();
				break;
			case 6: 
				op = new PerformSearchByPublisherOperation();
				op.performOperation();
				break;
			case 7: 
				op = new PerformSearchByDomainOperation();
				op.performOperation();
				break;
			case 8: 
				op = new PerformGenerateNoOfAuthorsReport();
				op.performOperation();
				break;
			case 9: 
				op = new PerformGenerateNoOfBooksReport();
				op.performOperation();
				break;
			case 10: break;
			default:break;
		}
    }
}