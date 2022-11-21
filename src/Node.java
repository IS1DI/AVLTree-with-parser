public class Node {
    int value;
    Node right;
    Node left;

    Node(int value) {
        this.value = value;
    }

    public String toString() {
        if (this != null) {
            return value + " " + recursiveToString(left, "") + recursiveToString(right, "");
        } else return "";
    }

    private String recursiveToString(Node n, String o) {
        if (n != null) {
            return "\n" + o + "└──" + n.value + (n.left != null ? recursiveToString(n.left, o + "│  ") : "") + (n.right != null ? recursiveToString(n.right, o + "│  ") : "");
        } else {
            return "";
        }
    }
}
//public String toString() {
//        if (this != null) {
//            return value + " " + recursiveToString(left, "\n ") + recursiveToString(right, "\n ");
//        } else return "";
//    }
//
//    private String recursiveToString(Node n, String o) {
//        if (n != null) {
//            return o + n.value + (n.left != null ? recursiveToString(n.left, o + " ") : "") + (n.right != null ? recursiveToString(n.right, o + " ") : "");
//        } else {
//            return "";
//        }
//    }
