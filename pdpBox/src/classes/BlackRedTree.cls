public class BlackRedTree {

    private Node root;

    public void setRoot(Node node) {
        root = node;
    }

    public class Node {
        private Node parent;
        private Node leftNode;
        private Node rightNode;
        private Boolean black;
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

        public Boolean isRoot() {
            return parent == null;
        }

        public Boolean isLeaf() {
            return children == null;
        }

        public void removeParent() {
            parent = null;
        }
    }

    public class Entry {
        private String key;
        private Object value;

        public Entry(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        override public String toString() {
            return '{key: ' + key + ', value: ' + value + '}';
        }

        public Boolean equals(Object obj) {
            if (obj instanceof Entry) {
                Entry entry = (Entry) obj;
                return this.key.equals(entry.key);
            }
            return false;
        }
    }
}