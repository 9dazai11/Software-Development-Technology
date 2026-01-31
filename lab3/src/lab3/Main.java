package lab3;

/**
 * Класс для демонтсрации работы StringProcessor.
 * Содержит точку входа в программу
 */
public class Main {
	/**
     * Точка входа в программу.
     * <p>
     * В методе создаётся объект класса {@link StringProcessor},
     * после чего демонстрируется работа методов проверки строки
     * на префикс, суффикс и подстроку.
     *
     * @param args аргументы командной строки (в данной программе не используются)
     */
	public static void main(String[] args) {
		StringProcessor sp = new StringProcessor("prefixTest");
        
        System.out.println("Is Prefix: " + sp.isPrefix("pre")); // true
        System.out.println("Is Suffix: " + sp.isSuffix("Test")); // true
        System.out.println("Is Substring: " + sp.isSubstring("fix")); // true
        
        System.out.println("Is Prefix (empty): " + sp.isPrefix("")); // true
        System.out.println("Is Suffix (empty): " + sp.isSuffix("")); // true
        System.out.println("Is Substring (empty): " + sp.isSubstring("")); // true
    }
}
