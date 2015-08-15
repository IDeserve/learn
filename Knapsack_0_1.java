package algos;

import java.util.ArrayList;

public class Knapsack_0_1 
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
	
	
	public class listWithBenefit
	{
		ArrayList<Integer> listItems;
		int benefit;
		
		public listWithBenefit(int benefit)
		{
			listItems = new ArrayList();
			this.benefit = benefit;
		}
		
		
	  public listWithBenefit(listWithBenefit obj) 
	  {
				listItems = new ArrayList();
				for(int i = 0; i < obj.listItems.size();i++)
				{
					listItems.add(obj.listItems.get(i));
				}
				this.benefit = obj.benefit;
	  }	  
		
	}
	
	listWithBenefit findOptimalItems(int w, int n, int [] val, int [] weight, listWithBenefit[][] optimalKnapsack)
    {
		// nothing can be added to Knapsack. 
		if ( w == 0 || n == weight.length)
		{
			 optimalKnapsack[w][n] = new listWithBenefit(0); 
			 return optimalKnapsack[w][n]; 
				
		}
		    
		// this node can not be added to Knapsack.
		if(weight[n] > w)
		   return (optimalKnapsack[w][n+1] == null) ? findOptimalItems(w, n+1, val, weight, optimalKnapsack) : optimalKnapsack[w][n+1];		
		
		// compute optimal knapsack if we want to include this item in it.
		listWithBenefit include_n_benefit = (optimalKnapsack[w-weight[n]][n+1] == null) ? 
											new listWithBenefit(findOptimalItems(w-weight[n], n+1, val, weight, optimalKnapsack))
											: new listWithBenefit (optimalKnapsack[w-weight[n]][n+1]);
		
		//  now include this item and its benefit in the knapsack	        
		include_n_benefit.listItems.add(weight[n]);
		include_n_benefit.benefit += val[n];
		
		// compute optimal knapsack if we do not want to include this item in it.
		listWithBenefit exclude_n_benefit = (optimalKnapsack[w][n+1] == null) ? 
											  new listWithBenefit(findOptimalItems(w, n+1, val, weight, optimalKnapsack)) 
											: new listWithBenefit (optimalKnapsack[w][n+1]);
				 
		// check which knapsack is gives us better benefit?
		if(include_n_benefit.benefit > exclude_n_benefit.benefit)
		{
			optimalKnapsack[w][n] = new listWithBenefit (include_n_benefit); 
			return include_n_benefit;
		}
		
		optimalKnapsack[w][n] = new listWithBenefit (exclude_n_benefit);
		return exclude_n_benefit;
    }

	public static void main(String[] args)
	{
		int [] val = {3,7,2,9};
		int [] weight = {2,2,4,5};
		
		int weightLimit = 10;
		listWithBenefit [][] optimalKnapsack = new listWithBenefit[weightLimit + 1][val.length + 1];
		
		
		// ArrayList<Integer> optimumWeights = new ArrayList();
		Knapsack_0_1 obj = new Knapsack_0_1();
		listWithBenefit sln = obj.findOptimalItems(weightLimit, 0, val, weight, optimalKnapsack);
		
		System.out.println("Maximum benefit is"+sln.benefit);
		System.out.println("And the weights to be included are");
		
		for(int i = 0; i < sln.listItems.size(); i++)
			System.out.println(sln.listItems.get(i));
		
	}
}
		
		
