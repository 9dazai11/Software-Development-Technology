package lab1;

public class StringProcessor {
	private String baseString;
	
	// Конструктор
	public StringProcessor(String baseString) {
		this.baseString = baseString;
	}
	
	// Проверка является ли str1 префиксом baseString
	public boolean isPrefix(String str1) {
		if (str1.isEmpty()) return true;
		if (baseString.isEmpty() || str1.length() > baseString.length()) return false;
		
		for (int i = 0; i < str1.length(); i++) {
			if (str1.charAt(i) != baseString.charAt(i)) return false;
		}
		return true;
	}
	
	// Проверка является ли str1 суффиксом baseString
	public boolean isSuffix(String str1) {
        if (str1.isEmpty()) return true;
        if (baseString.isEmpty() || str1.length() > baseString.length()) return false;
        
        int offset = baseString.length() - str1.length();
        
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != baseString.charAt(i + offset)) return false;
        }
        return true;
    }

	// Проверка является ли str1 подстрокой baseString
    public boolean isSubstring(String str1) {
        if (str1.isEmpty()) return true;
        if (baseString.isEmpty() || str1.length() > baseString.length()) return false;
        
        for (int i = 0; i <= baseString.length() - str1.length(); i++) {
            int j;
            for (j = 0; j < str1.length(); j++) {
                if (baseString.charAt(i + j) != str1.charAt(j)) break;
            }
            if (j == str1.length()) return true;
        }
        return false;
    }
}
