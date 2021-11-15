package senla.list;

import java.util.Arrays;

public class Experiments {

    public static void main(String[] args) {
        checkArrayList();
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
}
