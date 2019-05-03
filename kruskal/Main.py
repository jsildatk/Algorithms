from Graph import Graph
from Edge import Edge


if __name__ == "__main__":
    g = Graph(4, 5)
    g.add_edge(Edge(0, 1, 10))
    g.add_edge(Edge(0, 2, 6))
    g.add_edge(Edge(0, 3, 5))
    g.add_edge(Edge(1, 3, 15))
    g.add_edge(Edge(2, 3, 4))
    g.kruskal()
