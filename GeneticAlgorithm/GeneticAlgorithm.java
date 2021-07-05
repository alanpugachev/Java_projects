import java.util.Arrays;
import java.util.Random;

public class GeneticAlgorithm {
    private Population p = new Population();
    private Microorganism father, mother;

    public void crossover(Microorganism father, Microorganism mother) {
        Random random = new Random();
        int crossover = random.nextInt(100);

        if(crossover >= 1 && crossover <= 33){
            onePointCrossover(father, mother);
        }
        if(crossover >= 34 && crossover <= 66){
            twoPointsCrossover(father, mother);
        }
        if(crossover >= 67 && crossover <= 100 ){
            fewPointsCrossover(father, mother);
        }
    }

    public void mutation(Microorganism father, Microorganism mother) {
        Random random = new Random();
        int mutationKind = random.nextInt(2);

        switch (mutationKind){
            case 0: //inversion
                int parentMutationChoose = random.nextInt(2);

                switch (parentMutationChoose){
                    case 0:
                        int gene = random.nextInt(father.getGENESAMOUNT());
                        int mutationPoint = random.nextInt(father.getGENELENGTH());

                        String tempGene = father.getGene(gene).substring(mutationPoint);
                        if(tempGene.equals("1")){
                            tempGene = "0";
                        }
                        else{
                            tempGene = "1";
                        }

                        father.setGene(father.getGene(gene).substring(0, mutationPoint) + tempGene
                                + father.getGene(gene).substring(mutationPoint + 1, father.getGENELENGTH()), gene);
                        break;
                    case 1:
                        int gene1 = random.nextInt(mother.getGENESAMOUNT());
                        int mutationPoint1 = random.nextInt(mother.getGENELENGTH());

                        String tempGene1 = mother.getGene(gene1).substring(mutationPoint1);
                        if(tempGene1.equals("1")){
                            tempGene1 = "0";
                        }
                        else{
                            tempGene1 = "1";
                        }

                        mother.setGene(mother.getGene(gene1).substring(0, mutationPoint1) + tempGene1
                                + mother.getGene(gene1).substring(mutationPoint1 + 1, mother.getGENELENGTH()), gene1);
                        break;
                    default:
                        break;
                }
                break;
            case 1: //exchange
                int mutationPoint = random.nextInt(father.getGENELENGTH() - 1);
                int gene = random.nextInt(mother.getGENESAMOUNT() - 1);

                String temp1 = father.getGene(gene).substring(mutationPoint);
                String temp2 = mother.getGene(gene).substring(mutationPoint);

                father.setGene(father.getGene(mutationPoint) + temp2 + father.getGene(mutationPoint + 1), gene);
                mother.setGene(mother.getGene(mutationPoint) + temp1 + mother.getGene(mutationPoint + 1), gene);
                break;
            default:
                break;
        }

        offspring(father, mother);
    }

    private void onePointCrossover(Microorganism father, Microorganism mother) {
        Random random = new Random();
        String fatherGenes[] = new String[father.getGENESAMOUNT()];
        String motherGenes[] = new String[mother.getGENESAMOUNT()];

        for(int j = 0; j < father.getGENESAMOUNT(); j++){
            int randomCrossoverPoint = random.nextInt(father.getGENELENGTH());
            if(randomCrossoverPoint == (father.getGENESAMOUNT() - 1)){
                --randomCrossoverPoint;
            }
            else if (randomCrossoverPoint == 0){
                ++randomCrossoverPoint;
            }

            String fatherGene = new String(father.getGene(j));
            String motherGene = new String(mother.getGene(j));

            fatherGenes[j] = String.join("", fatherGene.substring(0, randomCrossoverPoint), motherGene.substring(randomCrossoverPoint, mother.getGENELENGTH()));
            motherGenes[j] = String.join("", motherGene.substring(0, randomCrossoverPoint), fatherGene.substring(randomCrossoverPoint, father.getGENELENGTH()));
        }
        father.setGenes(fatherGenes);
        mother.setGenes(motherGenes);
    }

    private void twoPointsCrossover(Microorganism father, Microorganism mother) {
        Random random = new Random();
        String fatherGenes[] = new String[father.getGENESAMOUNT()];
        String motherGenes[] = new String[mother.getGENESAMOUNT()];

        for(int j = 0; j < father.getGENESAMOUNT(); j++) {
            int firstPoint = random.nextInt(father.getGENELENGTH() - 2) + 2, secondPoint = random.nextInt(mother.getGENELENGTH()) + 2;

            if(secondPoint + 1 >= father.getGENELENGTH() - 1){
                secondPoint--;
            }

            if(secondPoint - 1 <= 0){
                secondPoint++;
            }

            if(firstPoint > secondPoint) {
                int temp = firstPoint;
                firstPoint = secondPoint;
                secondPoint = temp;
            }

            String fatherGene = new String(father.getGene(j));
            String motherGene = new String(mother.getGene(j));

            fatherGenes[j] = motherGene.substring(0, firstPoint) + fatherGene.substring(firstPoint, secondPoint) + motherGene.substring(secondPoint, father.getGENELENGTH() - 1);
            motherGenes[j] = fatherGene.substring(0, firstPoint) + motherGene.substring(firstPoint, secondPoint) + fatherGene.substring(secondPoint, father.getGENELENGTH() - 1);
        }
        father.setGenes(fatherGenes);
        mother.setGenes(motherGenes);
    }

    private void fewPointsCrossover(Microorganism father, Microorganism mother) {
        Random random = new Random();
        int amountOfCrossoverPoints = random.nextInt(father.getGENELENGTH() - 2) + 2;
        String fatherGenes[] = new String[father.getGENESAMOUNT()];
        String motherGenes[] = new String[mother.getGENESAMOUNT()];

        for(int j = 0; j < father.getGENESAMOUNT(); j++){
            String fatherGene = new String(father.getGene(j));
            String motherGene = new String(mother.getGene(j));
            int amountOfGenes = father.getGENELENGTH() / amountOfCrossoverPoints;
            int k = 0;

            while(k < father.getGENELENGTH()){
                if(k % 2 == 0){
                    motherGenes[j] += fatherGene.substring(k, k + amountOfGenes);
                }
                else{
                    fatherGenes[j] += motherGene.substring(k, k + amountOfGenes);
                }
                k+=amountOfGenes;
            }
        }
        father.setGenes(fatherGenes);
        mother.setGenes(motherGenes);
    }

    public void offspring(Microorganism father, Microorganism mother) {
        Microorganism[] m = new Microorganism[p.getPOPULATIONSIZE()];
        for (int i = 0; i < p.getPOPULATIONSIZE(); i++){
            m[i] = new Microorganism(1);
            for (int j = 0; j < father.getGENESAMOUNT(); j++){
                Random random = new Random();
                int parentGene = random.nextInt(2);
                switch (parentGene){
                    case 0:
                        m[i].setGene(father.getGene(j), j);
                        break;
                    case 1:
                        m[i].setGene(mother.getGene(j), j);
                        break;
                    default:
                        break;
                }
            }
            m[i].calculateFitness();
        }
        p.setMicroorganisms(m);
        p.calculateFitness();
    }

    public void setFather(Microorganism father) {
        this.father = father;
    }

    public void setMother(Microorganism mother) {
        this.mother = mother;
    }

    public Population getPopulation() {
        return p;
    }

    public Microorganism getFather() {
        return father;
    }

    public Microorganism getMother() {
        return mother;
    }

    @Override
    public String toString() {
        return "GeneticAlgorithm{" +
                "p=" + p +
                '}';
    }
}
