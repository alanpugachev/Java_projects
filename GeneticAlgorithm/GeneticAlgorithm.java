import java.util.Random;

public class GeneticAlgorithm {
    private Microorganism firstChild = new Microorganism();
    private Microorganism secondChild = new Microorganism();

    public void onePointCrossover(Microorganism father, Microorganism mother) {
        Random random = new Random();
        String firstGenes[] = new String[firstChild.getGENESAMOUNT()];
        String secondGenes[] = new String[firstChild.getGENESAMOUNT()];

        for(int i = 0; i < firstChild.getGENESAMOUNT(); i++){
            int randomCrossoverPoint = random.nextInt(firstChild.getGENELENGTH());
            if(randomCrossoverPoint == (firstChild.getGENESAMOUNT() - 1)){
                --randomCrossoverPoint;
            }
            else if (randomCrossoverPoint == 0){
                ++randomCrossoverPoint;
            }

            String fatherGene = new String(father.getGene(i));
            String motherGene = new String(mother.getGene(i));

            firstGenes[i] = String.join("", fatherGene.substring(0, randomCrossoverPoint), motherGene.substring(randomCrossoverPoint, mother.getGENELENGTH()));
            secondGenes[i] = String.join("", motherGene.substring(0, randomCrossoverPoint), fatherGene.substring(randomCrossoverPoint, mother.getGENELENGTH()));
        }

        firstChild.setGenes(firstGenes);
        secondChild.setGenes(secondGenes);
    }

    public void twoPointsCrossover(Microorganism father, Microorganism mother) {
        Random random = new Random();
        String firstGenes[] = new String[father.getGENESAMOUNT()];
        String secondGenes[] = new String[father.getGENESAMOUNT()];

        for(int i = 0; i < father.getGENESAMOUNT(); i++) {
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

            String fatherGene = new String(father.getGene(i));
            String motherGene = new String(mother.getGene(i));

            firstGenes[i] = fatherGene.substring(0, firstPoint) + motherGene.substring(firstPoint, secondPoint) + fatherGene.substring(secondPoint, father.getGENELENGTH() - 1);
            secondGenes[i] = motherGene.substring(0, firstPoint) + fatherGene.substring(firstPoint, secondPoint) + motherGene.substring(secondPoint, father.getGENELENGTH() - 1);
        }

        firstChild.setGenes(firstGenes);
        secondChild.setGenes(secondGenes);
    }

    public void multiplePointsCrossover(Microorganism father, Microorganism mother) {
        Random random = new Random();
        int amountOfCrossoverPoints = random.nextInt(father.getGENELENGTH() - 2) + 2;
        String firstGenes[] = new String[father.getGENESAMOUNT()];
        String secondGenes[] = new String[father.getGENESAMOUNT()];

        for(int i = 0; i < father.getGENESAMOUNT(); i++){
            String fatherGene = new String(father.getGene(i));
            String motherGene = new String(mother.getGene(i));
            int amountOfGenes = father.getGENELENGTH() / amountOfCrossoverPoints;
            int j = 0;

            while(j < father.getGENELENGTH()){
                if(j % 2 == 0){
                    firstGenes[i] += fatherGene.substring(j, j + amountOfGenes);
                    secondGenes[i] += motherGene.substring(j, j + amountOfGenes);
                }
                else{
                    firstGenes[i] += motherGene.substring(j, j + amountOfGenes);
                    secondGenes[i] += fatherGene.substring(j, j + amountOfGenes);
                }
                j+=amountOfGenes;
            }
        }

        firstChild.setGenes(firstGenes);
        secondChild.setGenes(secondGenes);
    }


    public Microorganism getFirstChild() {
        return firstChild;
    }

    public Microorganism getSecondChild() {
        return secondChild;
    }

    @Override
    public String toString() {
        return "GeneticAlgorithm{" +
                "firstChild=" + firstChild +
                ", secondChild=" + secondChild +
                '}';
    }
}
