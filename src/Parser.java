

public class Parser {


    public static Node parse(String exp) {
        int count = 0;
        ListNew<String> queue = new LinkedListNew();

        String expression = exp.replaceAll("\\s+", "");
        char[] expr = expression.toCharArray();
        for (int i = 0; i < expr.length; i++) {
            if (expr[i] == '(') {
                queue.add("(");
                count++;
            } else if (expr[i] == ')') {
                queue.add(")");
                count--;
            } else if (Character.isDigit(expr[i])) {
                int seq = i;
                for (; seq < expr.length && Character.isDigit(expr[seq]); seq++) {
                }
                queue.add(expression.substring(i, seq));
                i = seq - 1;
            } else {
                System.out.printf("%s^\n", " ".repeat(exp.indexOf(expr[i])));
                System.out.printf("Некорректный ввод после символа %d\n", exp.indexOf(expr[i]) == 0 ? 0 : exp.indexOf(expr[i]) - 1);
                return null;
            }
        }
        if (count > 0) {
            System.out.println("Введено неверное количество закрывающихся скобок");
            return null;
        } else if (count < 0) {
            System.out.println("Введено неверное количество открывающихся скобок");
            return null;
        } else {
            return parse(queue);
        }
    }

    private static Node parse(ListNew<String> queue) {
        Node root = null;
        if (queue.getFirst() == "(") {
            queue.removeFirst();
            if (queue.getFirst() != ")") {
                root = new Node(Integer.parseInt(queue.getFirst()));
                queue.removeFirst();
                if (queue.getLast() == ")") {
                    queue.remove(queue.getSize() - 1);
                } else {
                    System.out.println("Неверный ввод");
                    return null;
                }
                int count = 0;
                boolean isLeft = true;
                ListNew q = new LinkedListNew<String>();
                for (int i = 0; i < queue.getSize(); i++) {
                    q.add(queue.get(i));

                    if (queue.get(i) == "(") count++;
                    else if (queue.get(i) == ")") count--;
                    if (count == 0 && isLeft) {
                        root.left = parse(q);
                        isLeft = false;
                        q.clear();
                    } else if (count == 0) {
                        root.right = parse(q);
                    }
                }
            }
        }
        return root;
    }

}
