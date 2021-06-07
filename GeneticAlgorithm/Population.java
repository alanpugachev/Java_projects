import java.util.Arrays;

public class Population {
    private final int POPULATIONSIZE = 10;
    public Individual[] individuals = new Individual[POPULATIONSIZE];
    private int fittest = 0;

    public Population(){
        for(int i = 0; i < individuals.length; i++){
            individuals[i] = new Individual();
        }
    }

    public Individual getFittest() {
        int maxFit = Integer.MIN_VALUE, maxFitIndex = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (maxFit <= individuals[i].getFitness()) {
                maxFit = individuals[i].getFitness();
                maxFitIndex = i;
            }
        }
        fittest = individuals[maxFitIndex].getFitness();
        return individuals[maxFitIndex];
    }

    public Individual getSecondFittest() {
        int maxFit1 = 0, maxFit2 = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (individuals[i].getFitness() > individuals[maxFit1].getFitness()) {
                maxFit2 = maxFit1;
                maxFit1 = i;
            }
            else if (individuals[i].getFitness() > individuals[maxFit2].getFitness()) {
                maxFit2 = i;
            }
        }
        return individuals[maxFit2];
    }

    public int getLeastFittestIndex() {
        int minFitVal = Integer.MAX_VALUE, minFitIndex = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (minFitVal >= individuals[i].getFitness()) {
                minFitVal = individuals[i].getFitness();
                minFitIndex = i;
            }
        }
        return minFitIndex;
    }

    public void calculateFitness() {
        for (int i = 0; i < individuals.length; i++) {
            individuals[i].calculateFitness();
        }
        getFittest();
    }

    @Override
    public String toString() {
        return "Population{" +
                "POPULATIONSIZE=" + POPULATIONSIZE +
                ", individuals=" + Arrays.toString(individuals) +
                ", fittest=" + fittest +
                '}';
    }
}