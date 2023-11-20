package library;

//clasa pt validarea anilor
public class ValidateYears implements Validate {

	@Override
	public boolean validate(String input) 
	{
		//input este un string ce uneste year1 si year2 prin virgula
		//impartim input in 2
		String[] years = input.split(",");
		//verifica daca s-au obtinut c. p. 2 termeni din input prin split
		if(years.length >= 2)
		{
			//obtine cei 2 ani
            String year1 = years[0].trim();
            String year2 = years[1].trim();
            
            ValidateYear validator = new ValidateYear();
            //daca ambii ani sunt valizi (numere intregi poz)
            if(validator.validate(year1) && validator.validate(year2))
            {
            	//si daca year1 < year2
            	int year1AsNr = Integer.parseInt(year1);
            	int year2AsNr = Integer.parseInt(year2);
            	
            	return year1AsNr < year2AsNr;
            }
            else
            {
            	return false;
            }
		}
		return false;
	}

}
