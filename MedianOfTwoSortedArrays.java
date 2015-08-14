package algos;

import java.util.ArrayList;

public class MedianOfTwoSortedArrays 
{
	
	public int max(int a, int b)
	{
		if (a > b) return a;
		return b;
	}
	
	public int min(int a, int b)
	{
		if (a < b) return a;
		return b;
	}
	
	// a: 1,2,5,11,15  // b: 3 4 13 17 18
	public double findMedian_sorted_arrays(int[] a, int[] b, int start_a, int end_a, int start_b, int end_b)
	{
		
		if ((end_a - start_a == 1) && ((end_b - start_b == 1)))
		{
			return (1.0*(max(a[start_a], b[start_b]) + min(a[end_a], b[end_b])))/2;
		}
		
		int m1_index = (start_a + end_a)/2;
		int m2_index = (start_b + end_b)/2;
		
		int m2 = b[m2_index];
		int m1 = a[m1_index];
		
		if (m2 == m1)
		{
			return m2;
		}
		
		// since m1 <= median <= m2 narrow down search by eliminating elements less than m1 and elements greater than m2  
		if (m1 < m2) //   
		{
			start_a = m1_index;
			end_b = m2_index;
		}
		
		// since m2 <= median <= m1 narrow down search by eliminating elements less than m2 and elements greater than m1
		else // m2 < m1
		{
			start_b = m2_index;
			end_a = m1_index;
		}
		return findMedian_sorted_arrays(a, b, start_a, end_a, start_b, end_b);
	}
	
	public static void main(String[] args)
	{
	
	  MedianOfTwoSortedArrays obj = new MedianOfTwoSortedArrays();
	  
    int [] a = {1,2,5,19,121};
		int [] b = {3,4,12,17,18};
		System.out.println(obj.findMedian_sorted_arrays(a, b, 0, 4, 0, 4));
	}
}
