package analysis;

import domain.AnalysisResult;

/**
 * Детектор тахикардии: ЧСС > 100.
 */
public class TachycardiaDetector implements Detector {

    @Override
    public void detect(int heartRate, AnalysisResult result) {
        if (heartRate > 100) {
            result.addEvent("Подозрение на тахикардию (ЧСС > 100)");
            result.addRecommendation("Рекомендуется повторная регистрация ЭКГ и оценка клинических симптомов.");
        }
    }
}
