package HW2;

import java.util.Comparator;

public class FCostComparator implements Comparator<Node>
{
	public int compare(Node a, Node b)
	{
		if(a.fCost < b.fCost)
			return -1;
		else if(a.fCost > b.fCost)
			return 1;
		else
			return 0;
	}
}