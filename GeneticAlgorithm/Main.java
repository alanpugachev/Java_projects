public class Main {
    public static void main(String[] args) {
        Population p = new Population();
        SimpleGA s = new SimpleGA();
        s.twoPointsCrossover(p.getFittest(), p.getSecondFittest());
        System.out.println(s.getFirstChild());
        System.out.println(s.getSecondChild());
    }
}
