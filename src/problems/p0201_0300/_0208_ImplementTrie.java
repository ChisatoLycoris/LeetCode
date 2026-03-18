package problems.p0201_0300;

import patterns.TreePattern;
import patterns.HashTablePattern;
import patterns.StringPattern;
import difficulty.Medium;

/**
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to
 * efficiently store and retrieve keys in a dataset of strings. There are
 * various
 * applications of this data structure, such as autocomplete and spellchecker.
 *
 * Implement the Trie class:
 *
 * Trie() Initializes the trie object.
 * void insert(String word) Inserts the string word into the trie.
 * boolean search(String word) Returns true if the string word is in the trie
 * (i.e., was inserted before), and false otherwise.
 * boolean startsWith(String prefix) Returns true if there is a previously
 * inserted string word that has the prefix prefix, and false otherwise.
 *
 * Example 1:
 * Input: ["Trie", "insert", "search", "search", "startsWith", "insert",
 * "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * Output: [null, null, true, false, true, null, true]
 *
 * Constraints:
 * 1 <= word.length, prefix.length <= 2000
 * word and prefix consist only of lowercase English letters.
 * At most 3 * 10^4 calls in total will be made to insert, search, and
 * startsWith.
 *
 * <a href="https://leetcode.com/problems/implement-trie-prefix-tree/">208.
 * Implement Trie (Prefix Tree)</a>
 */
public class _0208_ImplementTrie implements TreePattern, HashTablePattern, StringPattern, Medium {

    class Trie {
        Node root;

        public Trie() {
            root = new Node();
        }

        /**
         * Time Complexity: O(n)
         * Space Complexity: O(n)
         */
        public void insert(String word) {
            Node node = root;
            for (int i = 0; i < word.length(); i++) {
                if (node.children[word.charAt(i) - 97] == null) {
                    node.children[word.charAt(i) - 97] = new Node();
                }
                node = node.children[word.charAt(i) - 97];
            }
            node.val = word;
        }

        /**
         * Time Complexity: O(n)
         * Space Complexity: O(1)
         */
        public boolean search(String word) {
            Node node = root;
            for (int i = 0; i < word.length(); i++) {
                if (node.children[word.charAt(i) - 97] == null) {
                    return false;
                }
                node = node.children[word.charAt(i) - 97];
            }
            return node.val != null;
        }

        /**
         * Time Complexity: O(n)
         * Space Complexity: O(1)
         */
        public boolean startsWith(String prefix) {
            Node node = root;
            for (int i = 0; i < prefix.length(); i++) {
                if (node.children[prefix.charAt(i) - 97] == null) {
                    return false;
                }
                node = node.children[prefix.charAt(i) - 97];
            }
            return true;
        }

        class Node {
            String val;
            Node[] children;

            public Node() {
                this.children = new Node[26];
            }
        }
    }

    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */
}
