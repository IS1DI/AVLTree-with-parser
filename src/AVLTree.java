public class AVLTree {

    private Node root;

    AVLTree(Node root) {
        if (root == null) {
            throw new NullPointerException();
        } else {
            addAll(root);
        }
    }

    public void addAll(Node root) {
        if (root != null) {
            add(root.value);
            if (root.left != null) addAll(root.left);
            if (root.right != null) addAll(root.right);
            this.root = balance(this.root);
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
    public void remove(int value){
        if(find(value)){

        }
    }

    private Node balance(Node r) {
        if (r == null) return r;
        else {
            r.left = balance(r.left);
            r.right = balance(r.right);
        }
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
            } else if (r.left != null ) {
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
