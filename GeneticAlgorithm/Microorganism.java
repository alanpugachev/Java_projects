import java.util.Arrays;
import java.util.Random;

public class Microorganism {
    private final int GENESAMOUNT = 5; //function has 5 max points
    private final int GENELENGTH  = 10; //max gene length (524 = 1000001100)
    private double fitness;
    private String[] genes = new String[5]; //array for generated genes
    private String[] fit = new String[]{"1100", "1110", "101100", "10101110", "1000001100"}; //max of 2x^4 + 12 on [0, 1, 2, 3, 4]

    public Microorganism() {
        Random r = new Random();

        for (int i = 0; i < genes.length; i++) {
            int tmp = r.nextInt(999) + 1;
            genes[i] = Integer.toString(tmp, 2);
            while (genes[i].length() < GENELENGTH) {
                genes[i] = "0" + genes[i];
            }
        }

        fitness = 0;
    }

    public Microorganism(int b) {
        Random r = new Random();

        for(int i = 0; i < genes.length; i++) {
            int offset = r.nextInt(3);
            int offsetedGene = Integer.parseInt(fit[i], 2);
            offsetedGene += offset;
            genes[i] = Integer.toString(offsetedGene, 2);

            while (genes[i].length() < GENELENGTH) {
                genes[i] = "0" + genes[i];
            }
        }

        fitness = 0;
    }


    public void calculateFitness() {
        fitness = 0;
        int[] compareGenes = new int[5];

        for (int i = 0; i < genes.length; i++) {
            compareGenes[i] = Math.abs(Integer.parseInt(genes[i], 2) - Integer.parseInt(fit[i], 2));
        }

        for (int i = 0; i < genes.length; i++) {
            try {
                if (compareGenes[i] == 0)
                    fitness += 1;
                else {
                    fitness += (1.0) / (compareGenes[i] * 1.0);
                }
            } catch (ArithmeticException e) {
                fitness += 1;
            }
        }
    }

    public int getMax() {
        return Integer.parseInt(genes[0], 2);
    }

    public int getGENESAMOUNT() {
        return GENESAMOUNT;
    }

    public int getGENELENGTH() {
        return GENELENGTH;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public String[] getGenes() {
        return genes;
    }

    public void setGenes(String[] genes) {
        this.genes = genes;
    }

    public String[] getFit() {
        return fit;
    }

    public void setFit(String[] fit) {
        this.fit = fit;
    }

    @Override
    public String toString() {
        return Arrays.toString(genes) + "\n";
    }
}