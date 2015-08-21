package coding;

import java.util.ArrayList;

public class graph {

	public class graph_node
	{
		int node_id;
		graph_node next;
		
		public graph_node(int id)
		{
			this.node_id = id;
		}
	}
	
	ArrayList<graph_node> nodeList;
	
	public graph()
	{
		nodeList = new ArrayList<graph_node>();
	}
	
	
	public graph_node addNode(int id)
	{
		graph_node node = new graph_node(id);
		
		nodeList.add(0, node);

		return nodeList.get(0); 
	}
	
	// adding edge from id1 to id2
	// if either of these does not exist then create and add
	public void addEdge(int id1, int id2)
	{
		
		// search for id1 and id2
		boolean node1_found = false, node2_found = false;
		
		graph_node node1  = null, node2 = null;
		for (int i = 0; i < nodeList.size(); i++)
		{
			if (nodeList.get(i).node_id == id1)
			{
				node1_found = true;
				node1 = nodeList.get(i);
			}
			if (nodeList.get(i).node_id == id2)
			{
				node2_found = true;
				node2 = nodeList.get(i);
			}
			
			if (node1_found && node2_found) break;
		}
		
		
		if (!node1_found)
		{
			node1 = this.addNode(id1); 
		}
		
		if (!node2_found)
		{
			node2 = this.addNode(id2); 
		}
		
		graph_node temp = new graph_node(id2);
		temp.next = node1.next;
		node1.next = temp;
		
		return;
	}
	
	public graph_node getNode(int id)
	{
		for(int i = 0; i < nodeList.size(); i++)
		{
			if (id == nodeList.get(i).node_id)
			{
				return nodeList.get(i);
			}
			
		}
		
		return null;
	}
	
	
	public void printGraph()
	{
		for (int i = 0; i < nodeList.size(); i++)
		{
			graph_node temp = nodeList.get(i);
			
			while (temp != null)
			{
				System.out.print(" ->"+temp.node_id);
				temp = temp.next;
			}
			System.out.println();
		}
	}
	

}
