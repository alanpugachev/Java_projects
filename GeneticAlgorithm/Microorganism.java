import java.util.Arrays;
import java.util.Random;

public class Microorganism {
    private final int GENESAMOUNT = 5;
    private final int GENELENGTH  = 10;
    private double fitness = 0;
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
            else {
                String geneTempByteCodeString = genes[i];
                int geneTempByteCodeToInt = Integer.parseInt(geneTempByteCodeString, 2);

                String fitTempByteCodeString = fit[i];
                int fitTempByteCodeToInt = Integer.parseInt(fitTempByteCodeString, 2);

                double tempFitness = (double)(1.0 / Math.abs(fitTempByteCodeToInt - geneTempByteCodeToInt) * 100);
                fitness += tempFitness;
                System.out.println(tempFitness);
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


    public void setGenes(String[] genes) {
        this.genes = genes;
    }

    @Override
    public String toString() {
        return "Microorganism{" +
                "genes=" + Arrays.toString(genes) +
                ", fitness=" + fitness +
                '}';
    }
}
