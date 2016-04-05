package questions.virendra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Bridge{
	Integer x; // North Point
	Integer y;// South Point
	
	Bridge(int xCorr, int yCorr)
	{
		this.x = xCorr;
		this.y = yCorr;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}
	
	
}

public class BuildingBridges {
	
	public static int[] LIS(int X[])
	{
		int parent[]= new int[X.length]; //Tracking the predecessors/parents of elements of each subsequence.
		int increasingSub[]= new int[X.length + 1]; //Tracking ends of each increasing subsequence.
		int length = 0; //Length of longest subsequence.
		
		for(int i=0; i<X.length; i++)
		{
			//Binary search
			int low = 1;
			int high = length;
			while(low <= high)
			{
				int mid = (int) Math.ceil((low + high)/2);
				
				if(X[increasingSub[mid]] <= X[i])
					low = mid + 1;
				else
					high = mid - 1;
			}
			
			int pos = low;
			//update parent/previous element for LIS
			parent[i] = increasingSub[pos-1];
			//Replace or append
			increasingSub[pos] =  i;
			
			//Update the length of the longest subsequence.
			if(pos > length)
				length=pos;
		}
		
		//Generate LIS by traversing parent array
		int LIS[] = new int[length];
		int k 	= increasingSub[length];
		for(int j=length-1; j>=0; j--)
		{
			LIS[j] =  X[k];
			k = parent[k];
		}
		
		
		return LIS;
	}
	
	public static void buildBridges(int x[], int y[])
	{
		ArrayList<Bridge> bridges = new ArrayList<Bridge>();
		
		for(int i=0; i<y.length; i++)
		{
			bridges.add(new Bridge(x[i],y[i]));
		}
		
		Comparator<Bridge> comparator = new Comparator<Bridge>()
	    {

	        public int compare(Bridge a,Bridge b)
	        {
	        	
	        	if(a.getY() != b.getY())
	        		return a.getY().compareTo(b.getY());
	        	else 
	        		return a.getX().compareTo(b.getX());
	        }

	    };

	    Collections.sort(bridges, comparator); //O(nlogn)
	    int[] xsortedOnY = new int[x.length];
	    int i = 0;
	    
	    for (Bridge b : bridges)
	    {
	        xsortedOnY[i] = b.getX();
	        i++;
	       
	    }
	    //find LIS count
	    System.out.println(LIS(xsortedOnY).length); 
	    
	}
	
	
	public static void main(String args[])
	{
		
		int X[] = {2,5,8,10};
		int Y[] = {6,4,1,2};
		//ans :- 2
		
	    buildBridges(X, Y);
	    
	    System.out.println();
	    
	    int X1[] = {5, 3, 10};
		int Y1[] = {6,4,1};
		//ans :- 2
	    
	    buildBridges(X1,Y1);
	    
	    System.out.println();
	    
	    int X2[] = {1,2,3,4,5,6};
		int Y2[] = {3,4,5,6,1,2};
		//ans :- 4
	    
	    buildBridges(X2, Y2);
	    
	    System.out.println();
	    
	    int X3[] = {0,1,1};
	  	int Y3[] = {1,2,-1};
	  	//ans :- 2
	  	    
	    buildBridges(X3, Y3);
	    
	    System.out.println();

	    int X4[] = {0,1,1};
	  	int Y4[] = {1,3,2}; 
	  	//ans :- 3
	  	    
	    buildBridges(X4, Y4);
	    
	    System.out.println();
	    
	    int X5[] = {1,2,2};
	  	int Y5[] = {1,1,1};
	  	//ans :- 3
	  	    
	    buildBridges(X5, Y5);
	    
	    System.out.println();
	    
	    int X6[] = {0,2,1,1};
	  	int Y6[] = {1,4,4,3}; 
	    buildBridges(X6, Y6);
	    //ans :- 4
	    
	    System.out.println();
	    
	    int X7[] = {2,7};
	  	int Y7[] = {6,6}; 
	    buildBridges(X7, Y7);
	    //ans :- 2
	    
	    System.out.println();
	}
	
}
