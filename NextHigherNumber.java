package questions.virendra;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class NextHigherNumber {
	
	public static void nextHigherNumber(Integer num)
	{
		Integer nextNum = num, temp = num;
		ArrayList<Integer> digits = new ArrayList<Integer>();
		
		while(temp > 0 )
		{
			int digit = temp%10;
			temp = temp/10;
			digits.add(digit);
		}
		
		int j = 0;
		int i = j+1;
		
		if(digits.size() > 1)
		{
			
			
			while(i < digits.size())
			{
				if (digits.get(i) < digits.get(j))
				{
					break;
				}
				j++;
				i++;
			}
			
			if(i>=digits.size())
			{
				System.out.println("None");
				return;
			}
			
			
			//where to swap
			
			int swapLoc = 0;
			j=0;
			
			while(j<i)
			{
				if(digits.get(j) > digits.get(i))
					break;
				j++;
			}
			//swap last and i
			int dg = digits.get(i);
			digits.set(i, digits.get(j));
			digits.set(j, dg);
			
			//Collections.sort(digits.subList(i+1, digits.size()));
		}
	
		List<Integer> subList =  digits.subList(0, i);
		
		System.out.println(subList);
		
		Collections.sort(subList);
	    int m = digits.size() - 1;
		while(m >= i)
		{
			System.out.print(digits.get(m));
			m--;
		}
		
		m = 0;
		while(m < subList.size())
		{
			System.out.print(subList.get(m));
			m++;
		}
		
		System.out.println();
		
	}

	public static void main(String args[])
	{
		nextHigherNumber(12543);
		nextHigherNumber(4132);
		nextHigherNumber(1234);
		nextHigherNumber(32841);
	}
	
}
