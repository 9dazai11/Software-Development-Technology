package app;

import java.util.List;

import analysis.BradycardiaDetector;
import analysis.Detector;
import analysis.TachycardiaDetector;
import domain.AnalysisResult;
import report.TextReportBuilder;

/**
 * Facade: единая точка входа для анализа и формирования отчёта.
 */
public class CardioAdvisorFacade {

    private final List<Detector> detectors;
    private final TextReportBuilder reportBuilder;

    public CardioAdvisorFacade() {
        // В простейшем виде — фиксированный набор стратегий (детекторов)
        this.detectors = List.of(
                new BradycardiaDetector(),
                new TachycardiaDetector()
        );
        this.reportBuilder = new TextReportBuilder();
    }

    public AnalysisResult analyze(int heartRate) {
        AnalysisResult result = new AnalysisResult(heartRate);

        for (Detector detector : detectors) {
            detector.detect(heartRate, result);
        }

        // Небольшое правило "нормы" как доп. логика
        if (result.isNormal()) {
            result.addRecommendation("Показатели ЧСС в пределах условной нормы (60–100).");
        }

        return result;
    }

    public String analyzeAndBuildReport(int heartRate) {
        AnalysisResult result = analyze(heartRate);
        return reportBuilder.build(result);
    }
}
