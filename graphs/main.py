from graph import Graph
import utils


if __name__ == "__main__":
    v, adjacency_matrix = utils.read_from_file("matrix")
    edges = utils.get_edges(adjacency_matrix)
    g = Graph(v)
    utils.add_edges(g, edges)
    g.dfs(2)
    print("-----------------------------")
    g1 = Graph(v)
    utils.add_edges(g1, edges)
    g1.bfs(4)
    print("-----------------------------")
