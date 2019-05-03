from Disjoint_set import DisjointSet


if __name__ == "__main__":
    dj = DisjointSet()
    for i in range(0, 10):
        dj.make_set(i)

    DisjointSet.union(dj.find_set(dj.sets[0]), dj.find_set(dj.sets[1]))
    DisjointSet.union(dj.find_set(dj.sets[2]), dj.find_set(dj.sets[3]))
    DisjointSet.union(dj.find_set(dj.sets[1]), dj.find_set(dj.sets[2]))
    DisjointSet.union(dj.find_set(dj.sets[5]), dj.find_set(dj.sets[6]))
    DisjointSet.union(dj.find_set(dj.sets[7]), dj.find_set(dj.sets[8]))
    DisjointSet.union(dj.find_set(dj.sets[3]), dj.find_set(dj.sets[5]))
    DisjointSet.union(dj.find_set(dj.sets[0]), dj.find_set(dj.sets[7]))

    for n in dj.sets:
        print(f"Key: {n.key}")
        print(f"Parent: {n.parent.key}")
        print("-----------------")

    dj.find_path_to_root(dj.sets[7])
    print()
    dj.find_path_to_root(dj.sets[5])
    print()
    dj.find_path_to_root(dj.sets[1])
    print()
