package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Результат анализа ЭКГ (упрощенно: по ЧСС).
 * Хранит найденные события и рекомендации.
 */
public class AnalysisResult {
    private final int heartRate;
    private final List<String> events = new ArrayList<>();
    private final List<String> recommendations = new ArrayList<>();

    public AnalysisResult(int heartRate) {
        this.heartRate = heartRate;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void addEvent(String event) {
        if (event != null && !event.isBlank()) {
            events.add(event);
        }
    }

    public void addRecommendation(String recommendation) {
        if (recommendation != null && !recommendation.isBlank()) {
            recommendations.add(recommendation);
        }
    }

    public List<String> getEvents() {
        return Collections.unmodifiableList(events);
    }

    public List<String> getRecommendations() {
        return Collections.unmodifiableList(recommendations);
    }

    public boolean isNormal() {
        return events.isEmpty();
    }
}
