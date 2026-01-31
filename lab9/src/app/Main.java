package app;

/**
 * Демонстрация работы прототипа.
 */
public class Main {

    public static void main(String[] args) {
        CardioAdvisorFacade advisor = new CardioAdvisorFacade();

        int[] demoRates = {55, 75, 120};

        for (int hr : demoRates) {
            String report = advisor.analyzeAndBuildReport(hr);
            System.out.println(report);
            System.out.println("-----------------------------------------------\n");
        }
    }
}
