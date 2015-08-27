package Coding;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SkylineSolution {
	
	    public int greater(int a, int b)
	    {
	    	if(a > b) return a;
	    	return b;
	    }
	    
		public List<int[]> mergeSkylines(List<int[]> skyLineList_lower, List<int[]> skyLineList_higher)
		{
			
			int h1 = 0, h2 = 0;
			
			int newIndex = 0;
			
			List<int[]> skyLine_merged = new ArrayList<int[]>();;
			while(true)
			{
				if(skyLineList_lower.isEmpty() || skyLineList_higher.isEmpty())
				{
					break;
				}
				
				int [] stripe1 = skyLineList_lower.get(0);
				int [] stripe2 = skyLineList_higher.get(0);
				int [] mergedStripe = new int[2]; // 0: x co-ordinate, 1: height
				if(stripe1[0] < stripe2[0]) // comparing x co-ordinates
				{
					mergedStripe[0] = stripe1[0];
					mergedStripe[1] = stripe1[1];
					// if 'y' for chosen one is less than last seen height of other skyline
					if(stripe1[1] < h2)
					{
						mergedStripe[1] = h2;
					}
					// update the last seen height for this skyline
					h1 = stripe1[1];
					
					// move to next key point for this skyline
					skyLineList_lower.remove(0);
				}
				else if(stripe2[0] < stripe1[0])
				{
					mergedStripe[0] = stripe2[0];
					mergedStripe[1] = stripe2[1];
					if(stripe2[1] < h1)
					{
						mergedStripe[1] = h1;
					}
					h2 = stripe2[1];
					skyLineList_higher.remove(0);
				}
				else // (stripe2[0] == stripe1[0]): 
				{    // In this case, only one point can be added and hence we remove both 
					mergedStripe[0] = stripe2[0];
					mergedStripe[1] = greater(stripe1[1], stripe2[1]);
					h1 = stripe1[1];
					h2 = stripe2[1];
					skyLineList_lower.remove(0);
					skyLineList_higher.remove(0);
				}
				
				
				skyLine_merged.add(mergedStripe);
			}
			
			if(skyLineList_lower.isEmpty())
			{
			    while(!skyLineList_higher.isEmpty())
				{
					skyLine_merged.add(skyLineList_higher.remove(0));
				}
			}
			else
			{
				while(!skyLineList_lower.isEmpty())
				{
					skyLine_merged.add(skyLineList_lower.remove(0));
				}
			}
			
			int current = 0;
			while(current < skyLine_merged.size())
			{
				boolean dupeFound = true;
				int i = current + 1;
				while ((i < skyLine_merged.size()) &&  dupeFound)
				{
					if(skyLine_merged.get(current)[1] == skyLine_merged.get(i)[1])
					{
						dupeFound = true;
						skyLine_merged.remove(i);
					}
					else
					{
						dupeFound = false;
					}
				}
				current+=1;
			}
			return skyLine_merged;
		}
	
	
	    public List<int[]> getSkyline_rec(int low, int high, int[][]buildings)
	    {
	    	List<int[]> skyLineList = new ArrayList<int[]>();
	    	if(low > high)
	    	{
	    		return skyLineList;
	    	}
	    	else if(low == high)
	    	{
	    		int x1 = buildings[low][0];
	    		int x2 = buildings[low][1];
	    		int h = buildings[low][2];
	    		
	    		int[] element1 = {x1, h};
	    		int[] element2 = {x2, 0};
	    		skyLineList.add(element2);
	    		skyLineList.add(0,element1);
	    		return skyLineList;
	    	}
	    	else
	    	{
	    		int mid = (low + high) / 2;
	    		List<int[]> skyLineList_lower = getSkyline_rec(low, mid, buildings);
	    		List<int[]> skyLineList_higher = getSkyline_rec(mid+1, high, buildings);
	    		
	    		return mergeSkylines(skyLineList_lower, skyLineList_higher);
	    		
	    	}
	    }
	    
	    
	    
	    public List<int[]> getSkyline(int[][] buildings) {
	    	
	        return getSkyline_rec(0, buildings.length-1, buildings);
	    }

	    /*
	    public List<int[]> test(List<int[]> skyLine_merged)
	    {
	    	int current = 0;
			while(current < skyLine_merged.size())
			{
				if ((current+1) < skyLine_merged.size())
				{
					if(skyLine_merged.get(current)[1] == skyLine_merged.get(current+1)[1])
					{
						skyLine_merged.remove(current+1);
					}
				}
				current+=1;
			}
			return skyLine_merged;

	    }
	    */
	    
	    public static void main(String[] args)
	    {
	    	int[][] buildings = {{1,2,1},{1,2,2},{1,2,3}};
	    	// {{1,2,1},{1,2,2},{1,2,3}}
	    	
	    	SkylineSolution slnForSkyline = new SkylineSolution();
	    	
	    	// List<int[]> skyline_merged = new ArrayList<int[]>();
	    	
	    	/*
	    	int [] element = {0,3};
	    	skyline_merged.add(element);
	    	
	    	int[] element1 = {2,3};
	    	skyline_merged.add(element1);
	    	
	    	int[] element2 = {5,0};
	    	skyline_merged.add(element2);
	    	*/
	    	
	    	List<int[]> skyLine = slnForSkyline.getSkyline(buildings);
	    	
	    	// List<int[]> solList = slnForSkyline.test(skyline_merged);
	    	
	    	
	    	for(int i = 0;  i < skyLine.size(); i++)
	    	{
	    		System.out.println(skyLine.get(i)[0]+","+skyLine.get(i)[1]);
	    	}
	    	
	    }
}

