import java.util.Arrays;
import java.util.Random;

public class Individual {
    private final int GENESAMOUNT = 5;
    private final int GENELENGTH  = 12;
    private String[] genes = new String[5];
    private String[] fit = new String[]{"000000001100", "000000001110", "000000101100", "000010101110", "001000001100"};
    private int fitness = 0;

    public Individual(){
        for(int i = 0; i < GENESAMOUNT; i++) {
            Random rand = new Random();
            int randomGene = rand.nextInt(999) + 1;
            genes[i] = Integer.toBinaryString(randomGene);

            while (genes[i].length() < GENELENGTH) {
                genes[i] = "0" + genes[i];
            }
        }
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

    public String getGene(int i) {
        return this.genes[i];
    }

    public void setGenes(String[] genes) {
        this.genes = genes;
    }

    public void setGene(String gene, int i) {
        this.genes[i] = gene;
    }

    public String[] getFit() {
        return fit;
    }

    public void setFit(String[] fit) {
        this.fit = fit;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    @Override
    public String toString() {
        return "Individual{" +
                "GENESAMOUNT=" + GENESAMOUNT +
                ", GENELENGTH=" + GENELENGTH +
                ", genes=" + Arrays.toString(genes) +
                ", fit=" + Arrays.toString(fit) +
                ", fitness=" + fitness +
                '}';
    }
}
