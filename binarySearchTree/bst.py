class Node:
    def __init__(self, key):
        self.key = key
        self.left = None
        self.right = None 

def find_min_value(node): # find minimum value 
    current = node 
    while (current.left is not None):
        current = current.left
    return current

def in_order(root): # left - middle - right
    if root is not None:
        in_order(root.left)
        print(root.key)
        in_order(root.right)
        
def insert(node, key):
    if node is None:
        return Node(key)
    if key < node.key:
        node.left = insert(node.left, key)
    else:
        node.right = insert(node.right, key)
    return node

def search(root, key):
    if root is None or root.key == key:
        return root
    if root.key < key: # if key is greater => right subtree
        return search(root.right, key)
    return search(root.left, key) # left subtree

def delete(root, key):
    if root is None:
        return root
    if key < root.key: # go to left subtree
        root.left = delete(root.left, key)
    elif key > root.key: # go to right subtree
        root.right = delete(root.right, key)
    else: # key == root.key => node to be deleted
        if root.left is None:
            temp = root.right
            root = None
            return temp
        elif root.right is None:
            temp = root.left 
            root = None 
            return temp

        temp =  find_min_value(root.right) # minimum value in right subtree
        root.key = temp.key
        root.right = delete(root.right, temp.key)
        return root

if __name__ == "__main__":
    root = None 
    root = insert(root, 18)
    root = insert(root, 11)
    root = insert(root, 6)
    root = insert(root, 30)
    root = insert(root, 21)
    root = insert(root, 19)
    root = insert(root, 8)
    root = insert(root, 22)
    root = insert(root, 23)
    root = insert(root, 5)
    root = insert(root, 20)
    root = insert(root, 26)
    root = insert(root, 17)
    in_order(root)
    print("------------------------")
    print(search(root, 22))
    print(search(root, 543))
    print("------------------------")
    delete(root, 11)
    in_order(root)