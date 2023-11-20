package library;

//clasa pt validarea unui an
public class ValidateYear implements Validate {

	//verifica daca un string poate sa fie an
	//adica este un numar intreg pozitiv
	@Override
	public boolean validate(String input) 
	{
		try {
			int number = Integer.parseInt(input);
			return number >= 0;
		} catch(NumberFormatException e)
		{
			return false;
		}
	}
}
