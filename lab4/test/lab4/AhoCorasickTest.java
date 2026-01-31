package lab4;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AhoCorasickTest {

    @Test
    void constructor_nullTrie_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new AhoCorasick(null));
    }

    @Test
    void search_nullText_throwsIllegalArgumentException() {
        Trie trie = new Trie();
        trie.addPattern("a");
        AhoCorasick ac = new AhoCorasick(trie);

        assertThrows(IllegalArgumentException.class, () -> ac.search(null));
    }

    @Test
    void search_emptyText_returnsEmptyList() {
        Trie trie = new Trie();
        trie.addPattern("a");
        AhoCorasick ac = new AhoCorasick(trie);

        List<Match> matches = ac.search("");

        assertNotNull(matches);
        assertTrue(matches.isEmpty());
    }

    @Test
    void search_buildsAutomatonAutomaticallyOnFirstCall() {
        Trie trie = new Trie();
        trie.addPattern("he");
        AhoCorasick ac = new AhoCorasick(trie);

        assertFalse(ac.isBuilt(), "До первого поиска автомат ещё не построен");
        ac.search("he");
        assertTrue(ac.isBuilt(), "После первого поиска автомат должен построиться автоматически");
    }

    @Test
    void classicExample_ushers_findsSheHeHers() {
        Trie trie = new Trie();
        trie.addPattern("he");
        trie.addPattern("she");
        trie.addPattern("his");
        trie.addPattern("hers");
        AhoCorasick ac = new AhoCorasick(trie);

        List<Match> matches = ac.search("ushers");

        // "ushers"
        //  012345
        //  "she"  = 1..3
        //   "he"  = 2..3
        //   "hers"= 2..5
        assertTrue(containsMatch(matches, "she", 1, 3));
        assertTrue(containsMatch(matches, "he", 2, 3));
        assertTrue(containsMatch(matches, "hers", 2, 5));

        // "his" не встречается в "ushers"
        assertFalse(matches.stream().anyMatch(m -> "his".equals(m.pattern)));
    }

    @Test
    void suffixPattern_isAlsoReported_viaOutputInheritance() {
        Trie trie = new Trie();
        trie.addPattern("he");
        trie.addPattern("she");
        AhoCorasick ac = new AhoCorasick(trie);

        List<Match> matches = ac.search("she");

        // "she" = 0..2, "he" (суффикс) = 1..2
        assertTrue(containsMatch(matches, "she", 0, 2));
        assertTrue(containsMatch(matches, "he", 1, 2));
    }

    @Test
    void overlappingMatches_areReported() {
        Trie trie = new Trie();
        trie.addPattern("aa");
        trie.addPattern("aaa");
        AhoCorasick ac = new AhoCorasick(trie);

        List<Match> matches = ac.search("aaaa");

        // "aaaa"
        // 0123
        // "aa" : 0..1, 1..2, 2..3
        // "aaa": 0..2, 1..3
        assertTrue(containsMatch(matches, "aa", 0, 1));
        assertTrue(containsMatch(matches, "aa", 1, 2));
        assertTrue(containsMatch(matches, "aa", 2, 3));

        assertTrue(containsMatch(matches, "aaa", 0, 2));
        assertTrue(containsMatch(matches, "aaa", 1, 3));
    }

    @Test
    void patternId_matchesInsertionOrder() {
        Trie trie = new Trie();
        int idHe = trie.addPattern("he");     // 0
        int idShe = trie.addPattern("she");   // 1
        AhoCorasick ac = new AhoCorasick(trie);

        List<Match> matches = ac.search("she");

        // Проверяем, что patternId в Match соответствует id, возвращенному addPattern
        Match she = matches.stream().filter(m -> "she".equals(m.pattern)).findFirst().orElseThrow();
        assertEquals(idShe, she.patternId);

        Match he = matches.stream().filter(m -> "he".equals(m.pattern)).findFirst().orElseThrow();
        assertEquals(idHe, he.patternId);
    }

    // --- helper ---
    private static boolean containsMatch(List<Match> matches, String pattern, int start, int end) {
        return matches.stream().anyMatch(m ->
                pattern.equals(m.pattern) && m.start == start && m.end == end
        );
    }
}
