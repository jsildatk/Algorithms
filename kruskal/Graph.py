from Disjoint_set import DisjointSet


class Graph:

    def __init__(self, v, e):
        self.V = v
        self.E = e
        self.edges = list()

    def add_edge(self, edge):
        self.edges.append(edge)

    def kruskal(self):
        dj = DisjointSet()
        for i in range(0, self.V):
            dj.make_set()

        self.edges = sorted(self.edges, key=lambda edge: edge.weight)
        for i in range(0, self.E):
            r_src = dj.find_set(dj.sets[self.edges[i].src])
            r_dest = dj.find_set(dj.sets[self.edges[i].dest])
            if r_src != r_dest:
                print(f"{self.edges[i]}")
                DisjointSet.union(r_src, r_dest)