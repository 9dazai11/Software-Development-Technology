package analysis;

import domain.AnalysisResult;

/**
 * Детектор брадикардии: ЧСС < 60.
 */
public class BradycardiaDetector implements Detector {

    @Override
    public void detect(int heartRate, AnalysisResult result) {
        if (heartRate < 60) {
            result.addEvent("Подозрение на брадикардию (ЧСС < 60)");
            result.addRecommendation("Рекомендуется оценить динамику ЧСС и при необходимости провести Холтеровское мониторирование.");
        }
    }
}
