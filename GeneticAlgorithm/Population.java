import java.util.Arrays;
import java.util.Random;

public class Population {
    private final int POPULATIONSIZE = 10;
    public Individual[] individuals = new Individual[POPULATIONSIZE];
    private int fittest = 0;

    public Population(Individual[] individuals){
        this.individuals = individuals;
    }

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

    public Individual randomSelection() {
        Random random = new Random();
        int randomIndex = random.nextInt(9);
        return individuals[randomIndex];
    }

    public void calculateFitness() {
        for (int i = 0; i < individuals.length; i++) {
            individuals[i].calculateFitness();
        }
        getFittest();
    }

    public Individual onePointCrossover(Individual father, Individual mother) {
        Individual son = new Individual();
        String[] sonsGenes = new String[son.getGENESAMOUNT()];
        Random random = new Random();

        for(int i = 0; i < son.getGENESAMOUNT(); i++){
            int randomCrossoverPoint = random.nextInt(son.getGENELENGTH());
            if(randomCrossoverPoint == (son.getGENESAMOUNT() - 1)){
                --randomCrossoverPoint;
            }
            else if (randomCrossoverPoint == 0){
                ++randomCrossoverPoint;
            }

            String fatherGene = new String(father.getGene(i));
            String motherGene = new String(mother.getGene(i));

            String sonsGene = new String();
            sonsGene = String.join("", fatherGene.substring(0, randomCrossoverPoint), motherGene.substring(randomCrossoverPoint, mother.getGENELENGTH()));

            son.setGene(sonsGene, i);
        }

        return son;
    }

    public Individual multiplePointsCrossover(Individual father, Individual mother) {
        Individual son = new Individual();
        String[] sonsGenes = new String[son.getGENESAMOUNT()];
        Random random = new Random();
        int amountOfCrossoverPoints = random.nextInt(son.getGENELENGTH() - 2) + 2;


        for(int i = 0; i < son.getGENESAMOUNT(); i++){
            String fatherGene = new String(father.getGene(i));
            String motherGene = new String(mother.getGene(i));
            String sonsGene = new String();
            int amountOfGenes = son.getGENELENGTH() / amountOfCrossoverPoints;
            int j = 0;

            while(j < son.getGENELENGTH()){
                if(j % 2 == 0){
                    sonsGene += fatherGene.substring(j, j + amountOfGenes);
                }
                else{
                    sonsGene += motherGene.substring(j, j + amountOfGenes);
                }
                j+=amountOfGenes;
            }

            son.setGene(sonsGene, i);
        }

        return son;
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