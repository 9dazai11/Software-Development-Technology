package lab4;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // 1) Строим Бор (Trie) отдельно
        Trie trie = new Trie();
        trie.addPattern("he");
        trie.addPattern("she");
        trie.addPattern("his");
        trie.addPattern("hers");

        // 2) Строим алгоритм Ахо–Корасика отдельно (failure links)
        AhoCorasick ac = new AhoCorasick(trie);
        ac.buildFailureLinks();

        // Вывод структуры (для отчёта)
        ac.printBfsDebug();

        // 3) Демонстрация сценария: поиск (не обязателен, но полезен)
        String text = "ushers";
        List<Match> matches = ac.search(text);

        System.out.println("\nText: " + text);
        System.out.println("Matches:");
        for (Match m : matches) {
            System.out.println(m + " -> '" + text.substring(m.start, m.end + 1) + "'");
        }
    }
}
