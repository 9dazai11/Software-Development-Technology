package lab3;

/**
 * Класс для обработки строк с функционалом проверки префикса, суффикса и подстроки.
 */
public class StringProcessor {
	private String baseString;
	
	/**
	 * Конструктор создает экземпляр StringProcessor с заданной базовой строкой.
	 * @param baseString базовая строка для операций сравнения
	 */
	public StringProcessor(String baseString) {
		this.setBaseString(baseString);
	}
	
	/**
	 * Проверяет, является ли переданная строка префиксом базовой строки.
     * Пустая строка считается префиксом любой строки.
	 * @param candidate проверяемая строка
	 * @return true если str1 — префикс, иначе false
	 */
	public boolean isPrefix(String candidate) {
		if (candidate.isEmpty()) return true;
		if (getBaseString().isEmpty() || candidate.length() > getBaseString().length()) return false;
		
		for (int i = 0; i < candidate.length(); i++) {
			if (candidate.charAt(i) != getBaseString().charAt(i)) return false;
		}
		return true;
	}
	
	/**
	 * Проверяет, является ли переданная строка суффиксом базовой строки.
     * Пустая строка считается суффиксом любой строки.
	 * @param candidate проверяемая строка
	 * @return true если candidate — суффикс, иначе false
	 */
	public boolean isSuffix(String candidate) {
        if (candidate.isEmpty()) return true;
        if (getBaseString().isEmpty() || candidate.length() > getBaseString().length()) return false;
        
        int offset = getBaseString().length() - candidate.length();
        
        for (int i = 0; i < candidate.length(); i++) {
            if (candidate.charAt(i) != getBaseString().charAt(i + offset)) return false;
        }
        return true;
    }

	/**
	 * Проверяет, содержится ли переданная строка в базовой строке.
     * Пустая строка считается подстрокой любой строки.
	 * @param candidate проверяемая подстрока
	 * @return true если candidate содержится в базовой строке, иначе false
	 */
    public boolean isSubstring(String candidate) {
        if (candidate.isEmpty()) return true;
        if (getBaseString().isEmpty() || candidate.length() > getBaseString().length()) return false;
        
        for (int i = 0; i <= getBaseString().length() - candidate.length(); i++) {
            int j;
            for (j = 0; j < candidate.length(); j++) {
                if (getBaseString().charAt(i + j) != candidate.charAt(j)) break;
            }
            if (j == candidate.length()) return true;
        }
        return false;
    }

    /**
     * Возвращает значение базовой строки.
     * @return текущая базовая строка, используемая для операций сравнения
     */
	private String getBaseString() {
		return baseString;
	}

	/**
     * Устанавливает новое значение базовой строки.
     * @param baseString новая базовая строка для операций сравнения
     */
	private void setBaseString(String baseString) {
		this.baseString = baseString;
	}
}
