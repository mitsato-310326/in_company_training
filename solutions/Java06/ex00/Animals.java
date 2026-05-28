public class Animals {
    public static Walk[] make_animals_array(int[] ids) {
        Walk[] result = new Walk[ids.length];
        for (int i = 0; i < ids.length; i++) {
            if      (ids[i] == 0) result[i] = new Donkey();
            else if (ids[i] == 1) result[i] = new Dog();
            else                  result[i] = new Cat();
        }
        return result;
    }
}
