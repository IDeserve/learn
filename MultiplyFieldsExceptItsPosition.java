package questions.virendra;

import java.util.Arrays;

public class MultiplyFieldsExceptItsPosition {
	
	public static void prod(int[] input) {
		int n = input.length;
		int[] leftArr = new int[n];
		int left = 1;
		//Traverse from the left
		for (int i = 0; i < n; ++i) {
			leftArr[i] = left;
			left =  left*input[i];
		}
		
		//Traverse from the right
		int right  = 1;
		int[] prodArray =  leftArr;
		for (int i = n - 1; i >= 0; --i) {
			prodArray[i] = right * prodArray[i];
			right = right*input[i];
		}
		System.out.println(Arrays.toString(prodArray));
	}
	
	public static void main(String args[])
	{
		int a[] = {1,2,3,4};
		prod(a);
	}

}
