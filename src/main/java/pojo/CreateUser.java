package pojo;

public class CreateUser {
    public String name;
    private String salary;
    private String age;

    public CreateUser() {
    }

    public CreateUser(String name, String salary, String age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }
}
