public class Resume {
    private String name;
    private int age;
    private String job;

    public Resume() {
        this.name = "Unknown";
        this.age = 0;
        this.job = "Unknown";
    }

    public Resume(String name, int age, String job) {
        this.name = name;
        this.age = age;
        this.job = job;
    }

    public void setName(String name) { this.name = name; }
    public String getName()          { return name; }

    public void setAge(int age)      { this.age = age; }
    public int getAge()              { return age; }

    public void setJob(String job)   { this.job = job; }
    public String getJob()           { return job; }
}
