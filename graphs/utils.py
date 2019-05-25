def read_from_file(path):
    result = []
    with open(path, "r") as file:
        n = file.readline()
        for line in file:
            temp = []
            for c in line.replace(" ", "").replace("\n", ""):
                temp.append(int(c))
            result.append(temp)

    return n, result


def get_edges(matrix):
    result = []
    for i in range(len(matrix)):
        for j in range(len(matrix[i])):
            if matrix[i][j] == 1:
                result.append([i, j])

    return result


def add_edges(graph, edges):
    for e in edges:
        graph.add_edge(e[0], e[1])


def swap(tree):
    for row in tree:
        if row[0] > row[1]:
            temp = row[0]
            row[0] = row[1]
            row[1] = temp
