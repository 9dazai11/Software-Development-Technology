package lab4;

import java.util.*;

public class Trie {
    private final TrieNode root = new TrieNode();
    private final List<String> patterns = new ArrayList<>();

    public TrieNode getRoot() {
        return root;
    }

    public List<String> getPatterns() {
        return Collections.unmodifiableList(patterns);
    }

    /** Добавить шаблон в бор. Возвращает id шаблона. */
    public int addPattern(String pattern) {
        if (pattern == null || pattern.isEmpty()) {
            throw new IllegalArgumentException("pattern must be non-empty");
        }

        int id = patterns.size();
        patterns.add(pattern);

        TrieNode cur = root;
        for (char c : pattern.toCharArray()) {
            TrieNode nxt = cur.next.get(c);
            if (nxt == null) {
                nxt = new TrieNode();
                nxt.depth = cur.depth + 1;
                cur.next.put(c, nxt);
            }
            cur = nxt;
        }
        cur.out.add(id);
        return id;
    }
}
