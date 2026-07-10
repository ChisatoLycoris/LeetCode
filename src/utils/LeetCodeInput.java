package utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 將 LeetCode 測資字串解析為 Java 資料結構的除錯工具。
 *
 * <p>搭配 Java 21 text block,可直接貼上 LeetCode 失敗測資:</p>
 * <pre>{@code
 * char[][] grid = LeetCodeInput.parseCharMatrix("""
 *         [["1","1","0"],["0","1","0"]]
 *         """);
 * int[] nums = LeetCodeInput.parseIntArray("[2,7,11,15]");
 * int[][] edges = LeetCodeInput.parseIntMatrix("[[1,2],[2,3]]");
 * Integer[] tree = LeetCodeInput.parseIntegerArray("[6,2,8,null,4]"); // 保留 null,可自行建樹
 * }</pre>
 *
 * <p>輸入允許帶有 LeetCode 常見的變數前綴,例如 {@code "grid = [[...]]"} 會自動去除 {@code grid =}。</p>
 */
public final class LeetCodeInput {

    private LeetCodeInput() {
        // 工具類別,不允許實例化
    }

    // ===== 常用型別的解析方法 =====

    /**
     * 解析單一整數,例如 {@code "42"} 或 {@code "target = 42"}。
     */
    public static int parseInt(String input) {
        return requireInt(parse(input));
    }

    /**
     * 解析整數陣列,例如 {@code "[2,7,11,15]"}。
     */
    public static int[] parseIntArray(String input) {
        return toIntArray(requireList(parse(input)));
    }

    /**
     * 解析二維整數陣列,例如 {@code "[[1,2],[3,4]]"}。
     */
    public static int[][] parseIntMatrix(String input) {
        List<Object> rows = requireList(parse(input));
        int[][] matrix = new int[rows.size()][];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = toIntArray(requireList(rows.get(i)));
        }
        return matrix;
    }

    /**
     * 解析可含 null 的整數陣列,例如樹的層序表示 {@code "[6,2,8,null,4]"}。
     * 保留 null 元素,可自行搭配建樹邏輯使用。
     */
    public static Integer[] parseIntegerArray(String input) {
        List<Object> list = requireList(parse(input));
        Integer[] result = new Integer[list.size()];
        for (int i = 0; i < result.length; i++) {
            Object value = list.get(i);
            result[i] = value == null ? null : requireInt(value);
        }
        return result;
    }

    /**
     * 解析字元陣列,例如 {@code "[\"a\",\"b\",\"c\"]"}(每個元素必須是單一字元)。
     */
    public static char[] parseCharArray(String input) {
        return toCharArray(requireList(parse(input)));
    }

    /**
     * 解析二維字元陣列,例如 200. Number of Islands 的
     * {@code "[[\"1\",\"1\"],[\"0\",\"1\"]]"}(每個元素必須是單一字元)。
     */
    public static char[][] parseCharMatrix(String input) {
        List<Object> rows = requireList(parse(input));
        char[][] grid = new char[rows.size()][];
        for (int i = 0; i < grid.length; i++) {
            grid[i] = toCharArray(requireList(rows.get(i)));
        }
        return grid;
    }

    /**
     * 解析字串陣列,例如 {@code "[\"flower\",\"flow\",\"flight\"]"}。
     */
    public static String[] parseStringArray(String input) {
        List<Object> list = requireList(parse(input));
        String[] result = new String[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = requireString(list.get(i));
        }
        return result;
    }

    /**
     * 解析二維字串陣列,例如 {@code "[[\"eat\",\"tea\"],[\"tan\"]]"}。
     */
    public static String[][] parseStringMatrix(String input) {
        List<Object> rows = requireList(parse(input));
        String[][] matrix = new String[rows.size()][];
        for (int i = 0; i < matrix.length; i++) {
            List<Object> row = requireList(rows.get(i));
            matrix[i] = new String[row.size()];
            for (int j = 0; j < row.size(); j++) {
                matrix[i][j] = requireString(row.get(j));
            }
        }
        return matrix;
    }

    /**
     * 解析整數清單,例如 {@code "[1,2,3]"} → {@code List<Integer>}。
     */
    public static List<Integer> parseIntList(String input) {
        List<Object> list = requireList(parse(input));
        List<Integer> result = new ArrayList<>(list.size());
        for (Object value : list) {
            result.add(requireInt(value));
        }
        return result;
    }

    /**
     * 解析巢狀整數清單,例如 {@code "[[1,2],[3]]"} → {@code List<List<Integer>>}。
     */
    public static List<List<Integer>> parseIntListList(String input) {
        List<Object> rows = requireList(parse(input));
        List<List<Integer>> result = new ArrayList<>(rows.size());
        for (Object row : rows) {
            List<Object> items = requireList(row);
            List<Integer> converted = new ArrayList<>(items.size());
            for (Object value : items) {
                converted.add(requireInt(value));
            }
            result.add(converted);
        }
        return result;
    }

    /**
     * 通用解析入口,回傳結構為 {@code List<Object>} / {@code String} /
     * {@code Long} / {@code Double} / {@code Boolean} / {@code null}。
     * 上述型別方法皆以此為基礎;遇到特殊測資格式時可直接使用。
     */
    public static Object parse(String input) {
        Parser parser = new Parser(stripAssignmentPrefix(input));
        Object value = parser.parseValue();
        parser.expectEnd();
        return value;
    }

    // ===== 除錯輔助:格式化輸出 =====

    /**
     * 將字元格網格式化為易讀的多行字串,方便肉眼比對測資。
     */
    public static String gridToString(char[][] grid) {
        StringBuilder sb = new StringBuilder();
        for (char[] row : grid) {
            for (int j = 0; j < row.length; j++) {
                if (j > 0) {
                    sb.append(' ');
                }
                sb.append(row[j]);
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * 將整數矩陣格式化為易讀的多行字串。
     */
    public static String matrixToString(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : matrix) {
            for (int j = 0; j < row.length; j++) {
                if (j > 0) {
                    sb.append(' ');
                }
                sb.append(row[j]);
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    // ===== 型別轉換與檢查 =====

    private static int[] toIntArray(List<Object> list) {
        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = requireInt(list.get(i));
        }
        return result;
    }

    private static char[] toCharArray(List<Object> list) {
        char[] result = new char[list.size()];
        for (int i = 0; i < result.length; i++) {
            String s = requireString(list.get(i));
            if (s.length() != 1) {
                throw new IllegalArgumentException("預期單一字元,但取得 \"" + s + "\"");
            }
            result[i] = s.charAt(0);
        }
        return result;
    }

    private static List<Object> requireList(Object value) {
        if (value instanceof List<?> list) {
            @SuppressWarnings("unchecked")
            List<Object> result = (List<Object>) list;
            return result;
        }
        throw new IllegalArgumentException("預期陣列,但取得: " + describe(value));
    }

    private static int requireInt(Object value) {
        if (value instanceof Long number) {
            if (number < Integer.MIN_VALUE || number > Integer.MAX_VALUE) {
                throw new IllegalArgumentException("數值超出 int 範圍: " + number);
            }
            return number.intValue();
        }
        throw new IllegalArgumentException("預期整數,但取得: " + describe(value));
    }

    private static String requireString(Object value) {
        if (value instanceof String s) {
            return s;
        }
        throw new IllegalArgumentException("預期字串,但取得: " + describe(value));
    }

    private static String describe(Object value) {
        return value == null ? "null" : value.getClass().getSimpleName() + " (" + value + ")";
    }

    /**
     * 去除 LeetCode 測資常見的 {@code name =} 前綴。
     */
    private static String stripAssignmentPrefix(String input) {
        int eq = input.indexOf('=');
        if (eq > 0 && input.substring(0, eq).trim().matches("[A-Za-z_$][A-Za-z0-9_$]*")) {
            return input.substring(eq + 1);
        }
        return input;
    }

    // ===== 底層遞迴下降解析器 =====

    private static final class Parser {
        private final String text;
        private int pos;

        Parser(String text) {
            this.text = text;
        }

        Object parseValue() {
            skipWhitespace();
            char c = peek();
            if (c == '[') {
                return parseArray();
            }
            if (c == '"' || c == '\'') {
                return parseString(c);
            }
            if (c == 'n') {
                expect("null");
                return null;
            }
            if (c == 't') {
                expect("true");
                return Boolean.TRUE;
            }
            if (c == 'f') {
                expect("false");
                return Boolean.FALSE;
            }
            return parseNumber();
        }

        private List<Object> parseArray() {
            pos++; // 略過 '['
            List<Object> items = new ArrayList<>();
            skipWhitespace();
            if (peek() == ']') {
                pos++;
                return items;
            }
            while (true) {
                items.add(parseValue());
                skipWhitespace();
                char c = peek();
                pos++;
                if (c == ']') {
                    return items;
                }
                if (c != ',') {
                    throw error("預期 ',' 或 ']',但取得 '" + c + "'");
                }
            }
        }

        private String parseString(char quote) {
            pos++; // 略過開頭引號
            StringBuilder sb = new StringBuilder();
            while (pos < text.length()) {
                char c = text.charAt(pos++);
                if (c == quote) {
                    return sb.toString();
                }
                if (c == '\\' && pos < text.length()) {
                    char escaped = text.charAt(pos++);
                    sb.append(switch (escaped) {
                        case 'n' -> '\n';
                        case 't' -> '\t';
                        default -> escaped; // \" \\ \' 等直接取原字元
                    });
                } else {
                    sb.append(c);
                }
            }
            throw error("字串缺少結尾引號");
        }

        private Object parseNumber() {
            int start = pos;
            while (pos < text.length() && "+-0123456789.eE".indexOf(text.charAt(pos)) >= 0) {
                pos++;
            }
            String token = text.substring(start, pos);
            if (token.isEmpty()) {
                throw error("無法解析的字元 '" + text.charAt(pos) + "'");
            }
            try {
                if (token.indexOf('.') >= 0 || token.indexOf('e') >= 0 || token.indexOf('E') >= 0) {
                    return Double.parseDouble(token);
                }
                return Long.parseLong(token);
            } catch (NumberFormatException e) {
                throw error("無法解析的數值 \"" + token + "\"");
            }
        }

        void expectEnd() {
            skipWhitespace();
            if (pos < text.length()) {
                throw error("測資結尾有多餘字元 '" + text.charAt(pos) + "'");
            }
        }

        private void expect(String literal) {
            if (!text.startsWith(literal, pos)) {
                throw error("預期 \"" + literal + "\"");
            }
            pos += literal.length();
        }

        private char peek() {
            if (pos >= text.length()) {
                throw error("未預期的測資結尾");
            }
            return text.charAt(pos);
        }

        private void skipWhitespace() {
            while (pos < text.length() && Character.isWhitespace(text.charAt(pos))) {
                pos++;
            }
        }

        private IllegalArgumentException error(String message) {
            return new IllegalArgumentException("解析失敗 (位置 " + pos + "): " + message);
        }
    }
}
