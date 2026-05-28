public class AgeValidator {
    public static int validate(int age) throws InvalidAgeException {
        if (age < 0 || age > 150) {
            throw new InvalidAgeException("Age must be between 0 and 150");
        }
        return age;
    }
}
