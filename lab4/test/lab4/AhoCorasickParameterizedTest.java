package lab4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AhoCorasickParameterizedTest {

    record Case(String[] patterns, String text, String expectedPattern, boolean shouldExist) {}

    static Stream<Case> cases() {
        return Stream.of(
                new Case(new String[]{"a"}, "a", "a", true),
                new Case(new String[]{"ab", "b"}, "ab", "ab", true),
                new Case(new String[]{"he", "she"}, "she", "he", true),   // суффикс должен находиться
                new Case(new String[]{"xyz"}, "abc", "xyz", false),       // совпадения нет
                new Case(new String[]{"aba", "ba"}, "aba", "ba", true)    // перекрытие/суффикс
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void generatedCases(Case c) {
        Trie trie = new Trie();
        for (String p : c.patterns) {
            trie.addPattern(p);
        }

        AhoCorasick ac = new AhoCorasick(trie);
        List<Match> matches = ac.search(c.text);

        boolean exists = matches.stream().anyMatch(m -> c.expectedPattern.equals(m.pattern));
        assertEquals(c.shouldExist, exists);
    }
}
