package HW3;

import java.util.ArrayList;
import java.util.Random;

public class Evolver {

	int numIterations;
	ArrayList<Population> populations;
	
	public Evolver()
	{
		numIterations = 0;
		populations = new ArrayList<Population>();
		Population initial = new Population(10, true);
		populations.add(initial);
	}
	
	public void evolvePopulation()
	{
		while(numIterations < 1000)
		{
			//Promote diversity within the population.
			Population newPopulation = new Population(10, false);
			
			//Select the "most fit" chromosomes to survive (or kill off the "least fit" ones).
			ArrayList<IAgent> agents = new ArrayList<IAgent>();
			int[] wins = new int[agents.size()];
			try {
				Controller.rankAgents(agents, wins, true);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			
			//Replenish the population.
			
			
			numIterations++;
		}
	}
}
