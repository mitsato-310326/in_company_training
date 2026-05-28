public class Tester {
    static final java.io.PrintStream OUT = System.out;
    static int p = 0, t = 0;

    static void chk(String n, boolean r) {
        t++; if (r) { p++; OUT.println("OK " + n); }
        else OUT.println("KO " + n);
    }

    public static void main(String[] args) {
        // {0,1,2} -> Donkey, Dog, Cat
        Walk[] arr = Animals.make_animals_array(new int[]{0, 1, 2});
        chk("length == 3",            arr.length == 3);
        chk("arr[0] is Donkey",       arr[0] instanceof Donkey);
        chk("arr[1] is Dog",          arr[1] instanceof Dog);
        chk("arr[2] is Cat",          arr[2] instanceof Cat);
        chk("all instanceof Walk",
            arr[0] instanceof Walk && arr[1] instanceof Walk && arr[2] instanceof Walk);

        // {2,0,1} -> Cat, Donkey, Dog
        Walk[] arr2 = Animals.make_animals_array(new int[]{2, 0, 1});
        chk("arr2 length == 3",  arr2.length == 3);
        chk("arr2[0] is Cat",    arr2[0] instanceof Cat);
        chk("arr2[1] is Donkey", arr2[1] instanceof Donkey);
        chk("arr2[2] is Dog",    arr2[2] instanceof Dog);

        OUT.println(p + "/" + t + " passed");
        System.exit(p == t ? 0 : 1);
    }
}
