public class UseProfile {
    public static Profile create(String name, int age) {
        Profile p = new Profile();
        p.setName(name);
        p.setAge(age);
        return p;
    }
}
