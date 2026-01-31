package analysis;

import domain.AnalysisResult;

/**
 * Strategy: общий интерфейс детектора.
 * Каждый детектор добавляет событие/рекомендацию в AnalysisResult при срабатывании.
 */
public interface Detector {
    void detect(int heartRate, AnalysisResult result);
}
