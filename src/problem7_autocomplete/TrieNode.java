package problem7_autocomplete;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {

    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEnd = false;
}
