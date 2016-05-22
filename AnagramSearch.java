package questions.virendra;

import java.util.HashMap;
import java.util.HashSet;

public class AnagramSearch {
	
	public static boolean foundAnagram(HashMap<Integer,Integer> patternHash, HashMap<Integer,Integer>  textHash)
	{	
		for(Integer key: patternHash.keySet())
		{
			if(textHash.containsKey(key))
			{
				if(textHash.get(key) != patternHash.get(key))
					return false;
			}
			else return false;
		}
		
		return true;
	}
	
	public static boolean anagramSubstringSearch(String pattern, String text)
	{	
		HashMap<Integer,Integer> patternHash = new HashMap<Integer,Integer>();
		HashMap<Integer,Integer>  textHash    = new HashMap<Integer,Integer>();
		
		for(int i=0; i<pattern.length();i++)
		{
			int patCh = (int)pattern.charAt(i);
			int txtCh = (int)text.charAt(i);
			if(!patternHash.containsKey(patCh))
			{
				patternHash.put(patCh, 1);
			}
			else
			{
				int temp = patternHash.get(patCh);
				patternHash.put(patCh, ++temp);
			}
			
			if(!textHash.containsKey(txtCh))
			{
				textHash.put(txtCh, 1);
			}
			else
			{
				int temp = textHash.get(txtCh);
				textHash.put(txtCh, ++temp);
			}
			
		}
		

		int windowStart = 0;
		int windowEnd = pattern.length()-1;
		
		while(true)
		{
			if(foundAnagram(patternHash,  textHash))
			{
				return true;
			}
			windowStart++;
			windowEnd++;
			if(windowEnd >= text.length())
				return false;
			
			
			int txtCh = (int)text.charAt(windowStart -1);
			if(textHash.containsKey(txtCh))
			{
				int count = textHash.get(txtCh);
				if(count==1)
					textHash.remove(txtCh);
				else
					textHash.put(txtCh, count-1);
			}
			
			txtCh = (int)text.charAt(windowEnd);
			if(!textHash.containsKey(txtCh))
			{
				textHash.put(txtCh, 1);
			}
			else
			{
				int temp = textHash.get(txtCh);
				textHash.put(txtCh, ++temp);
			}
			
		}
	}

	
	public static void main(String args[])
	{
		AnagramSearch object = new AnagramSearch();
		
	 System.out.println(anagramSubstringSearch("xyiz", "afdgzyxksldfm"));
		
	}
	
}
