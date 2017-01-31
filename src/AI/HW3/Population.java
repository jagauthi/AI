package HW3;

import java.util.ArrayList;
import java.util.Random;

public class Population {
	
	Individual[] individuals;

    public Population(int populationSize, boolean initialise) {
        individuals = new Individual[populationSize];
        if (initialise) {
            for (int i = 0; i < individuals.length; i++) {
                saveIndividual(i, generateRandomIndividual());
            }
        }
    }

    public Individual get(int index) {
        return individuals[index];
    }

    public Individual getFittest() {
    	ArrayList<IAgent> agents = new ArrayList<IAgent>();
    	int[] wins = new int[individuals.length];
        for(int i = 0; i < individuals.length; i++) {
        	individuals[i].generateAgent();
        	agents.add(individuals[i].agent);
        }
        
        try {
			Controller.rankAgents(agents, wins, false);
		} 
        catch (Exception e) {
			e.printStackTrace();
		}
        
    	return individuals[findWinner(agents, wins)];
    }
    
    public int findWinner(ArrayList<IAgent> agents, int[] wins) {
    	int winnerIndex = 0;
    	int winnerAmount = 0;
    	for(int i = 0; i < agents.size(); i++) {
    		if(wins[i] > winnerAmount) {
    			winnerIndex = i;
    			winnerAmount = wins[i];
    		}
    	}
    	return winnerIndex;
    }
    
    public Individual generateRandomIndividual() {
    	Individual newIndividual = new Individual();
    	Random r = new Random();

		double dev = Math.max(0.3, 1.0 / Individual.geneLength);
        for (int i = 0; i < individuals.length; i++) {
        	newIndividual.setGene(i, dev * r.nextGaussian());
        }
        return newIndividual;
    }

    public void saveIndividual(int index, Individual indiv) {
        individuals[index] = indiv;
    }
    
    public int size()
    {
    	return individuals.length;
    }

}
