import java.util.Arrays;
import java.util.Random;

public class Microorganism {
    private final int GENESAMOUNT = 5; //function has 5 max points
    private final int GENELENGTH  = 10; //max gene length (524 = 1000001100)
    private double fitness = 0;
    private String[] genes = new String[5]; //array for generated genes
    private String[] fit = new String[]{"1100", "1110", "101100", "10101110", "1000001100"}; //max of 2x^4 + 12 on [0, 1, 2, 3, 4]


    public Microorganism(){
        for(int i = 0; i < GENESAMOUNT; i++) {
            Random rand = new Random();
            int randomGene = rand.nextInt(999) + 1; //max gene length = 10 => 1023 is max value => range = ~1000
            genes[i] = Integer.toBinaryString(randomGene); //generate random int in binary system

            while (genes[i].length() < GENELENGTH) {
                genes[i] = "0" + genes[i];
            }
        }
        calculateFitness();
    }

    public void calculateFitness() {
        for (int i = 0; i < GENESAMOUNT; i++) {
            if (genes[i].equals(fit[i])) {
                ++fitness;
            }
            else {
                String geneTempByteCodeString = genes[i];
                int geneTempByteCodeToInt = Integer.parseInt(geneTempByteCodeString, 2);

                String fitTempByteCodeString = fit[i];
                int fitTempByteCodeToInt = Integer.parseInt(fitTempByteCodeString, 2);

                double tempFitness = (double)(1.0 / Math.abs(fitTempByteCodeToInt - geneTempByteCodeToInt));
                fitness += tempFitness;
                //System.out.println(i + "-gene fitness = " + tempFitness);
            }
        }
    }


    public double getFitness() {
        return fitness;
    }

    public int getGENESAMOUNT() {
        return GENESAMOUNT;
    }

    public int getGENELENGTH() {
        return GENELENGTH;
    }

    public String[] getGenes() {
        return genes;
    }

    public String[] getFit() {
        return fit;
    }


    //additional function
    //made for replace genes because this class hasn't constructor with arguments
    public void setGenes(String[] genes) {
        this.genes = genes;
    }

    @Override
    public String toString() {
        return "Microorganism { " +
                "genes=" + Arrays.toString(genes) +
                ", fitness=" + fitness +
                "} \n";
    }
}
