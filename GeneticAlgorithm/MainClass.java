public class MainClass {
    public static void main(String[] args) {
        Population p = new Population(0);
        System.out.println(p);

        GeneticAlgorithm g = new GeneticAlgorithm();
        g.mutation(p.getFittest(), p.getSecondFittest());
        System.out.println(g);
    }
}
