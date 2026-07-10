# Debug Harness Template

## LeetCodeInput API Summary

All methods are static on `utils.LeetCodeInput`. Input may carry LeetCode's `name = ` prefix (it is stripped automatically).

| Method | Input example | Returns |
|--------|---------------|---------|
| `parseInt` | `"target = 42"` | `int` |
| `parseIntArray` | `"[2,7,11,15]"` | `int[]` |
| `parseIntMatrix` | `"[[1,2],[3,4]]"` | `int[][]` |
| `parseIntegerArray` | `"[6,2,8,null,4]"` | `Integer[]` (nulls kept — tree level-order) |
| `parseCharArray` | `"[\"a\",\"b\"]"` | `char[]` |
| `parseCharMatrix` | `"[[\"1\",\"0\"],[\"1\",\"1\"]]"` | `char[][]` |
| `parseStringArray` | `"[\"flower\",\"flow\"]"` | `String[]` |
| `parseStringMatrix` | `"[[\"eat\"],[\"tea\"]]"` | `String[][]` |
| `parseIntList` | `"[1,2,3]"` | `List<Integer>` |
| `parseIntListList` | `"[[1,2],[3]]"` | `List<List<Integer>>` |
| `parse` | anything | generic `Object` tree (`List`/`String`/`Long`/`Double`/`Boolean`/`null`) |
| `gridToString` | `char[][]` | readable multi-line string |
| `matrixToString` | `int[][]` | readable multi-line string |

## Harness Template (matrix input)

```java
import utils.LeetCodeInput;

public static void main(String[] args) {
    // 直接貼上 LeetCode 的失敗測資
    char[][] grid = LeetCodeInput.parseCharMatrix("""
            [["1","1","0"],["0","1","0"],["0","0","1"]]
            """);
    System.out.println(LeetCodeInput.gridToString(grid));

    _0200_NumberOfIslands solution = new _0200_NumberOfIslands();
    int expected = 2;
    System.out.println("expected : " + expected);
    // 解法可能會改動輸入,每次呼叫都要用深拷貝
    System.out.println("dfs      : " + solution.dfs(copyOf(grid)));
    System.out.println("bfs      : " + solution.bfs(copyOf(grid)));
}

/** 深拷貝二維字元陣列,避免解法間互相污染輸入 */
private static char[][] copyOf(char[][] grid) {
    char[][] copy = new char[grid.length][];
    for (int i = 0; i < grid.length; i++) {
        copy[i] = grid[i].clone();
    }
    return copy;
}
```

Deep-copy rules by type:
- `int[]` / `char[]`: `nums.clone()`
- `int[][]` / `char[][]`: row-by-row `clone()` (as above)
- `List` inputs: rebuild via the parse method again, or copy-construct
- Immutable inputs (`int`, `String`): no copy needed

## ListNode Builder (uses the problem's own inner class)

```java
/** 由整數陣列建立鏈結串列,回傳頭節點 */
private static ListNode buildList(int[] values) {
    ListNode dummy = new ListNode(0);
    ListNode current = dummy;
    for (int value : values) {
        current.next = new ListNode(value);
        current = current.next;
    }
    return dummy.next;
}

/** 鏈結串列轉字串,方便比對輸出 */
private static String listToString(ListNode head) {
    StringBuilder sb = new StringBuilder("[");
    for (ListNode node = head; node != null; node = node.next) {
        if (sb.length() > 1) {
            sb.append(',');
        }
        sb.append(node.val);
    }
    return sb.append(']').toString();
}
```

Usage: `ListNode head = buildList(LeetCodeInput.parseIntArray("[1,2,3,4,5]"));`

Note: if the problem's `ListNode` is a non-static inner class (`solution.new ListNode(...)` required), either make the builder take the outer instance, or change the inner class to `static` — adjusting inner data-structure classes is harness-adjacent and allowed.

## TreeNode Builder (level-order with nulls)

```java
/** 由層序表示(含 null)建立二元樹,回傳根節點 */
private static TreeNode buildTree(Integer[] values) {
    if (values.length == 0 || values[0] == null) {
        return null;
    }
    TreeNode root = new TreeNode(values[0]);
    java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
    queue.add(root);
    int i = 1;
    while (!queue.isEmpty() && i < values.length) {
        TreeNode node = queue.poll();
        if (i < values.length && values[i] != null) {
            node.left = new TreeNode(values[i]);
            queue.add(node.left);
        }
        i++;
        if (i < values.length && values[i] != null) {
            node.right = new TreeNode(values[i]);
            queue.add(node.right);
        }
        i++;
    }
    return root;
}
```

Usage: `TreeNode root = buildTree(LeetCodeInput.parseIntegerArray("[6,2,8,0,4,null,9]"));`
