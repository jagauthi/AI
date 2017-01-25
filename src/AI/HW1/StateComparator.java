package HW1;

import java.util.Comparator;

public class StateComparator implements Comparator<GameState>
{
	public int compare(GameState a, GameState b)
	{
		for(int i = 0; i < 22; i++)
		{
			if(a.state[i] < b.state[i])
				return -1;
			else if(a.state[i] > b.state[i])
				return 1;
		}
		return 0;
	}
}