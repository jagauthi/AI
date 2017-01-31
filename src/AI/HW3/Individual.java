package HW3;

public class Individual {
	
	static int geneLength = 291;
    private double[] genes;
    
	public Individual()
	{
		genes = new double[geneLength];
	}
    
    public double getGene(int index) {
        return genes[index];
    }

    public void setGene(int index, double value) {
        genes[index] = value;
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
