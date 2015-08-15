package questions.virendra;

public class EncodeDecode {
	
	
	public static int decode(String message)
	{
		
		int msgLen = message.length();
		if(msgLen == 0 || msgLen == 1)
			return 1;
		
		int count = 0;
		
		if(message.charAt(msgLen - 1 ) > '0')//last digit
			count = decode(message.substring(0, msgLen - 1));//trimmed message by decreasing length by 1
		
		
		if((message.charAt(msgLen - 2) < '2') || ( message.charAt(msgLen - 2) == '2' &&  message.charAt(msgLen - 1) < '7' ) )
			count += decode(message.substring(0, msgLen - 2));
		
		return count;
	}
	
	
	public static int decodeDp(String message)
	{
		int msgLen = message.length();
		int[] decodeCount = new int[msgLen + 1];
		
		decodeCount[0] =1;
		decodeCount[1] =1;
		
		
		for(int i=2; i< msgLen + 1; i++)
		{
			if(message.charAt(i - 1 ) > '0')
				decodeCount[i] = decodeCount[i-1];
			
			if((message.charAt(i - 2) < '2') || ( message.charAt(i - 2) == '2' &&  message.charAt(i - 1) < '7' ) )
				decodeCount[i] = decodeCount[i] + decodeCount[i-2];
		}
		
		return decodeCount[msgLen];
	}
	
	
	public static int countBinary(int N)
	{
		int C0 = 1;
		int C1 = 1;
		
		for(int i=1; i<N; i++)
		{
			int temp = C1;
			C1 = C0;
			C0 = C0 + temp;
		}
		
		return C0 + C1;
	}
	
	public  static int cutRod(int cost[])
	{
	   int n = cost.length;
	   int dp[] = new int[cost.length +1];
	   dp[0] = 0;
	   int i, j;
	
	   for (i = 1; i<=n; i++)
	   {
		   int max_val = Integer.MIN_VALUE;
	       for (j = 0; j < i; j++)
	       {   
	         max_val = Math.max(max_val, cost[j] + dp[i-j-1]);
	       }
	       dp[i] = max_val;
	   }
	 
	   return dp[n];
	}
	
	public static void main(String args[])
	{
	    int rodcosts[] = {1, 5, 8, 9, 10, 17, 17, 20};
	    System.out.println(cutRod(rodcosts));
	}
	
	
	

}
