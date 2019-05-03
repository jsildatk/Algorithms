class Node:

    def __init__(self, key, rank):
        self.key = key
        self.parent = self
        self.rank = rank
