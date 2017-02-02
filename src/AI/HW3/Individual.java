package HW3;


public class Individual {
	
	static int geneLength = 291;
    double[] genes;
    NeuralAgent agent;
    
	public Individual()
	{
		genes = new double[geneLength];
		agent = new NeuralAgent(genes);
	}
    
    public double getGene(int index) {
        return genes[index];
    }

    public void setGene(int index, double value) {
        genes[index] = value;
    }
    
    public int size() {
        return genes.length;
    }
    
    public void generateAgent()
    {
    	agent = new NeuralAgent(genes);
    }

    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < genes.length; i++) {
            geneString += getGene(i);
        }
        return geneString;
    }
}
