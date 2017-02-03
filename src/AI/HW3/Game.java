package HW3;
import java.util.ArrayList;
import java.util.Random;

class Game
{
	static int populationSize = 20;
	static int numGenes = 291;
	static double mutationRate = 0.1;
	static double crossoverRate = 0.5;
	
	static double[] evolveWeights() throws Exception
	{
		// Create a random initial population
		Random r = new Random();
		Matrix population = new Matrix(populationSize, numGenes);
		for(int i = 0; i < populationSize; i++)
		{
			double[] chromosome = population.row(i);
			for(int j = 0; j < chromosome.length; j++)
				chromosome[j] = 0.03 * r.nextGaussian();
		}
		
		int numIterations = 0;
		while(Controller.doBattleNoGui(new ReflexAgent(), new NeuralAgent(population.row(0))) != -1) {
			System.out.println("Generation " + (numIterations+1));
			//mutate current population
			for(int y = 0; y < populationSize; y++) {
				for(int x = 0; x < numGenes; x++) {
					if(Math.random() <= mutationRate) {
						population.changeValue(x, y, (byte) Math.round(Math.random()));
					}
				}
			}
			
			//Natural selection: have two random ones fight, one wins and one dies
			for(int i = 0; i < 5; i++) {
				int fighter1 = (int) (Math.random() * population.rows());
				int fighter2 = (int) (Math.random() * population.rows());
				while( fighter1 == fighter2)
					fighter2 = (int) (Math.random() * population.rows());
				if(Controller.doBattleNoGui(new NeuralAgent(population.row(fighter1)), new NeuralAgent(population.row(fighter2))) == 1) {
					population.killRow(fighter2);
				}
				else {
					population.killRow(fighter1);
				}
			}
			
			//Make new babies to fill in the deaths (using crossover)
			int numReplacements = populationSize - population.rows();
			for(int i = 0; i < numReplacements; i++) {
				int parent1 = (int) (Math.random() * population.rows());
				int parent2 = (int) (Math.random() * population.rows());
				while( parent1 == parent2)
					parent2 = (int) (Math.random() * population.rows());
				double[] newChild = new double[291];
				for(int x = 0; x < numGenes; x++) {
					if (Math.random() <= crossoverRate) {
						newChild[x] = population.row(parent1)[x];
		            } 
					else {
						newChild[x] = population.row(parent2)[x];
		            }
				}
				population.addRow(newChild);
			}
			numIterations++;
			population.printMatrix();
		}
		return population.row(0);
	}

	public static void main(String[] args) throws Exception
	{
		/*
		Evolver evolver = new Evolver();
		Population finalPopulation = evolver.evolvePopulation();
		NeuralAgent myAgent = new NeuralAgent(finalPopulation.getFittest().genes);
		Controller.doBattle(new ReflexAgent(), myAgent);
		*/
		double[] w = evolveWeights();
		Controller.doBattle(new ReflexAgent(), new NeuralAgent(w));
	}

}
