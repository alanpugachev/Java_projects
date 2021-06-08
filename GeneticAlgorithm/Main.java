public class Main {
    public static void main(String[] args) {
        Population p = new Population();

        System.out.println(p.getFittest());
        System.out.println(p.getSecondFittest());

        System.out.println(p.multiplePointsCrossover(p.getFittest(), p.getSecondFittest()));
    }
}
