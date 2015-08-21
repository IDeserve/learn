
package coding;

import java.util.ArrayList;

import java.util.Comparator;
import java.util.PriorityQueue;

public class graph {	

	
	class graph_node
	{
		int node_id;
		graph_node next;
		int parentDist;
		
		graph_node(int id)
		{
			node_id = id;
			next = null;
		}
		
		graph_node(int id, int dist)
		{
			node_id = id;
			next = null;
			parentDist = dist;
		}
	}

	private static final int INT_MAX = 100;
	
	ArrayList<graph_node> nodeList;
	
	public graph()
	{
		nodeList = new ArrayList<graph_node>();
	}
	
	
	void addNode(int id)
	{
		graph_node node = new graph_node(id);
		nodeList.add(node);
	}
	
	void addEdge(int id1, int id2, int dist)
	{
		int i = 0;
		for (i = 0; i < nodeList.size(); i++)
		{
			if (nodeList.get(i).node_id == id1)
			{
				break;
			}
		}
		if (i == nodeList.size())
		{
			return;
		}
		graph_node node1 = nodeList.get(i);
		graph_node node2 = new graph_node(id2, dist);
		
		node2.next = node1.next; 
		node1.next = node2;
	}
	
	public class queueNode 
	{
		int node_id;
		int distFromSrc;
		public queueNode(int id, int dist)
		{
			node_id = id;
			distFromSrc = dist;
		}
	}
	
	public class queueNodeComparator implements Comparator<queueNode>
	{
	    @Override
	    public int compare(queueNode x, queueNode y)
	    {
	        // Assume neither string is null. Real code should
	        // probably be more robust
	        // You could also just return x.length() - y.length(),
	        // which would be more efficient.
	        if (x.distFromSrc < y.distFromSrc)
	        {
	            return -1;
	        }
	        if (x.distFromSrc > y.distFromSrc)
	        {
	            return 1;
	        }
	        return 0;
	    }
	}
	
	
	void printGraph()
	{
		for (int i = 0; i < nodeList.size(); i++)
		{
			graph_node curr = nodeList.get(i);
			
			while (curr != null)
			{
				System.out.print(curr.node_id+"("+curr.parentDist+")"+"->");
				curr = curr.next;
			}
			System.out.print("Null");
			System.out.println();
		}
		
		Comparator<queueNode> comparator = new queueNodeComparator();
        PriorityQueue<queueNode> queue = 
            new PriorityQueue<queueNode>(10, comparator);
            
        queue.add(new queueNode(1,2));
        queue.add(new queueNode(2,3));
        queue.add(new queueNode(3,1));
        while (queue.size() != 0)
        {
            System.out.println(queue.remove().node_id);
        }
	}
	
	
	public graph_node findGraphNode(int curr_node_id)
	{
		for(int i = 0; i < nodeList.size(); i++)
		{
			if(nodeList.get(i).node_id == curr_node_id)
			{
				return nodeList.get(i);
			}
		}
	
		return null;
	}
	
	void updateQueue(PriorityQueue queue, int node_id, int oldDist, int newDist)
	{
		// this step removes the old node with non-optimum distance. 
		// This is the first step for updating new shortest possible distance  
		queue.remove(new queueNode(node_id, oldDist));

		// the new possible distance also has to be updated in the priority queue
		queue.add(new queueNode(node_id, newDist));

	}
	
	void findShortest_Dijkstra (int src_id)
	{
		
		Comparator<queueNode> comparator = new queueNodeComparator();
        PriorityQueue<queueNode> queue = 
            new PriorityQueue<queueNode>(10, comparator);
        
            
        // queue.remove(arg0)    
        graph_node temp = null;    
        boolean[] visited = new boolean[nodeList.size()];
        
        int[] parent = new int[nodeList.size()];
        int[] distance = new int[nodeList.size()];
        		
        for(int i = 0; i < nodeList.size(); i++)
        {
        	visited[i] = false;
        	parent[i] = -1;
        	distance[i] = INT_MAX;
        	
        }
            
        queue.add(new queueNode(src_id, 0));
                
        while (!queue.isEmpty())
        {
        	queueNode curr_node = queue.remove();
        	distance[curr_node.node_id] = curr_node.distFromSrc;
        	
        	visited[curr_node.node_id] = true;
        	
        	graph_node curr_g_node = findGraphNode(curr_node.node_id);
        	
        	graph_node neighbor_node = (curr_g_node == null) ? null : curr_g_node.next;
        	
        	while (neighbor_node != null)
        	{
        		if (!visited[neighbor_node.node_id])
        		{
        			if ((distance[curr_node.node_id] + neighbor_node.parentDist) < distance[neighbor_node.node_id])
        			{
        				int oldDistance = distance[neighbor_node.node_id];
        				int newDistance = distance[curr_node.node_id] + neighbor_node.parentDist;
        				distance[neighbor_node.node_id] = newDistance;
        				
        				updateQueue(queue, neighbor_node.node_id, oldDistance, newDistance);
        			}
        		}
        		neighbor_node = neighbor_node.next;
        	}
        	
        }
       
        for (int i = 0; i < distance.length; i++)
        {
        	System.out.println(distance[i]);
        }
		return;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		graph graphObj = new graph();

		graphObj.addNode(0);
		graphObj.addNode(1);
		graphObj.addNode(2);
		graphObj.addNode(3);
		graphObj.addNode(4);
		graphObj.addNode(5);
		graphObj.addNode(6);
		
		
		graphObj.addEdge(0,2,1);
		graphObj.addEdge(0,1,2);
		graphObj.addEdge(1,2,3);
		graphObj.addEdge(2,3,5);
		
		
		graphObj.addEdge(2,6,4);
		graphObj.addEdge(3,5,6);
		graphObj.addEdge(5,3,7);
		graphObj.addEdge(3,1,6);
		
		
		graphObj.printGraph();
	
		graphObj.findShortest_Dijkstra(0);
	}

}
