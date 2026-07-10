#!/usr/bin/env python3
"""從 LeetCode GraphQL API 抓取題目資訊,輸出 JSON 供 init-problem skill 使用。

用法:
    python3 fetch_problem.py 200
    python3 fetch_problem.py "200. Number of Islands"
    python3 fetch_problem.py number-of-islands
    python3 fetch_problem.py "Number of Islands"

輸出欄位:
    id, title, titleSlug, url, difficulty, difficultyInterface,
    description(純文字), javaSnippet, patterns(已對應的介面), unmappedTags
"""

import json
import re
import sys
import urllib.request
from html.parser import HTMLParser

GRAPHQL_URL = "https://leetcode.com/graphql"

HEADERS = {
    "Content-Type": "application/json",
    "Referer": "https://leetcode.com",
    "User-Agent": "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36",
}

DETAIL_QUERY = """
query($titleSlug: String!) {
  question(titleSlug: $titleSlug) {
    questionFrontendId
    title
    titleSlug
    difficulty
    content
    topicTags { name }
    codeSnippets { langSlug code }
  }
}
"""

SEARCH_QUERY = """
query($filters: QuestionListFilterInput) {
  questionList(categorySlug: "", limit: 50, skip: 0, filters: $filters) {
    data { questionFrontendId title titleSlug difficulty }
  }
}
"""

# LeetCode topic tag → 本專案 pattern 介面對應表
# 未列出的 tag(如 Trie, Design, Simulation)會回報於 unmappedTags,由使用者決定
TAG_TO_PATTERN = {
    "Array": "ArrayPattern",
    "String": "StringPattern",
    "Hash Table": "HashTablePattern",
    "Dynamic Programming": "DynamicProgrammingPattern",
    "Memoization": "DynamicProgrammingPattern",
    "Math": "MathPattern",
    "Sorting": "SortingPattern",
    "Greedy": "GreedyPattern",
    "Depth-First Search": "DepthFirstSearchPattern",
    "Breadth-First Search": "BreadthFirstSearchPattern",
    "Binary Search": "BinarySearchPattern",
    "Matrix": "MatrixPattern",
    "Tree": "TreePattern",
    "Binary Tree": "BinaryTreePattern",
    "Binary Search Tree": "BinarySearchTreePattern",
    "Two Pointers": "TwoPointersPattern",
    "Bit Manipulation": "BitManipulationPattern",
    "Stack": "StackPattern",
    "Monotonic Stack": "StackPattern",
    "Queue": "QueuePattern",
    "Monotonic Queue": "QueuePattern",
    "Heap (Priority Queue)": "HeapPattern",
    "Graph": "GraphPattern",
    "Topological Sort": "GraphPattern",
    "Shortest Path": "GraphPattern",
    "Linked List": "LinkedListPattern",
    "Doubly-Linked List": "LinkedListPattern",
    "Union Find": "UnionFindPattern",
    "Union-Find": "UnionFindPattern",
    "Backtracking": "BacktrackingPattern",
    "Divide and Conquer": "DivideAndConquerPattern",
    "Sliding Window": "SlidingWindowPattern",
}


class HtmlToText(HTMLParser):
    """將 LeetCode 題目描述的 HTML 轉為純文字。"""

    BLOCK_TAGS = {"p", "div", "ul", "ol", "pre", "blockquote"}

    def __init__(self):
        super().__init__(convert_charrefs=True)
        self.parts = []

    def handle_starttag(self, tag, attrs):
        if tag == "br" or tag in self.BLOCK_TAGS:
            self.parts.append("\n")
        elif tag == "li":
            self.parts.append("\n- ")
        elif tag == "sup":
            self.parts.append("^")

    def handle_endtag(self, tag):
        if tag in self.BLOCK_TAGS:
            self.parts.append("\n")

    def handle_data(self, data):
        self.parts.append(data)

    def text(self):
        raw = "".join(self.parts)
        lines = [line.rstrip() for line in raw.split("\n")]
        collapsed = []
        for line in lines:
            if line or (collapsed and collapsed[-1]):
                collapsed.append(line)
        return "\n".join(collapsed).strip()


def graphql(query, variables):
    payload = json.dumps({"query": query, "variables": variables}).encode()
    request = urllib.request.Request(GRAPHQL_URL, data=payload, headers=HEADERS)
    with urllib.request.urlopen(request, timeout=20) as response:
        body = json.load(response)
    if body.get("errors"):
        fail("GraphQL 錯誤: " + json.dumps(body["errors"], ensure_ascii=False))
    return body["data"]


def to_slug(name):
    return re.sub(r"[^a-z0-9]+", "-", name.lower()).strip("-")


def fetch_detail(slug):
    return graphql(DETAIL_QUERY, {"titleSlug": slug})["question"]


def search(keywords):
    data = graphql(SEARCH_QUERY, {"filters": {"searchKeywords": keywords}})
    return data["questionList"]["data"]


def resolve(raw):
    """將使用者輸入(編號 / 編號.名稱 / slug / 名稱)解析為題目詳細資料。"""
    raw = raw.strip()

    numbered = re.fullmatch(r"(\d+)\.?\s*(.*)", raw)
    if numbered:
        number, name = numbered.group(1), numbered.group(2).strip()
        if name:
            detail = fetch_detail(to_slug(name))
            if detail and detail["questionFrontendId"] == number:
                return detail
        for hit in search(number):
            if hit["questionFrontendId"] == number:
                return fetch_detail(hit["titleSlug"])
        fail(f"找不到編號 {number} 的題目,請改用完整題名(例如 \"200. Number of Islands\")")

    detail = fetch_detail(to_slug(raw))
    if detail:
        return detail

    hits = search(raw)
    if len(hits) == 1:
        return fetch_detail(hits[0]["titleSlug"])
    if hits:
        candidates = [f'{h["questionFrontendId"]}. {h["title"]}' for h in hits[:10]]
        fail("題名不唯一,候選題目:\n" + "\n".join(candidates))
    fail(f"找不到題目: {raw}")


def java_snippet(detail):
    for snippet in detail.get("codeSnippets") or []:
        if snippet["langSlug"] == "java":
            return snippet["code"]
    return None


def fail(message):
    print(json.dumps({"error": message}, ensure_ascii=False))
    sys.exit(1)


def main():
    if len(sys.argv) < 2:
        fail("用法: fetch_problem.py <題號 | \"題號. 題名\" | slug | 題名>")

    detail = resolve(" ".join(sys.argv[1:]))

    parser = HtmlToText()
    parser.feed(detail.get("content") or "")

    tags = [tag["name"] for tag in detail.get("topicTags") or []]
    patterns = sorted({TAG_TO_PATTERN[t] for t in tags if t in TAG_TO_PATTERN})
    unmapped = [t for t in tags if t not in TAG_TO_PATTERN]

    print(json.dumps({
        "id": int(detail["questionFrontendId"]),
        "title": detail["title"],
        "titleSlug": detail["titleSlug"],
        "url": f'https://leetcode.com/problems/{detail["titleSlug"]}/',
        "difficulty": detail["difficulty"],
        "difficultyInterface": detail["difficulty"],
        "description": parser.text(),
        "javaSnippet": java_snippet(detail),
        "patterns": patterns,
        "unmappedTags": unmapped,
    }, ensure_ascii=False, indent=2))


if __name__ == "__main__":
    main()
