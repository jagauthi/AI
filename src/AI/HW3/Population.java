package HW3;

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

    public Individual getIndividual(int index) {
        return individuals[index];
    }

    public Individual getFittest() {
        //Do something
    	return new Individual();
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

}
