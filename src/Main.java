public class Main {

    private void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        }
        catch (final Exception e) {
            System.out.println("xd");
        }
    }

    public static void main(String[] args) {
        Simulator simulator = new Simulator();
        simulator.start();
    }
}
