from collections import defaultdict
import utils


class Graph:
    def __init__(self, v):
        self.__v = int(v)
        self.__edges = defaultdict(list)

    def add_edge(self, u, v):
        self.__edges[u].append(v)

    def dfs(self, u):
        visited = [False] * (len(self.__edges))
        tree = []
        self.__dfs_util(u, visited, tree)
        utils.swap(tree)
        print(f"\n{tree}")

    def __dfs_util(self, u, visited, tree):
        visited[u] = True
        print(f"{u}", end=" ")
        for e in self.__edges[u]:
            if visited[e] is False:
                tree.append([e, u])
                self.__dfs_util(e, visited, tree)

    def bfs(self, u):
        visited = [False] * (len(self.__edges))
        tree = []
        queue = [u]
        visited[u] = True
        while queue:
            s = queue.pop(0)
            print(f"{s}", end=" ")
            for e in self.__edges[s]:
                if visited[e] is False:
                    visited[e] = True
                    queue.append(e)
                    tree.append([e, s])

        utils.swap(tree)
        print(f"\n{tree}")
