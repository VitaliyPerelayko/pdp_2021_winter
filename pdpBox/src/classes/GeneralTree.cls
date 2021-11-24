public class GeneralTree implements Iterable<Node> {

    public static final TreeIterator BFS_ITERATOR = new BreadthFirstIterator();
    public static final TreeIterator NLR_ITERATOR = new PreorderIterator();
    private Node root;
    private TreeIterator treeIterator;

    public GeneralTree(TreeIterator treeIterator) {
        this.treeIterator = treeIterator;
    }

    public GeneralTree() {
        this.treeIterator = NLR_ITERATOR;
    }

    public void setRoot(Node node) {
        root = node;
    }

    public Iterator<Node> iterator() {
        treeIterator.setRoot(root);
        return treeIterator;
    }

    public class PreorderIterator extends TreeIterator {

        public PreorderIterator() {
            super();
        }

        public Node next() {
            if (isStackEmpty()) stackPush(root);
            Node currentNode = stackPop();
            if (!currentNode.isLeaf()) {
                List<Node> childNodes = currentNode.children;
                for (Integer i = childNodes.size() - 1; i >= 0; i--) {
                    if (childNodes[i] != null) stackPush(childNodes[i]);
                }
            }
            if (isStackEmpty()) root = null;
            return currentNode;
        }
    }

    public class BreadthFirstIterator extends TreeIterator {

        private List<Node> nextLevel = new List<Node>();

        public Node next() {
            if (isStackEmpty()) stackPush(root);
            Node currentNode = stackPop();
            if (!currentNode.isLeaf()) {
                nextLevel.addAll(currentNode.children);
            }
            if (isStackEmpty()){
                if (nextLevel.isEmpty()) root = null;
                for (Integer i = nextLevel.size() - 1; i >= 0; i--) {
                    if (nextLevel[i] != null) stackPush(nextLevel[i]);
                }
                nextLevel.clear();
            }
            return currentNode;
        }
    }

    public abstract class TreeIterator implements Iterator<Node> {

        protected Node root;
        private List<Node> visited = new List<Node>();

        protected void stackPush(Node node) {
            visited.add(node);
        }

        protected Node stackPop() {
            return visited.remove(visited.size() - 1);
        }

        protected Boolean isStackEmpty() {
            return visited.isEmpty();
        }

        public TreeIterator() {
        }

        public Boolean hasNext() {
            return root != null;
        }

        public void setRoot(Node root) {
            this.root = root;
        }
    }

    public class Node {
        private List<Node> children;
        private Node parent;
        private GeneralTree.Entry data;

        public Node(GeneralTree.Entry data) {
            this.data = data;
        }

        public Node(GeneralTree.Entry data, Node parent) {
            this.data = data;
            this.parent = parent;
        }

        public GeneralTree.Entry getData() {
            return data;
        }

        public void addChild(GeneralTree.Entry data) {
            Node child = new Node(data);
            addChild(child);
        }

        public void addChild(Node child) {
            child.parent = this;
            if (children == null) children = new List<Node>();
            children.add(child);
        }

        public Boolean isLeaf() {
            return children == null;
        }
    }

    public class Entry {
        private Object key;
        private Object value;

        public Entry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        override public String toString() {
            return '{key: ' + key + ', value: ' + value + '}';
        }

        public Boolean equals(Object obj) {
            if (obj instanceof GeneralTree.Entry) {
                GeneralTree.Entry entry = (GeneralTree.Entry) obj;
                return this.key.equals(entry.key);
            }
            return false;
        }
    }
}