package HW3;


import java.util.ArrayList;
import java.util.Random;

public class Evolver {

	int populationSize = 20;
	double mutationRate = 0.2; //Slightly higher than normal mutation rate
	double crossoverRate = 0.5;
	ArrayList<Population> populations;
	Population lastPopulation;
	
	public Evolver()
	{
		populations = new ArrayList<Population>();
		Population initial = new Population(populationSize, true);
		lastPopulation = initial;
		populations.add(initial);
	}
	
	public Population evolvePopulation()
	{
		int numIterations = 0;
		Population finalPopulation = new Population(populationSize, true);
		try {
			while(Controller.doBattleNoGui(new NeuralAgent(finalPopulation.individuals[0].genes), new ReflexAgent()) != 1) {
			//while(numIterations < numEvolutions) {
				System.out.println("Generation " + (numIterations+1));
				Population newPopulation = new Population(populationSize, false);
				
				//Populate the new population with new children, gotten by crossing 2 random (favored) parents
				for(int i = 0; i < newPopulation.size(); i++) {
					Individual i1 = getNewParent(lastPopulation);
					Individual i2 = getNewParent(lastPopulation);
					Individual newChild = crossover(i1, i2);
					newPopulation.saveIndividual(i, newChild);
				}
				
				//Mutate some the population for diversity
				for(int i = 0; i < newPopulation.size(); i++) {
					mutate(newPopulation.get(i));
				}
				lastPopulation = finalPopulation;
				finalPopulation = newPopulation;
				populations.add(finalPopulation);
				numIterations++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalPopulation;
	}
	
	public Individual getNewParent(Population pop)
	{
		Population tournament = new Population(3, false);
        for (int i = 0; i < 3; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.get(randomId));
        }
        Individual fittest = tournament.getFittest();
        return fittest;
	}
	
	public Individual crossover(Individual i1, Individual i2) {
        Individual newSol = new Individual();
        for (int i = 0; i < i1.size(); i++) {
            if (Math.random() <= crossoverRate) {
                newSol.setGene(i, i1.getGene(i));
            } else {
                newSol.setGene(i, i2.getGene(i));
            }
        }
        return newSol;
    }

    public void mutate(Individual indiv) {
        for (int i = 0; i < indiv.size(); i++) {
            if (Math.random() <= mutationRate) {
                byte gene = (byte) Math.round(Math.random());
                indiv.setGene(i, gene);
            }
        }
    }
}
