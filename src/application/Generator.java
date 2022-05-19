package application;

public class Generator
{
	final static String ALP = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	final static String ALPHANUM = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ012345678";
	final static String ALPHANUMSYM = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ012345678!@#$%^&*'?";
	
	private String alphabet;
	
	public Generator(String alphabet)
	{
		this.alphabet = alphabet;
	}
	
	/**
	 * 
	 * @param length
	 * @return random password of given length from the current alphabet
	 */
	public String randomString(int length)
	{
		String result = "";
		for(int i = 0; i < length; i ++)
		{
			String nextChar = String.valueOf(randomLetter());
			result = result.concat(nextChar);	
		}
		return result;
	}
	
	private char randomLetter()
	{
		int rand = (int)(Math.random() * this.alphabet.length());
		return this.alphabet.charAt(rand);
	}
	
	public void setAlphabet(String alphabet)
	{
		this.alphabet = alphabet;
	}
	
}
