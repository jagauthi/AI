package HW1;

public class Main {
	
	public static void main(String[] args) throws Exception
	{
		Viz v = new Viz();
		Solver solver = new Solver(v);
		GameState answer = solver.BFS();
	}
}
