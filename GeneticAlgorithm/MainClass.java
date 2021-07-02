public class MainClass {
    public static void main(String[] args) {
        Population p = new Population(1);
        System.out.println(p);

        GeneticAlgorithm g = new GeneticAlgorithm();
        g.onePointCrossover(p.getFittest(), p.getSecondFittest());
        System.out.println(g.getFirstChild() + "\n" + g.getSecondChild());
    }
}
