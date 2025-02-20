import java.util.*;

class BTree {
    private static final int ORDER = 5;
    private Node root;

    private static class Node {
        int[] keys = new int[ORDER - 1];
        Node[] children = new Node[ORDER];
        int numKeys = 0;
        boolean isLeaf = true;

        Node() {
            Arrays.fill(children, null);
        }
    }

    public BTree() {
        root = new Node();
    }

    public boolean search(int key) {
        return search(root, key);
    }

    private boolean search(Node node, int key) {
        int i = 0;
        while (i < node.numKeys && key > node.keys[i]) {
            i++;
        }
        if (i < node.numKeys && node.keys[i] == key) {
            return true;
        }
        if (node.isLeaf) {
            return false;
        }
        return search(node.children[i], key);
    }

    public void insert(int key) {
        Node root = this.root;
        if (root.numKeys == ORDER - 1) {
            Node newRoot = new Node();
            newRoot.children[0] = this.root;
            newRoot.isLeaf = false;
            split(newRoot, 0);
            this.root = newRoot;
        }
        insertNonFull(this.root, key);
    }

    private void insertNonFull(Node node, int key) {
        int i = node.numKeys - 1;
        if (node.isLeaf) {
            while (i >= 0 && key < node.keys[i]) {
                node.keys[i + 1] = node.keys[i];
                i--;
            }
            node.keys[i + 1] = key;
            node.numKeys++;
        } else {
            while (i >= 0 && key < node.keys[i]) {
                i--;
            }
            i++;
            if (node.children[i].numKeys == ORDER - 1) {
                split(node, i);
                if (key > node.keys[i]) {
                    i++;
                }
            }
            insertNonFull(node.children[i], key);
        }
    }

    private void split(Node parent, int index) {
        Node fullNode = parent.children[index];
        Node newNode = new Node();
        newNode.isLeaf = fullNode.isLeaf;

        int mid = fullNode.numKeys / 2;
        int midKey = fullNode.keys[mid];

        // Shift parent keys and children
        for (int j = parent.numKeys; j > index; j--) {
            parent.keys[j] = parent.keys[j - 1];
            parent.children[j + 1] = parent.children[j];
        }

        parent.keys[index] = midKey;
        parent.numKeys++;

        // Move second half of keys to newNode
        for (int j = mid + 1, k = 0; j < fullNode.numKeys; j++, k++) {
            newNode.keys[k] = fullNode.keys[j];
            fullNode.keys[j] = 0; // Clear old keys
        }
        newNode.numKeys = fullNode.numKeys - mid - 1;
        fullNode.numKeys = mid;

        // Move second half of children to newNode
        if (!fullNode.isLeaf) {
            for (int j = mid + 1, k = 0; j <= ORDER - 1; j++, k++) {
                newNode.children[k] = fullNode.children[j];
                fullNode.children[j] = null;
            }
        }

        parent.children[index + 1] = newNode;
    }

    public void delete(int key) {
        delete(root, key);
        if (root.numKeys == 0 && !root.isLeaf) {
            root = root.children[0]; // Root becomes its only child
        }
    }

    private void delete(Node node, int key) {
        int i = 0;
        while (i < node.numKeys && key > node.keys[i]) {
            i++;
        }

        if (i < node.numKeys && node.keys[i] == key) {
            if (node.isLeaf) {
                for (int j = i; j < node.numKeys - 1; j++) {
                    node.keys[j] = node.keys[j + 1];
                }
                node.keys[node.numKeys - 1] = 0;
                node.numKeys--;
            } else {
                Node child = node.children[i];
                if (child.numKeys > ORDER / 2) {
                    node.keys[i] = child.keys[child.numKeys - 1];
                    delete(child, child.keys[child.numKeys - 1]);
                } else {
                    merge(node, i);
                    delete(node.children[i], key);
                }
            }
        } else if (!node.isLeaf) {
            delete(node.children[i], key);
        }
    }

    private void merge(Node parent, int index) {
        Node left = parent.children[index];
        Node right = parent.children[index + 1];

        left.keys[left.numKeys] = parent.keys[index];
        left.numKeys++;

        for (int i = 0; i < right.numKeys; i++) {
            left.keys[left.numKeys + i] = right.keys[i];
            left.children[left.numKeys + i] = right.children[i];
            right.children[i] = null;
        }

        left.numKeys += right.numKeys;
        parent.numKeys--;

        for (int i = index; i < parent.numKeys; i++) {
            parent.keys[i] = parent.keys[i + 1];
            parent.children[i + 1] = parent.children[i + 2];
        }
    }

    public void display() {
        display(root, 0);
    }

    private void display(Node node, int level) {
        if (node != null) {
            System.out.print("Level " + level + ": ");
            for (int i = 0; i < node.numKeys; i++) {
                System.out.print(node.keys[i] + " ");
            }
            System.out.println();
            if (!node.isLeaf) {
                for (Node child : node.children) {
                    if (child != null) {
                        display(child, level + 1);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        BTree bTree = new BTree();
        Set<Integer> insertedKeys = new HashSet<>();
        Random rand = new Random();

        while (insertedKeys.size() < 50) {
            int num = rand.nextInt(100);
            if (!insertedKeys.contains(num)) {
                insertedKeys.add(num);
                bTree.insert(num);
            }
        }

        System.out.println("B-Tree after insertion:");
        bTree.display();

        int deleteKey = insertedKeys.iterator().next(); // Pick first key for deletion
        System.out.println("Deleting key: " + deleteKey);
        bTree.delete(deleteKey);
        System.out.println("B-Tree after deletion:");
        bTree.display();
    }
}