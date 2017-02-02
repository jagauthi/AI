package HW3;
import java.util.Random;

class Game
{
	int populationSize = 20;
	int numGenes = 291;
	double mutationRate = 0.1;
	double crossoverRate = 0.5;
	
	double[] evolveWeights() throws Exception
	{
		// Create a random initial population
		Random r = new Random();
		Matrix population = new Matrix(populationSize, numGenes);
		for(int i = 0; i < 100; i++)
		{
			double[] chromosome = population.row(i);
			for(int j = 0; j < chromosome.length; j++)
				chromosome[j] = 0.03 * r.nextGaussian();
		}
		
		while(Controller.doBattleNoGui(new ReflexAgent(), new NeuralAgent(population.row(0))) != -1) {
			//mutate current population
			for(int y = 0; y < populationSize; y++) {
				for(int x = 0; x < numGenes; x++) {
					if(Math.random() <= mutationRate) {
						population.changeValue(x, y, (byte) Math.round(Math.random()));
					}
				}
			}
			//Natural selection: have two random ones fight, one wins and one dies
			int[] deaths = new int[5];
			for(int i = 0; i < 5; i++)
			{
				if(Controller.doBattleNoGui(new NeuralAgent(), new NeuralAgent())) == 1) {
					
				}
				else {
					
				}
			}
			
			//Make new babies to fill in the deaths (using crossover)
			for(int i = 0; i < deaths.length; i++)
			{
				
			}
			
		}

		return population.row(0);
	}

	public void mutate(Individual indiv) {
        for (int i = 0; i < indiv.size(); i++) {
            if (Math.random() <= mutationRate) {
                byte gene = (byte) Math.round(Math.random());
                indiv.setGene(i, gene);
            }
        }
    }

	public static void main(String[] args) throws Exception
	{
		Evolver evolver = new Evolver();
		Population finalPopulation = evolver.evolvePopulation();
		NeuralAgent myAgent = new NeuralAgent(finalPopulation.getFittest().genes);
		Controller.doBattle(new ReflexAgent(), myAgent);
		
		//double[] w = evolveWeights();
		//Controller.doBattle(new ReflexAgent(), new NeuralAgent(w));
	}

}
