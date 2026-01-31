package lab4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProfileMain {

    private static final int PATTERN_COUNT = 50_000;
    private static final int PATTERN_MIN_LEN = 4;
    private static final int PATTERN_MAX_LEN = 12;

    private static final int TEXT_LEN = 5_000_000; // 5 млн символов

    // Итерации: прогрев + замеры
    private static final int WARMUP_ITERS = 2;
    private static final int MEASURE_ITERS = 5;

    public static void main(String[] args) {
        // Можно регулировать через аргументы запуска: patterns textlen
        int patterns = (args.length >= 1) ? Integer.parseInt(args[0]) : PATTERN_COUNT;
        int textLen  = (args.length >= 2) ? Integer.parseInt(args[1]) : TEXT_LEN;

        System.out.println("patterns=" + patterns + ", textLen=" + textLen);

        List<String> patternList = generatePatterns(patterns, PATTERN_MIN_LEN, PATTERN_MAX_LEN, 123);
        String text = generateText(textLen, 12345);

        // Прогрев JVM (JIT)
        for (int i = 0; i < WARMUP_ITERS; i++) {
            runOnce(patternList, text, false);
        }

        long sumAdd = 0, sumBuild = 0, sumSearch = 0;
        long sumMatches = 0;

        for (int i = 0; i < MEASURE_ITERS; i++) {
            Result r = runOnce(patternList, text, true);
            sumAdd += r.addMs;
            sumBuild += r.buildMs;
            sumSearch += r.searchMs;
            sumMatches += r.matches;
        }

        System.out.println("=== AVERAGE over " + MEASURE_ITERS + " runs ===");
        System.out.println("addPatterns(ms): " + (sumAdd / MEASURE_ITERS));
        System.out.println("buildFailureLinks(ms): " + (sumBuild / MEASURE_ITERS));
        System.out.println("search(ms): " + (sumSearch / MEASURE_ITERS));
        System.out.println("matches(avg): " + (sumMatches / MEASURE_ITERS));
    }

    private static Result runOnce(List<String> patterns, String text, boolean print) {
        Trie trie = new Trie();
        AhoCorasick ac = new AhoCorasick(trie);

        long t0 = System.nanoTime();
        for (String p : patterns) {
            trie.addPattern(p);
        }
        long t1 = System.nanoTime();
        ac.buildFailureLinks();
        long t2 = System.nanoTime();
        List<Match> matches = ac.search(text);
        long t3 = System.nanoTime();

        Result r = new Result();
        r.addMs = (t1 - t0) / 1_000_000;
        r.buildMs = (t2 - t1) / 1_000_000;
        r.searchMs = (t3 - t2) / 1_000_000;
        r.matches = matches.size();

        if (print) {
            System.out.println("add=" + r.addMs + "ms, build=" + r.buildMs + "ms, search=" + r.searchMs + "ms, matches=" + r.matches);
        }
        return r;
    }

    private static List<String> generatePatterns(int count, int minLen, int maxLen, int seed) {
        Random rnd = new Random(seed);
        ArrayList<String> list = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            int len = minLen + rnd.nextInt(maxLen - minLen + 1);
            list.add(randomLowercase(len, rnd));
        }
        return list;
    }

    private static String generateText(int len, int seed) {
        Random rnd = new Random(seed);
        return randomLowercase(len, rnd);
    }

    private static String randomLowercase(int len, Random rnd) {
        char[] arr = new char[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (char) ('a' + rnd.nextInt(26));
        }
        return new String(arr);
    }

    private static class Result {
        long addMs;
        long buildMs;
        long searchMs;
        long matches;
    }
}
