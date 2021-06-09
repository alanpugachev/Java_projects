import java.util.Random;

public class SimpleGA {
    private Individual firstChild;
    private Individual secondChild;

    public void onePointCrossover(Individual father, Individual mother) {
        Random random = new Random();
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

            String firstGene = new String();
            firstGene = String.join("", fatherGene.substring(0, randomCrossoverPoint), motherGene.substring(randomCrossoverPoint, mother.getGENELENGTH()));
            firstChild.setGene(firstGene, i);

            String secondGene = new String();
            secondGene = String.join("", motherGene.substring(0, randomCrossoverPoint), fatherGene.substring(randomCrossoverPoint, mother.getGENELENGTH()));
            secondChild.setGene(firstGene, i);
        }
    }

    public void twoPointsCrossover(Individual father, Individual mother) {
        Random random = new Random();
        for(int i = 0; i < father.getGENELENGTH(); i++) {
            int firstPoint = random.nextInt(father.getGENELENGTH() - 2) + 2, secondPoint = random.nextInt(mother.getGENELENGTH()) + 2;
            if(firstPoint == secondPoint){
                if(secondPoint + 1 == 12){
                    secondPoint--;
                }
                else if(secondPoint - 1 < 0){
                    secondPoint++;
                }
                else{
                    secondPoint++;
                }
            }

            if(firstPoint > secondPoint) {
                int temp = firstPoint;
                firstPoint = secondPoint;
                secondPoint = temp;
            }

            String fatherGene = new String(father.getGene(i));
            String motherGene = new String(mother.getGene(i));

            String firstGene = new String();
            firstGene = fatherGene.substring(0, firstPoint) + motherGene.substring(firstPoint, secondPoint) + fatherGene.substring(secondPoint, father.getGENELENGTH() - 1);
            firstChild.setGene(firstGene, i);

            String secondGene = new String();
            secondGene = motherGene.substring(0, firstPoint) + fatherGene.substring(firstPoint, secondPoint) + motherGene.substring(secondPoint, father.getGENELENGTH() - 1);
            secondChild.setGene(secondGene, i);
        }
    }

    public void multiplePointsCrossover(Individual father, Individual mother) {
        Random random = new Random();
        int amountOfCrossoverPoints = random.nextInt(father.getGENELENGTH() - 2) + 2;
        for(int i = 0; i < father.getGENESAMOUNT(); i++){
            String fatherGene = new String(father.getGene(i));
            String motherGene = new String(mother.getGene(i));
            String firstGene = new String();
            String secondGene = new String();
            int amountOfGenes = father.getGENELENGTH() / amountOfCrossoverPoints;
            int j = 0;

            while(j < father.getGENELENGTH()){
                if(j % 2 == 0){
                    firstGene += fatherGene.substring(j, j + amountOfGenes);
                    secondGene += motherGene.substring(j, j + amountOfGenes);
                }
                else{
                    firstGene += motherGene.substring(j, j + amountOfGenes);
                    secondGene += fatherGene.substring(j, j + amountOfGenes);
                }
                j+=amountOfGenes;
            }

            firstChild.setGene(firstGene, i);
            secondChild.setGene(secondGene, i);
        }
    }



    public Individual getFirstChild() {
        return firstChild;
    }

    public Individual getSecondChild() {
        return secondChild;
    }

    @Override
    public String toString() {
        return "SimpleGA{" +
                "firstChild=" + firstChild +
                ", secondChild=" + secondChild +
                '}';
    }
}
