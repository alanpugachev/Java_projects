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
