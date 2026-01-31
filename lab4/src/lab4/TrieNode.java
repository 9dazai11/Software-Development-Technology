package lab4;

import java.util.*;

public class TrieNode {
    public final Map<Character, TrieNode> next = new HashMap<>();
    public TrieNode link;                       // failure link (суффиксная ссылка)
    public final List<Integer> out = new ArrayList<>(); // id шаблонов, которые заканчиваются здесь
    public int depth;                           // глубина (для отладки/печати)
}
