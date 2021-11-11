package senla.list;

public class Experiments {

    public static void main(String[] args) {
        int num = 1954700000;
        System.out.println(Integer.toBinaryString(num));
        num = num >> 1;
        System.out.println(Integer.toBinaryString(num));
        System.out.println(num);
        System.out.println(num  + 1334700000);
    }
}
