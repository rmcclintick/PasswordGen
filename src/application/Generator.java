package application;

public class Generator
{
//	final static String ALP = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//	final static String ALPHANUM = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//	final static String ALPHANUMSYM = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*'?";
	
	private int maxIndex;
	
	String[] charSets = 
		{
			"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ",
			"0123456789",
			"!@#$%^&*?"
		};
	
	public Generator(int maxIndex)
	{
		this.maxIndex = maxIndex;
	}
	
	public Generator()
	{
		//default character set in alphanumeric + symbols
		this.maxIndex = 2;
	}
	
	/**
	 * 
	 * @param length
	 * @return random password of given length from the current alphabet
	 */
	public String randomString(int length)
	{
		StringBuilder result = new StringBuilder("");
		//fill result string with spaces to given length
		for (int i = 0; i < length; i ++)
		{
			result = result.append(" ");
		}
		//loop through charSets upto max index
		for (int i = 0; i <= maxIndex; i ++)
		{
			for (int j = 0; j < length; j ++)
			{
				//fills in alphabet characters
				if (i == 0)
				{
					char nextChar = randomLetter(charSets[i]);
					result.setCharAt(j, nextChar);
					System.out.println(result);
				}
				//fills in numbers
				if (i == 1)
				{
					if (Math.random() > 0.5)
					{
						char nextChar = randomLetter(charSets[i]);
						result.setCharAt(j, nextChar);
						System.out.println(result);
					}
				}
				//fills in symbols
				if (i == 2)
				{
					if (Math.random() > 0.8)
					{
						char nextChar = randomLetter(charSets[i]);
						result.setCharAt(j, nextChar);
						System.out.println(result);
					}
				}
					
			}
		}
		return result.toString();
	}
	
	private char randomLetter(String charSet)
	{
		int rand = (int)(Math.random() * charSet.length());
		return charSet.charAt(rand);
	}
	
	public void setAlphabet(String alphabet)
	{
	}
	
}
