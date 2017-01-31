package AI.HW3;

import java.util.ArrayList;
import java.util.Random;

public class Evolver {

	NeuralNet nn;
	int numIterations;
	
	public Evolver()
	{
		numIterations = 0;
		nn = new NeuralNet();
		nn.layers.add(new LayerTanh(10, 2));
		nn.init(new Random());
	}
	
	public void evolvePopulation()
	{
		while(numIterations < 1000)
		{
			//Promote diversity within the population.
			
			
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
