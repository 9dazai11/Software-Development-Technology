package lab4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {

    @Test
    void addPattern_returnsSequentialIds_andStoresPatterns() {
        Trie trie = new Trie();

        int id0 = trie.addPattern("abc");
        int id1 = trie.addPattern("ab");

        assertEquals(0, id0);
        assertEquals(1, id1);

        assertEquals(2, trie.getPatterns().size());
        assertEquals("abc", trie.getPatterns().get(0));
        assertEquals("ab", trie.getPatterns().get(1));
    }

    @Test
    void addPattern_null_throwsIllegalArgumentException() {
        Trie trie = new Trie();
        assertThrows(IllegalArgumentException.class, () -> trie.addPattern(null));
    }

    @Test
    void addPattern_empty_throwsIllegalArgumentException() {
        Trie trie = new Trie();
        assertThrows(IllegalArgumentException.class, () -> trie.addPattern(""));
    }
}
