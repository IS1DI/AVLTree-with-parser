public class Node {
    int value;
    Node right;
    Node left;

    Node(int value) {
        this.value = value;
    }
    public void add(Node r){

    }

    public String toString() {
        if (this != null) {
            return value + " " + recursiveToString(left, "│  ") + recursiveToString(right, "│  ");
        } else return "";
    }

    private String recursiveToString(Node n, String o) {
        if (n != null) {
            return "\n" + o + "└──" + n.value + (n.left != null ? recursiveToString(n.left, o + "│  ") : "") + (n.right != null ? recursiveToString(n.right, o + "│  ") : "");
        } else {
            return "";
        }
    }
    public void BFS(){
        ListNew queue  = new LinkedListNew(value);
        if(left!=null) BFS(left,queue);
        if(right!=null) BFS(right,queue);

    }
    public void BFS(Node root, ListNew queue){
        queue.add(value);
        if(root.left!=null) BFS(root.left, queue);
        if(root.right!=null) BFS(root.right,queue);
    }


}
