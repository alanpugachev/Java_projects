import java.util.Arrays;
import java.util.Random;

public class Population {
    private final int POPULATIONSIZE = 10;
    private Microorganism[] microorganisms = new Microorganism[POPULATIONSIZE];
    private int fittest = 0;


    public Population() {
        for(int i = 0; i < microorganisms.length; i++) {
            microorganisms[i] = new Microorganism();
        }
        calculateFitness();
    }

    public Population(Microorganism[] microorganisms) {
        this.microorganisms = microorganisms;
        calculateFitness();
    }


    public Microorganism getFittest() {
        int maxFit = Integer.MIN_VALUE, maxFitIndex = 0;
        for (int i = 0; i < microorganisms.length; i++) {
            if (maxFit <= microorganisms[i].getFitness()) {
                maxFit = microorganisms[i].getFitness();
                maxFitIndex = i;
            }
        }
        fittest = microorganisms[maxFitIndex].getFitness();
        return microorganisms[maxFitIndex];
    }

    public void calculateFitness() {
        for (int i = 0; i < microorganisms.length; i++) {
            microorganisms[i].calculateFitness();
        }
        getFittest();
    }

    @Override
    public String toString() {
        return "Population{" +
                "Microorganisms=" + Arrays.toString(microorganisms) +
                ", fittest=" + fittest +
                '}';
    }
}
