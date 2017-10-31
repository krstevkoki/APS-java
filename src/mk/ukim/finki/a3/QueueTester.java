package mk.ukim.finki.a3;

public class QueueTester {
    public static void main(String[] args) {
        ArrayQueue<Integer> intQueue = new ArrayQueue<>(5);
        intQueue.enqueue(1);
        intQueue.enqueue(2);
        intQueue.enqueue(3);
        intQueue.enqueue(4);
        System.out.println(intQueue.dequeue());
        System.out.println(intQueue.dequeue());
        System.out.println(intQueue.dequeue());
        System.out.println(intQueue.peek());
        System.out.println(intQueue.dequeue());
        intQueue.enqueue(5);
        System.out.println(intQueue.dequeue());
        intQueue.enqueue(6);
        System.out.println(intQueue.dequeue());
        System.out.println(intQueue.size());
        intQueue.clear();
        if (intQueue.isEmpty())
            System.out.println("Empty Queue");

        LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();
        linkedQueue.enqueue(1);
        linkedQueue.enqueue(2);
        linkedQueue.enqueue(3);
        linkedQueue.enqueue(4);
        System.out.println(linkedQueue.dequeue());
        System.out.println(linkedQueue.dequeue());
        System.out.println(linkedQueue.dequeue());
        System.out.println(linkedQueue.peek());
        System.out.println(linkedQueue.dequeue());
        linkedQueue.enqueue(5);
        System.out.println(linkedQueue.dequeue());
        linkedQueue.enqueue(6);
        System.out.println(linkedQueue.dequeue());
        System.out.println(linkedQueue.size());
        linkedQueue.clear();
        if (linkedQueue.isEmpty())
            System.out.println("Empty Queue");
    }
}
