package HW2;

import java.util.Comparator;

public class StateComparator implements Comparator<Node>
{
	public int compare(Node a, Node b)
	{
		for(int i = 0; i < 22; i++)
		{
			/*
			if(a.state[i] < b.state[i])
				return -1;
			else if(a.state[i] > b.state[i])
				return 1;
				*/
		}
		return 0;
	}
}