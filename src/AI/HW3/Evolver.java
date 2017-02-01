package HW3;

import java.util.ArrayList;
import java.util.Random;

public class Evolver {

	int populationSize = 50;
	int tournSize = 10;
	int numEvolutions = 20;
	double mutationRate = 0.015;
	ArrayList<Population> populations;
	
	public Evolver()
	{
		populations = new ArrayList<Population>();
		Population initial = new Population(populationSize, true);
		populations.add(initial);
	}
	
	public Population evolvePopulation()
	{
		int numIterations = 0;
		Population finalPopulation = new Population(populationSize, false);
		while(numIterations < numEvolutions) {
			System.out.println("Generation " + (numIterations+1));
			Population newPopulation = new Population(populationSize, false);
			
			//Populate the new population with new children
			System.out.println("\tPopulating the new generation...");
			for(int i = 0; i < newPopulation.size(); i++) {
				Individual i1 = getNewParent(populations.get(numIterations));
				Individual i2 = getNewParent(populations.get(numIterations));
				Individual newChild = crossover(i1, i2);
				newPopulation.saveIndividual(i, newChild);
			}
			
			//Slightly mutate the population
			System.out.println("\tMutating the population...");
			for(int i = 0; i < newPopulation.size(); i++) {
				mutate(newPopulation.get(i));
			}
			finalPopulation = newPopulation;
			populations.add(finalPopulation);
			numIterations++;
		}
		return finalPopulation;
	}
	
	public Individual getNewParent(Population pop)
	{
		Population tournament = new Population(tournSize, false);
        for (int i = 0; i < tournSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.get(randomId));
        }
        Individual fittest = tournament.getFittest();
        return fittest;
	}
	
	public Individual crossover(Individual i1, Individual i2) {
        Individual newSol = new Individual();
        for (int i = 0; i < i1.size(); i++) {
            if (Math.random() <= 0.5) {
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
