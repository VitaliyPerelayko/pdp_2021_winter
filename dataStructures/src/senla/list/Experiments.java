package senla.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

public class Experiments {

    public static void main(String[] args) {
//        checkArrayList();

        checkLinkedList();
//        checkLinkedQueue();
//        checkArrayStack();
    }

    private static void checkArrayList() {
        MyList<String> fooList = new MyArrayList<>();
        for (int i = 0; i < 100; i ++){
            fooList.add("foo" + i);
        }
        MyList<String> barList = new MyArrayList<>();
        for (int i = 0; i < 20; i ++){
            barList.add("bar" + i);
        }
        fooList.addAll(barList);
        System.out.println(Arrays.toString(fooList.toArray()));
        barList.remove("bar13");
        barList.remove("bar16");
        System.out.println(barList.size());
        barList.forEach(System.out::println);
        System.out.println(Arrays.toString(barList.toArray()));
    }

    private static void checkLinkedList() {
        MyList<String> fooList = new MyLinkedList<>();

        for (int i = 0; i < 100; i ++){
            fooList.add("foo" + i);
        }
        MyList<String> barList = new MyLinkedList<>();
        for (int i = 0; i < 20; i ++){
            barList.add("bar" + i);
        }

        fooList.addAll(barList);
        System.out.println(Arrays.toString(fooList.toArray()));
        barList.remove("bar13");
        barList.remove("bar16");
        System.out.println(barList.size());
        barList.forEach(System.out::println);
        System.out.println(Arrays.toString(barList.toArray()));
        System.out.println(barList.get(13));
    }

    private static void checkLinkedQueue() {
        MyQueue<Fish> fishQueue = new MyLinkedQueue<>();
        for (int i = 0; i < 5; i ++){
            fishQueue.add(new Fish("type" + i, i));
        }
        fishQueue.forEach(System.out::println);
        System.out.println("=================");
        System.out.println(fishQueue.peek());
        System.out.println(fishQueue.poll());
        System.out.println(fishQueue.poll());
        System.out.println(fishQueue.poll());
        System.out.println("=================");
        fishQueue.forEach(System.out::println);
        fishQueue.add(new Fish("Guppy", 8));
        System.out.println("=================");
        fishQueue.forEach(System.out::println);
    }

    private static void checkArrayStack() {
        MyStack<Fish> fishStack = new MyArrayStack<>();
        for (int i = 0; i < 5; i ++){
            fishStack.push(new Fish("type" + i, i));
        }
        fishStack.forEach(System.out::println);
        System.out.println("=================");
        System.out.println(fishStack.peek());
        System.out.println(fishStack.pop());
        System.out.println(fishStack.pop());
        System.out.println(fishStack.pop());
        System.out.println("=================");
        fishStack.forEach(System.out::println);
        fishStack.push(new Fish("Guppy", 8));
        System.out.println("=================");
        fishStack.forEach(System.out::println);
    }

    static class Fish {
        String type;
        int cost;

        public Fish(String type, int cost) {
            this.type = type;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Fish{" +
                    "type=" + type +
                    ", cost=" + cost +
                    '}';
        }
    }
}
