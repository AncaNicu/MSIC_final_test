package library;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

//clasa ce defineste un logger customizat
public class ConfiguredLogger {
    private static Logger logger = Logger.getLogger(ConfiguredLogger.class.getName());
    static {
        // configureaza logger-ul
        logger.setUseParentHandlers(false);

        //creeaza un ConsoleHandler si ii seteaza formatul la CustomFormatter
        //pt a putea af un msg in consola
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new CustomFormatter());

        //seteaza nivelullogger-ului la INFO
        logger.setLevel(Level.INFO);

        //adauga ConsoleHandler la logger
        logger.addHandler(consoleHandler);
    }
    
    //functie pt a afisa mesajul primit ca parametru
    public void info(String message)
    {
    	logger.info(message);
    }    
}