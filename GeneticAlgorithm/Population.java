import java.util.Arrays;
import java.util.Random;

public class Population {
    private final int POPULATIONSIZE = 10;
    private Microorganism[] microorganisms = new Microorganism[POPULATIONSIZE];
    private double fittest = 0;

    public Population(){

    }


    public Population(int choice) {
        if(choice == 0) {
            //shotgun strategy
            for(int i = 0; i < microorganisms.length; i++) {
                microorganisms[i] = new Microorganism(0);
            }
        }
        if(choice == 1) {
            //blanket strategy
            for(int i = 0; i < microorganisms.length; i++) {
                microorganisms[i] = new Microorganism(1);
            }
        }
        calculateFitness();
    }

    public Population(Microorganism[] microorganisms) {
        this.microorganisms = microorganisms;
        calculateFitness();
    }


    public Microorganism getFittest() {
        double maxFit = Integer.MIN_VALUE;
        int maxFitIndex = 0;
        for (int i = 0; i < microorganisms.length; i++) {
            if (maxFit <= microorganisms[i].getFitness()) {
                maxFit = microorganisms[i].getFitness();
                maxFitIndex = i;
            }
        }
        fittest = microorganisms[maxFitIndex].getFitness();
        return microorganisms[maxFitIndex];
    }

    public Microorganism getSecondFittest() {
        int maxFit1 = 0, maxFit2 = 0;
        for (int i = 0; i < microorganisms.length; i++) {
            if (microorganisms[i].getFitness() > microorganisms[maxFit1].getFitness()) {
                maxFit2 = maxFit1;
                maxFit1 = i;
            }
            else if (microorganisms[i].getFitness() > microorganisms[maxFit2].getFitness()) {
                maxFit2 = i;
            }
        }
        return microorganisms[maxFit2];
    }

    public Microorganism randomSelection() {
        Random random = new Random();
        int randomIndex = random.nextInt(9);

        return microorganisms[randomIndex];
    }

    public void calculateFitness() {
        for (int i = 0; i < microorganisms.length; i++) {
            microorganisms[i].calculateFitness();
        }
        getFittest();
    }

    public int getPOPULATIONSIZE() {
        return POPULATIONSIZE;
    }

    public Microorganism[] getMicroorganisms() {
        return microorganisms;
    }

    public void setMicroorganisms(Microorganism[] microorganisms) {
        this.microorganisms = microorganisms;
    }

    @Override
    public String toString() {
        return "Population { " +
                "Microorganisms = \n" + Arrays.toString(microorganisms) +
                ", fittest=" + fittest +
                "}";
    }
}
