package lab1;

public class Main {
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
