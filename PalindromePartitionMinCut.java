package questions.virendra;

public class PalindromePartitionMinCut {
	
	public static int partition(String s) {
		  int n = s.length();
		  boolean palindrome[][] = new boolean[n][n]; //boolean table
		  
		  //Trivial case: single letter palindromes
		  for (int i = 0; i < n; i++) {
			  palindrome[i][i] = true;
		  }
		  
		  //Finding palindromes of two characters.
		  for (int i = 0; i < n-1; i++) {
		    if (s.charAt(i) == s.charAt(i+1)) {
		      palindrome[i][i+1] = true;
		    }
		  }
		  
		  //Finding palindromes of length 3 to n
		  for (int curr_len = 3; curr_len <= n; curr_len++) {
		    for (int i = 0; i < n-curr_len+1; i++) {
		      int j = i+curr_len-1;
		      if (s.charAt(i) == s.charAt(j) //1. The first and last characters should match 
		    	  && palindrome[i+1][j-1]) //2. Rest of the substring should be a palindrome
		      {
		    	palindrome[i][j] = true; 
		      }
		    }
		  }
		  
		  int[] cuts = new int[n];
		  for(int i=0; i<n; i++)
		  {
			  int temp = Integer.MAX_VALUE;
			  if(palindrome[0][i])
				  cuts[i] = 0;
			  else
			  {
				  for(int j=0; j<i; j++)
				  {
					 if((palindrome[j+1][i]) && temp > cuts[j] + 1) 
					 {
						 temp = cuts[j] + 1;
					 }
				  }
				  cuts[i] = temp;
			  }			  
		  }
		  return cuts[n-1];
		}

	public static void main(String args[])
	{
		System.out.println(partition("aab"));
	}
}
