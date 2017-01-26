package HW2;

import java.util.Comparator;

public class CostComparator implements Comparator<Node>
{
	public int compare(Node a, Node b)
	{
		if(a.cost < b.cost)
			return -1;
		else if(a.cost > b.cost)
			return 1;
		else
			return 0;
	}
}