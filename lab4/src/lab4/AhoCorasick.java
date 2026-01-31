package lab4;

import java.util.*;

public class AhoCorasick {
    private final Trie trie;
    private boolean built = false;

    public AhoCorasick(Trie trie) {
        if (trie == null) throw new IllegalArgumentException("trie is null");
        this.trie = trie;
    }

    /** Шаг 1 алгоритма Ахо–Корасика: построение суффиксных ссылок (failure links) BFS-ом */
    public void buildFailureLinks() {
        TrieNode root = trie.getRoot();
        Queue<TrieNode> q = new ArrayDeque<>();

        root.link = root;

        // дети корня: их link всегда в корень
        for (TrieNode child : root.next.values()) {
            child.link = root;
            q.add(child);
        }

        // BFS
        while (!q.isEmpty()) {
            TrieNode v = q.poll();

            for (Map.Entry<Character, TrieNode> entry : v.next.entrySet()) {
                char ch = entry.getKey();
                TrieNode u = entry.getValue();

                TrieNode f = v.link;
                while (f != root && !f.next.containsKey(ch)) {
                    f = f.link;
                }

                if (f.next.containsKey(ch)) {
                    u.link = f.next.get(ch);
                } else {
                    u.link = root;
                }

                // наследуем out по суффиксной ссылке (важно для шаблонов-суффиксов)
                u.out.addAll(u.link.out);

                q.add(u);
            }
        }

        built = true;
    }

    /** Шаг 2 алгоритма: поиск всех вхождений шаблонов в тексте */
    public List<Match> search(String text) {
        if (!built) buildFailureLinks();
        if (text == null) throw new IllegalArgumentException("text is null");

        List<Match> res = new ArrayList<>();
        TrieNode root = trie.getRoot();
        TrieNode state = root;
        List<String> patterns = trie.getPatterns();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            while (state != root && !state.next.containsKey(ch)) {
                state = state.link;
            }

            if (state.next.containsKey(ch)) {
                state = state.next.get(ch);
            } else {
                state = root;
            }

            if (!state.out.isEmpty()) {
                for (int id : state.out) {
                    String p = patterns.get(id);
                    int start = i - p.length() + 1;
                    int end = i;
                    res.add(new Match(start, end, id, p));
                }
            }
        }

        return res;
    }

    /** Для отчёта: распечатать BFS-структуру после построения failure links */
    public void printBfsDebug() {
        if (!built) buildFailureLinks();

        TrieNode root = trie.getRoot();

        Map<TrieNode, Integer> ids = new IdentityHashMap<>();
        Queue<TrieNode> q = new ArrayDeque<>();

        int counter = 0;
        ids.put(root, counter++);
        q.add(root);

        System.out.println("BFS traversal:");
        while (!q.isEmpty()) {
            TrieNode v = q.poll();
            int vid = ids.get(v);

            int linkId = -1;
            if (v.link != null && ids.containsKey(v.link)) {
                linkId = ids.get(v.link);
            } else if (v.link != null) {
                ids.put(v.link, counter++);
                linkId = ids.get(v.link);
            }

            System.out.println(
                    "node#" + vid +
                    " depth=" + v.depth +
                    " link=node#" + linkId +
                    " out=" + v.out +
                    " next=" + v.next.keySet()
            );

            for (TrieNode u : v.next.values()) {
                if (!ids.containsKey(u)) {
                    ids.put(u, counter++);
                    q.add(u);
                }
            }
        }
    }

    public boolean isBuilt() {
        return built;
    }
}
