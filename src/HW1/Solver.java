import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;

public class Solver {
	
	TreeSet<GameState> S;
	LinkedList<GameState> Q;
	Viz v;
	int[] map;
	
	public Solver(Viz v)
	{
		this.v = v;
		StateComparator comp = new StateComparator();
		S = new TreeSet<GameState>(comp);
	}

	public GameState BFS()
	{
		Q = new LinkedList<GameState>();
		
		GameState root = new GameState(null);
		Q.add(root);
        
	    while(Q.size() > 0) {
	    	GameState current = Q.pop();
	    	if(isGoal(current)) {
	    		getAnswer(current);
	    		return current;
	    	}
	    	
	    	for(int i = 0; i < 22; i += 2) {
	    		GameState n1 = new GameState(current);
	    		GameState n2 = new GameState(current);
	    		GameState n3 = new GameState(current);
	    		GameState n4 = new GameState(current);
	    		
    			n1.state[i] += 1;
	    		if( ( !S.contains(n1) ) && ( isValidState(n1) ) ) {
	    			S.add(n1);
	    			Q.add(n1);
	    			v.getNewState(n1);
	    		}
	    		
    			n2.state[i] -= 1;
	    		if( (!S.contains(n2) ) && ( isValidState(n2) ) ) {
	    			S.add(n2);
	    			Q.add(n2);
	    			v.getNewState(n2);
	    		}
	    		
    			n3.state[i+1] += 1;
	    		if( (!S.contains(n3) ) && ( isValidState(n3) ) ) {
	    			S.add(n3);
	    			Q.add(n3);
	    			v.getNewState(n3);
	    		}
	    		
    			n4.state[i+1] -= 1;
	    		if( (!S.contains(n4) ) && ( isValidState(n4) ) ) {
	    			S.add(n4);
	    			Q.add(n4);
	    			v.getNewState(n4);
	    		}
	    	}
	    }
	    return null;
	}
	
	public boolean isValidState(GameState state)
	{
		int[] mapHolder = {
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			1, 1, 1, 0, 0, 0, 0, 1, 1, 1,
			1, 1, 0, 0, 0, 0, 0, 0, 1, 1,
			1, 0, 0, 0, 1, 0, 0, 0, 0, 1,
			1, 0, 0, 1, 1, 0, 0, 0, 0, 1,
			1, 0, 0, 0, 0, 0, 0, 0, 0, 1,
			1, 0, 0, 0, 0, 0, 0, 0, 0, 1,
			1, 1, 0, 0, 0, 0, 0, 0, 1, 1,
			1, 1, 1, 0, 0, 0, 0, 1, 1, 1,
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1
		};
		
		map = mapHolder;
		
		if( (!shape(state.state, 0, 1, 3, 2, 3, 1, 4, 2, 4)) || (!shape(state.state, 1, 1, 5, 1, 6, 2, 6)) || 
				(!shape(state.state, 2, 2, 5, 3, 5, 3, 6)) || (!shape(state.state, 3, 3, 7, 3, 8, 4, 8)) || 
				(!shape(state.state, 4, 4, 7, 5, 7, 5, 8)) || (!shape(state.state, 5, 6, 7, 7, 7, 6, 8)) || 
				(!shape(state.state, 8, 8, 5, 8, 6, 7, 6)) || (!shape(state.state, 9, 6, 2, 6, 3, 5, 3)) ||
				(!shape(state.state, 6, 5, 4, 5, 5, 5, 6, 4, 5)) || (!shape(state.state, 7, 6, 4, 6, 5, 6, 6, 7, 5)) ||
				(!shape(state.state, 10, 5, 1, 6, 1, 5, 2)) ) {
			return false;
		}
		return true;
	}
	
	public boolean shape(byte[] state, int id, int x1, int y1, int x2, int y2, int x3, int y3)
	{
		if( !b(state[2 * id] + x1, state[2 * id + 1] + y1) )
			return false;
		if( !b(state[2 * id] + x2, state[2 * id + 1] + y2) )
			return false;
		if( !b(state[2 * id] + x3, state[2 * id + 1] + y3) )
			return false;
		
		return true;
	}
	
	// Draw a 4-block piece
	public boolean shape(byte[] state, int id, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4)
	{
		if( (!shape(state, id, x1, y1, x2, y2, x3, y3)) || (!b(state[2 * id] + x4, state[2 * id + 1] + y4)) ) {
			return false;
		}
		
		return true;
	}
	
	// Draw a block
	public boolean b(int x, int y) {
		if(x < 0 || x > 10 || y < 0 || y > 10) {
			return false;
		}
		
		if(map[y*10 + x] == 1) {
			return false;
		}
		else {
			map[y*10 + x] = 1;
			return true;
		}
	}
	
	public boolean isGoal(GameState state)
	{
		if(state.state[0] == 4 && state.state[1] == -2)
			return true;
		else
			return false;
	}
	
	public void getAnswer(GameState state)
	{
		GameState current = state;
		GameState previous = current.prev;
		ArrayList<String> answer = new ArrayList<String>();
		while(previous != null)
		{
			answer.add(current.getStateAsString());
			current = previous;
			previous = current.prev;
		}
		writeToFile("results.txt", answer);
	}
	
	public void writeToFile(String fileName, ArrayList<String> answer)
	{
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(int i = answer.size()-1; i >= 0; i--)
            {
                bufferedWriter.write(answer.get(i));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}



