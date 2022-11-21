import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Node f = Parser.parse(scan.nextLine());

        AVLTree tree = new AVLTree(f);
        System.out.println(tree);

    }
}
//9 12 14 19 23 17 12 67 54 72 76 50
//(9 (12 (14 (19) (23)))(12(17(67)(54))(72(76)(50))))