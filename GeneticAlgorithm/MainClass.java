import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class MainClass {
    Population population = new Population();
    Microorganism fittest;
    Microorganism secondFittest;
    int generationCount = 0;
    static int selectOperator;
    static int crossoverOperator;
    static int countGen;

    public static void main(String[] args) {
        MainClass GA = new MainClass();

        System.out.println("Enter amount of generations:");
        countGen = new Scanner(System.in).nextInt();

        InitializePopulation(GA);

        InitializeSelection();

        InitializeCrossover();

        GA.population.calculateFitness();

        System.out.println("Generation: " + GA.generationCount + " Fittest: " + GA.population.getFittest());

        while (GA.generationCount < countGen) {
            ++GA.generationCount;

            if (selectOperator == 0) {
                GA.selectionElite();
            } else if (selectOperator == 1) {
                GA.selectionRandom();
            } else {
                System.out.println("Something went wrong, choose selection again");
                InitializeSelection();
            }

            if (ThreadLocalRandom.current().nextInt(10) < 7) {
                if (crossoverOperator == 0) {
                    GA.crossoverStandard();
                } else if (crossoverOperator == 1) {
                    GA.crossoverFib();
                } else if (crossoverOperator == 2) {
                    GA.crossoverGold();
                } else if (crossoverOperator == 3) {
                    GA.crossoverTwoPoints();
                } else {
                    System.out.println("Something went wrong, choose selection again");
                    InitializeCrossover();
                }
            }

            if (ThreadLocalRandom.current().nextInt(10) < 2) {
                GA.mutation();
            }

            if (ThreadLocalRandom.current().nextInt(10) < 2) {
                GA.inversionTranslocation();
            }

            GA.proportionalSelection();

            GA.population.calculateFitness();

            int tmpMax = Integer.MIN_VALUE;
            for (int i = 0; i < GA.population.getPOPULATIONSIZE(); i++) {
                if (GA.population.getMicroorganisms()[i].getMax() > tmpMax) {
                    tmpMax = GA.population.getMicroorganisms()[i].getMax();
                }
            }
            System.out.println("Generation: " + GA.generationCount + " Maximum: " + tmpMax);
        }

    }

    static void InitializeSelection() {
        System.out.println("Which selection do you want to use? (0-Elite, 1-Random)");
        selectOperator = new Scanner(System.in).nextInt();
        if (selectOperator > 1 || selectOperator < 0) {
            System.out.println("Wrong choice");
            InitializeSelection();
        }
    }

    static void InitializeCrossover() {
        System.out.println("Which crossover do you want to use? (0-Standard single point, 1-Fib point, 2-Gold point, 3-Two points)");
        crossoverOperator = new Scanner(System.in).nextInt();
        if (crossoverOperator > 3 || crossoverOperator < 0) {
            System.out.println("Wrong choice");
            InitializeCrossover();
        }
    }

    static void InitializePopulation(MainClass GA) {
        System.out.println("Which strategy do you want to use? (0-shotgun, 1-blanket)");
        //Initialize population
        int tmp = new Scanner(System.in).nextInt();
        if (tmp == 0) {
            GA.population.initializePopulationShotgun(10);
        } else if (tmp == 1) {
            GA.population.initializePopulationBlanket(10);
        } else {
            System.out.println("Wrong choice");
            InitializePopulation(GA);
        }
    }

    void selectionElite() {
        fittest = population.getFittest();
        secondFittest = population.getSecondFittest();
    }

    void selectionRandom() {
        fittest = population.randomSelection();
        secondFittest = population.randomSelection();
    }

    void crossoverStandard() {
        Random rn = new Random();

        int crossOverPoint = rn.nextInt(population.getMicroorganisms()[0].getGENELENGTH());
        char[] temp1 = new char[10];
        char[] temp2 = new char[10];

        for (int i = 0; i < population.getMicroorganisms()[0].getGenes().length; i++) {
            fittest.getGenes()[i].getChars(0, crossOverPoint, temp1, 0);
            secondFittest.getGenes()[i].getChars(crossOverPoint, population.getMicroorganisms()[0].getGENELENGTH(), temp1, crossOverPoint);
            secondFittest.getGenes()[i].getChars(0, crossOverPoint, temp2, 0);
            fittest.getGenes()[i].getChars(crossOverPoint, population.getMicroorganisms()[0].getGENELENGTH(), temp2, crossOverPoint);

            fittest.getGenes()[i] = new String(temp1);
            secondFittest.getGenes()[i] = new String(temp2);
        }
    }

    void crossoverGold() {
        int crossOverPoint = (int)((double)(fittest.getGENELENGTH())/1.610);
        char[] temp1 = new char[10];
        char[] temp2 = new char[10];

        for (int i = 0; i < population.getMicroorganisms()[0].getGenes().length; i++) {
            fittest.getGenes()[i].getChars(0, crossOverPoint, temp1, 0);
            secondFittest.getGenes()[i].getChars(crossOverPoint, population.getMicroorganisms()[0].getGENELENGTH(), temp1, crossOverPoint);
            secondFittest.getGenes()[i].getChars(0, crossOverPoint, temp2, 0);
            fittest.getGenes()[i].getChars(crossOverPoint, population.getMicroorganisms()[0].getGENELENGTH(), temp2, crossOverPoint);

            fittest.getGenes()[i] = new String(temp1);
            secondFittest.getGenes()[i] = new String(temp2);
        }
    }

    private int fib(int n) {
        if (n == 0) return 0;
        if (n < 3) return 1;
        return fib(n - 1) + fib(n - 2);
    }

    public void crossoverFib(){

        Random rn = new Random();
        int crossOverPoint = fib(rn.nextInt(6));

        char[] temp1 = new char[10];
        char[] temp2 = new char[10];

        for (int i = 0; i < population.getMicroorganisms()[0].getGenes().length; i++) {
            fittest.getGenes()[i].getChars(0, crossOverPoint, temp1, 0);
            secondFittest.getGenes()[i].getChars(crossOverPoint, population.getMicroorganisms()[0].getGENELENGTH(), temp1, crossOverPoint);
            secondFittest.getGenes()[i].getChars(0, crossOverPoint, temp2, 0);
            fittest.getGenes()[i].getChars(crossOverPoint, population.getMicroorganisms()[0].getGENELENGTH(), temp2, crossOverPoint);

            fittest.getGenes()[i] = new String(temp1);
            secondFittest.getGenes()[i] = new String(temp2);
        }

    }

    public void crossoverTwoPoints(){
        Random rn = new Random();
        int firstCrossOverPoint = rn.nextInt(population.getMicroorganisms()[0].getGENELENGTH());
        int secondCrossOverPoint = rn.nextInt(population.getMicroorganisms()[0].getGENELENGTH());

        char[] temp1 = new char[10];
        char[] temp2 = new char[10];

        for (int i = 0; i < population.getMicroorganisms()[0].getGenes().length; i++) {
            fittest.getGenes()[i].getChars(0, firstCrossOverPoint, temp1, 0);
            fittest.getGenes()[i].getChars(firstCrossOverPoint, secondCrossOverPoint, temp1, firstCrossOverPoint);
            fittest.getGenes()[i].getChars(secondCrossOverPoint, population.getMicroorganisms()[0].getGENELENGTH(), temp1, secondCrossOverPoint);

            secondFittest.getGenes()[i].getChars(0, firstCrossOverPoint, temp2, 0);
            secondFittest.getGenes()[i].getChars(firstCrossOverPoint, secondCrossOverPoint, temp2, firstCrossOverPoint);
            secondFittest.getGenes()[i].getChars(secondCrossOverPoint, population.getMicroorganisms()[0].getGENELENGTH(), temp2, secondCrossOverPoint);
        }

    }

    //Mutation
    void mutation() {
        Random rn = new Random();

        int mutationPoint = rn.nextInt(population.getMicroorganisms()[0].getGENELENGTH());
        int chromosome = rn.nextInt(population.getMicroorganisms()[0].getGenes().length);

        char[] fittestTmp = fittest.getGenes()[chromosome].toCharArray();
        if (mutationPoint + 1 >= population.getMicroorganisms()[0].getGENELENGTH()) {
            char tmp = fittestTmp[mutationPoint];
            fittestTmp[mutationPoint] = fittestTmp[mutationPoint - 1];
            fittestTmp[mutationPoint - 1] = tmp;
        }
        else
        {
            char tmp = fittestTmp[mutationPoint];
            fittestTmp[mutationPoint] = fittestTmp[mutationPoint + 1];
            fittestTmp[mutationPoint + 1] = tmp;
        }
        fittest.getGenes()[chromosome] = new String(fittestTmp);

        mutationPoint = rn.nextInt(population.getMicroorganisms()[0].getGENELENGTH());
        chromosome = rn.nextInt(population.getMicroorganisms()[0].getGenes().length);

        char[] secondFittestTmp = secondFittest.getGenes()[chromosome].toCharArray();
        if (mutationPoint + 1 >= population.getMicroorganisms()[0].getGENELENGTH()) {
            char tmp = secondFittestTmp[mutationPoint];
            secondFittestTmp[mutationPoint] = secondFittestTmp[mutationPoint - 1];
            secondFittestTmp[mutationPoint - 1] = tmp;
        }
        else
        {
            char tmp = secondFittestTmp[mutationPoint];
            secondFittestTmp[mutationPoint] = secondFittestTmp[mutationPoint + 1];
            secondFittestTmp[mutationPoint + 1] = tmp;
        }
        secondFittest.getGenes()[chromosome] = new String(secondFittestTmp);
    }

    void proportionalSelection() {

        ArrayList list = new ArrayList<>();
        double[] masProb = new double[10];
        double sumFit = 0;
        double prevProb = 0.0;

        for (int i=0; i < masProb.length; i++) {
            sumFit += population.getMicroorganisms()[i].getFitness();
        }

        for (int i=0; i < masProb.length; i++) {
            list.add(population.getMicroorganisms()[i].getFitness() / sumFit);
        }

    }

    //Inversion
    void inversionTranslocation() {
        Random rn = new Random();

        //Select a random inversion point
        int inversionPoint = rn.nextInt(population.getMicroorganisms()[0].getGENELENGTH());
        int chromosome = rn.nextInt(population.getMicroorganisms()[0].getGenes().length);
        char[] temp1 = new char[10];
        char[] temp1Copy = new char[10];
        char[] temp2 = new char[10];
        char[] temp2Copy = new char[10];

        //Translocate values in inversion point
        fittest.getGenes()[chromosome].getChars(0, inversionPoint, temp1, 0);
        secondFittest.getGenes()[chromosome].getChars(inversionPoint, population.getMicroorganisms()[0].getGENELENGTH(), temp1, inversionPoint);

        secondFittest.getGenes()[chromosome].getChars(0, inversionPoint, temp2, 0);
        fittest.getGenes()[chromosome].getChars(inversionPoint, population.getMicroorganisms()[0].getGENELENGTH(), temp2, inversionPoint);

        System.arraycopy(temp1, 0, temp1Copy, 0, temp1.length);
        System.arraycopy(temp2, 0, temp2Copy, 0, temp2.length);

        int k = inversionPoint + 1;
        for (int i = population.getMicroorganisms()[0].getGENELENGTH() - 1; i >inversionPoint; i--) {
            temp1[k] = temp1Copy[i];
            temp2[k] = temp2Copy[i];
            k++;
        }
        fittest.getGenes()[chromosome] = new String(temp1);
        secondFittest.getGenes()[chromosome] = new String(temp2);
    }
}
