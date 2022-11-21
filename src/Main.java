import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Node f = Parser.parse("(9 (12 (14 (19) (23)))(12(17(67)(54))(72(76)(50))))");
        System.out.println(" f Tree:\n"+f);
        Node r = Parser.parse("(7(4(2)(3(1)))(6(5)(0(10)(11))))");
        System.out.println(" r Tree:\n"+r);
        AVLTree Ftree = new AVLTree(f);
        System.out.println(" AVL f Tree:\n"+Ftree);
        AVLTree Rtree = new AVLTree(r);
        System.out.println(" AVL r Tree:\n"+Rtree);
        Ftree.addAll(r);
        System.out.println("Avl Tree f + r\n" + Ftree);
        Rtree.addAll(f);
        System.out.println("Avl Tree r + f\n" + Rtree);
        Rtree.remove(7);
        System.out.println("Avl Tree r + f\n" + Rtree);
        System.out.println("");
    }
}
//9 12 14 19 23 17 12 67 54 72 76 50
//(9 (12 (14 (19) (23)))(12(17(67)(54))(72(76)(50))))
// (7(4(2)(3(1)))(6(5)(0(10)(11))))


