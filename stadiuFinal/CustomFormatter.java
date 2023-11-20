package library;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

//clasa pt a formata aspectul afisajului cu logger-ul
public class CustomFormatter extends Formatter {

    @Override
    public String format(LogRecord customRecord) {
        return customRecord.getMessage() + "\n";
    }
}
