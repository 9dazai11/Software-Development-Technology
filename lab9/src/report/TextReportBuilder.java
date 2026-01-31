package report;

import domain.AnalysisResult;

/**
 * Builder: формирование текстового отчёта по результату анализа.
 */
public class TextReportBuilder {

    public String build(AnalysisResult result) {
        StringBuilder sb = new StringBuilder();

        sb.append("=== CardioAdvisor: Отчёт анализа (упрощённый) ===\n");
        sb.append("Входные данные:\n");
        sb.append("- ЧСС: ").append(result.getHeartRate()).append(" уд/мин\n\n");

        sb.append("Результаты:\n");
        if (result.isNormal()) {
            sb.append("- Отклонений по заданным правилам не выявлено.\n");
        } else {
            sb.append("- Выявленные события:\n");
            for (String e : result.getEvents()) {
                sb.append("  * ").append(e).append("\n");
            }
        }

        sb.append("\nРекомендации:\n");
        if (result.getRecommendations().isEmpty()) {
            sb.append("- Общая рекомендация: при наличии жалоб обратиться к врачу-кардиологу.\n");
        } else {
            for (String r : result.getRecommendations()) {
                sb.append("  * ").append(r).append("\n");
            }
        }

        sb.append("\nПримечание: прототип предназначен для учебной демонстрации (не является медицинским диагнозом).\n");
        return sb.toString();
    }
}
