package questions.virendra;

import java.util.ArrayList;
import java.util.List;



public class LongestCommonSubsequence {
	public static void commonSubsequence(String S1, String S2)
	{
		int s1_len = S1.length(); 
		int s2_len = S2.length();
	    final int pick_s1_or_s2 = 0;
		final int pick_s1 = 1;
		final int pick_s2 = 2;
		int match[][] = new int[s1_len][s2_len];
		int pointer[][]  = new int[s1_len][s2_len];
		
		
		for(int i=0; i<s1_len; i++)
		{
			for(int j=0; j<s2_len; j++)
			{
				if(S1.charAt(i) ==  S2.charAt(j)) //Characters match
				{    
					if((i==0) || (j==0)) //first row or first column
					{
						match[i][j] = 1; //initialize
					}
					else
					{
						match[i][j] = match[i-1][j-1] + 1;
					}
					pointer[i][j] = pick_s1_or_s2;  
				}
				else  //Characters mismatch
				{	
					if((i > 0 ) && (j > 0)) //neither the first row nor first column
					{
						//Refer in ppt :- LCS(ACBE,ADC) = max(LCS(ACB,ADC), LCS(ACBE,AD))
						//Thumb rule:- mismatch take max. if not in first row or column.
						if(match[i-1][j] >= match[i][j-1])
						{
							match[i][j] = match[i-1][j];
							pointer[i][j] = pick_s2; //ditch s1's character
						}
						else
						{
							match[i][j] = match[i][j-1];
							pointer[i][j] = pick_s1;//ditch s2's character.
						}
					} 
					else if ((i==0) && ( j > 0)) //first row
					{
						match[i][j] = match[i][j-1];
						pointer[i][j] = pick_s1;
					}
					else if((j==0) && (i>0)) //first column
						
					{
						match[i][j] = match[i-1][j];
						pointer[i][j] = pick_s2;
					}
					
				}
				
			}
		}
		
	
		//Printing the LCS.
		int i = s1_len - 1;
		int j = s2_len - 1;
		StringBuffer result = new StringBuffer();
		
		while(i>=0 && j>=0)
		{
			switch(pointer[i][j])
			{
			   //go diagonal and collect the matched character
				case pick_s1_or_s2: 
					result.append(S1.charAt(i));
					i--;
					j--;
					break;
				case pick_s1://go left
					j--;
					break;
				case pick_s2://go up
					i--;
					break;
			}
		}
		
		System.out.println(result.reverse());
		
	}
	
	public static void main(String args[])
	{
		commonSubsequence("ACBEA", "ADCA");
	}

}
