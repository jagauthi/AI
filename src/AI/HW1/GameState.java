package HW1;

public class GameState
{
	GameState prev;
	byte[] state;

	GameState(GameState _prev)
	{
		prev = _prev;
		if(prev != null)
			state = prev.state.clone();
		else
			state = new byte[22];
	}
	
	public String getStateAsString()
	{
		String result = "";
		for(int i = 0; i < 22; i += 2)
		{
			result += "(" + state[i] + "," + state[i+1] + ") ";
		}
		return result;
	}
}

  