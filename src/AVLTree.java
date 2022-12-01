public class AVLTree {

    private Node root;

    AVLTree(Node root) {
        if (root == null) {
            throw new NullPointerException();
        } else {
            addAll(root);
        }
    }
    public String printInOrder(){
        return printInOrderRec(root,0);
    }
    private static String printInOrderRec(Node p, int level){
        if(p!=null){
            String f = "";
            f+=printInOrderRec(p.left, level + 1);
            for(int i = 0; i < level; i++) f+="\t";
            f+= p.value +"\n" ;
            f+=printInOrderRec(p.right, level + 1);
            return f;
        }
        return "";
    }
    public String printPreOrder(){
        return printPreOrderRec(root,0);
    }
    private static String printPreOrderRec(Node p, int level){
        if(p!=null){
            String f = "";
            for(int i = 0; i < level; i++)f+= "\t";
            f+= p.value +"\n" ;
            f+= printPreOrderRec(p.left, level + 1);
                          // вывод корня поддерева
            f+=printPreOrderRec(p.right, level + 1);
            return f;// вывод левого поддерева
        }
        return "";
    }
    public String printPostOrder(){
        return printPostOrderRec(root,0);
    }
    private static String printPostOrderRec(Node p, int level){
        if(p!=null){
            String f = "";

            f+=printPostOrderRec(p.left, level + 1);
            // вывод корня поддерева
            f+=printPostOrderRec(p.right, level + 1);
            for(int i = 0; i < level; i++)f+= "\t";
            f+= p.value +"\n" ;
            return f;
        }
        return "";
    }
    public String printBFS(){
        return printBFSRec(root);
    };
    private static String printBFSRec(Node node){
        ListNew<Node> queue = new LinkedListNew();
        String values = "";
        queue.add(node);
        while(!queue.isEmpty()){
            Node tempNode = queue.getFirst();
            queue.removeFirst();
            values +=  tempNode.value + " ";
            if (tempNode.left!=null){
                queue.add(tempNode.left);
            }
            if (tempNode.right!=null){
                queue.add(tempNode.right);
            }
        }
        return values;
    }

    public void addAll(Node root) {
        if (root != null) {
            add(root.value);
            if (root.left != null) addAll(root.left);
            if (root.right != null) addAll(root.right);
        } else {
            throw new NullPointerException();
        }
    }

    public void add(int value) {
        if (root == null) {
            root = new Node(value);
        } else if (!find(value)) {

            if (root.value > value) {
                root.left = add(value, root.left);

            } else {
                root.right = add(value, root.right);

            }
        }
        root = balance(root);

    }

    private Node add(int value, Node r) {
        if (r == null) {
            r = new Node(value);

        } else if (r.value > value) {
            r.left = add(value, r.left);
        } else {
            r.right = add(value, r.right);
        }
        return r;

    }

    public void remove(int value) {
        if(root==null){
            return;
        }else if(root.value==value){
            Node temp = root;
            root = null;
            addAll(temp.right);
            addAll(temp.left);
        }else{
            Node temp = findParent(value);
            if(temp==null){
                return;
            }else if(temp.left.value==value){
                Node tmp = temp.left;
                temp.left = null;
                addAll(tmp.left);
                addAll(tmp.right);
            }else {
                Node tmp = temp.right;
                temp.right = null;
                addAll(tmp.left);
                addAll(tmp.right);
            }
        }
    }
    private Node findParent(int value){
        return findParent(value,root);
    }
    private Node findParent(int value, Node r){
        if(r==null){
            return r;
        }else if(r.value == value){
            return r;
        }else if(r.value> value){
            if(r.left==null){
                return null;
            }else if(r.left.value == value){
                return r;
            }else return findParent(value,r.left);
        }else {
            if(r.right==null){
                return null;
            }else if(r.right.value == value){
                return r;
            }else return findParent(value,r.right);
        }
    }

    private boolean isLeaf(Node r){
        return r!=null&&r.left==null&&r.right==null?true:false;
    }

    private Node balance(Node r) {
        if (r == null) return r;
        else {
            r.left = balance(r.left);
            r.right = balance(r.right);

            if (isNeedToBalance(r)) {
                int maxDepthRR;
                int maxDepthRL;
                int maxDepthLR;
                int maxDepthLL;
                if (r.right != null) {
                    maxDepthRL = maxDepth(r.right.left);
                    maxDepthRR = maxDepth(r.right.right);
                    if (maxDepthRL <= maxDepthRR) {
                        r = minLeftRotation(r);
                    } else if (maxDepthRL > maxDepthRR) {
                        r = bigLeftRotation(r);
                    }
                } else if (r.left != null) {
                    maxDepthLR = maxDepth(r.left.right);
                    maxDepthLL = maxDepth(r.left.left);
                    if (maxDepthLR <= maxDepthLL) {
                        r = minRightRotation(r);
                    } else if (maxDepthLR > maxDepthLL) {
                        r = bigRightRotation(r);
                    }
                }


            }

            return r;
        }
    }

    private Node minLeftRotation(Node r) {
        if (r != null) {
            Node temp = r.right;
            r.right = temp.left;
            temp.left = r;
            return temp;
        }
        return r;
    }

    private Node minRightRotation(Node r) {
        if (r != null) {
            Node temp = r.left;
            r.left = temp.right;
            temp.right = r;
            return temp;
        }
        return r;
    }

    private Node bigLeftRotation(Node r) {
        r.right = minRightRotation(r.right);
        r = minLeftRotation(r);
        return r;
    }

    private Node bigRightRotation(Node r) {
        r.left = minLeftRotation(r.left);
        r = minRightRotation(r);
        return r;
    }

    private boolean isNeedToBalance(Node root) {
        if (root != null) {
            if (Math.abs(maxDepth(root.left) - maxDepth(root.right)) > 1) return true;
            else return false;
        } else return false;
    }

    private int maxDepth(Node r) {
        if (r == null) {
            return 0;
        } else {
            return Math.max(maxDepth(r.left), maxDepth(r.right)) + 1;
        }
    }


    public boolean find(int value) {
        return find(value, root);
    }

    private boolean find(int value, Node r) {
        if (r != null) {
            if (r.value == value) return true;
            else if (r.value > value) return find(value, r.left);
            else return find(value, r.right);
        } else return false;
    }

    public String toString() {
        if (root != null) {
            return root.toString();
        }
        return "";
    }


}
