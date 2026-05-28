public class Band {
    public static Bremen[] formBand(int[] ids) {
        Bremen[] result = new Bremen[ids.length];
        for (int i = 0; i < ids.length; i++) {
            if      (ids[i] == 0) result[i] = new Donkey();
            else if (ids[i] == 1) result[i] = new Dog();
            else if (ids[i] == 2) result[i] = new Cat();
            else                  result[i] = new Chicken();
        }
        return result;
    }
}
