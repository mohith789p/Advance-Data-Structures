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
            Node child = node.children[i];
            if (child.numKeys == ORDER - 1) {
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

        parent.keys[index] = fullNode.keys[fullNode.numKeys / 2];
        parent.numKeys++;

        newNode.isLeaf = fullNode.isLeaf;
        for (int i = fullNode.numKeys / 2 + 1; i < fullNode.numKeys; i++) {
            newNode.keys[i - fullNode.numKeys / 2 - 1] = fullNode.keys[i];
            fullNode.keys[i] = 0;
        }
        newNode.numKeys = fullNode.numKeys / 2;

        if (!fullNode.isLeaf) {
            for (int i = fullNode.numKeys / 2 + 1; i < ORDER; i++) {
                newNode.children[i - fullNode.numKeys / 2 - 1] = fullNode.children[i];
                fullNode.children[i] = null;
            }
        }

        fullNode.numKeys = fullNode.numKeys / 2;

        for (int i = parent.numKeys - 1; i > index; i--) {
            parent.children[i + 1] = parent.children[i];
            parent.keys[i] = parent.keys[i - 1];
        }
        parent.children[index + 1] = newNode;
    }

    public void delete(int key) {
        delete(root, key);
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

        parent.keys[index] = 0;
        parent.numKeys--;

        for (int i = index + 1; i < parent.numKeys; i++) {
            parent.children[i] = parent.children[i + 1];
            parent.keys[i] = parent.keys[i + 1];
        }

        right.numKeys = 0;
        Arrays.fill(right.keys, 0);
        Arrays.fill(right.children, null);
    }

    public void display() {
        // display(root, "", true);
        display(root, 0);
    }

    private void display(Node node, int level) {
        if (node != null) {
            for (int i = 0; i < node.numKeys; i++) {
                System.out.print("Level " + level + " Key: " + node.keys[i] + " ");
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
        Random rand = new Random();

        for (int i = 0; i < 100; i++) {
            bTree.insert(rand.nextInt(1000));
        }

        bTree.display();

        System.out.println("Searching for key 50: " + bTree.search(50));

        bTree.delete(50);
        System.out.println("After deleting key 50:");
        bTree.display();
    }
}

/*
 * private void display(Node node, String indent, boolean isRight) {
 * if (node != null) {
 * // Print the current node with indentation and proper label (L---- or R----)
 * System.out.print(indent);
 * if (isRight) {
 * System.out.print("R----");
 * indent += "   "; // Add space for further indentation if it's a right child
 * } else {
 * System.out.print("L----");
 * indent += "|  "; // Add '|' to show left-child connections
 * }
 * System.out.println(node.keys[0]); // Display the node's key (Assuming there's
 * at least one key)
 * 
 * // Recursively display the left and right children
 * if (node.children[0] != null) {
 * display(node.children[0], indent, false); // Left child
 * }
 * if (node.children[1] != null) {
 * display(node.children[1], indent, true); // Right child
 * }
 * }
 * }
 */