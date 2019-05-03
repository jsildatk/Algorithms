from Node import Node


class DisjointSet:

    def __init__(self):
        self.sets = list()

    def make_set(self):
        self.sets.append(Node())

    def find_set(self, node):
        if node != node.parent:
            node.parent = self.find_set(node.parent)

        return node.parent

    @staticmethod
    def union(node1, node2):
        if node1.rank > node2.rank:
            node2.parent = node1
        else:
            node1.parent = node2
            if node1.rank == node2.rank:
                node2.rank += 1
