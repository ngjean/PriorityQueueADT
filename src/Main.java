package src;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, and welcome to our Priority Queue ADT");

        // creating a priority queue with capacity 3:
        BinaryHeap queue = new BinaryHeap(3);

        System.out.println("The capacity of the queue is: " + queue.getCapacity());
        System.out.println("The size of the queue is: " + queue.getSize());

        // creating nodes to add to the queue:
        BinaryHeap.Node twelve = new BinaryHeap.Node<>("twelve", 12);
        BinaryHeap.Node eleven = new BinaryHeap.Node<>("eleven", 11);
        BinaryHeap.Node ten = new BinaryHeap.Node<>("ten", 10);
        BinaryHeap.Node nine = new BinaryHeap.Node<>("nine", 9);

        // adding to the queue:
        queue.insert(twelve,12);
        queue.insert(eleven,11);
        queue.insert(ten,10);
        queue.insert(nine,9);

        // checking the size capacity of the queue:
        System.out.println("The capacity of the queue is: " + queue.getCapacity()); // capacity is still 3, is this normal?
        System.out.println("The size of the queue is: " + queue.getSize()); // size is 4

    }
}