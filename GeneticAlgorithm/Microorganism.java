import java.util.Arrays;
import java.util.Random;

public class Microorganism {
    private final int GENESAMOUNT = 5;
    private final int GENELENGTH  = 10;
    private int fitness = 0;
    private String[] genes = new String[5];
    private String[] fit = new String[]{"0000001100", "0000001110", "0000101100", "0010101110", "1000001100"};


    public Microorganism(){
        for(int i = 0; i < GENESAMOUNT; i++) {
            Random rand = new Random();
            int randomGene = rand.nextInt(999) + 1;
            genes[i] = Integer.toBinaryString(randomGene);

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
        }
    }


    public int getFitness() {
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


    public void setGenes(String[] genes) {
        this.genes = genes;
    }

    @Override
    public String toString() {
        return "Individual{" +
                "genes=" + Arrays.toString(genes) +
                ", fitness=" + fitness +
                '}';
    }
}
