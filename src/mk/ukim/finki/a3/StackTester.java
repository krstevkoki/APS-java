package mk.ukim.finki.a3;

public class StackTester {
    public static void main(String[] args) {
        ArrayStack<Integer> arrayStack = new ArrayStack<>(5);
        if (arrayStack.isEmpty())
            System.out.println("Stack is empty");
        arrayStack.push(2);
        arrayStack.push(1);
        arrayStack.push(5);
        arrayStack.push(4);
        arrayStack.push(9);
        if (!(arrayStack.isEmpty()))
            System.out.println("Stack is not empty");
        System.out.println(arrayStack.peek());
        System.out.println(arrayStack.peek());
        System.out.println(arrayStack.peek());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        arrayStack.push(3);
        arrayStack.push(0);
        arrayStack.clear();
        arrayStack.push(2);
        arrayStack.push(1);
        arrayStack.push(5);
        arrayStack.push(4);
        arrayStack.push(9);

        LinkedStack<String> linkedStack = new LinkedStack<>();
        if (linkedStack.isEmpty())
            System.out.println("Stack is empty");
        linkedStack.push("a");
        linkedStack.push("b");
        linkedStack.push("c");
        linkedStack.push("d");
        linkedStack.push("e");
        linkedStack.push("f");
        linkedStack.push("g");
        linkedStack.push("h");
        linkedStack.push("i");
        linkedStack.push("j");
        linkedStack.push("k");
        if (!(linkedStack.isEmpty()))
            System.out.println("Stack is not empty");
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.peek());
        linkedStack.clear();
        if (linkedStack.isEmpty())
            System.out.println("Stack is empty");
    }
}
