package pojo;

import lombok.Data;
import lombok.Getter;

@Data
public class Sportsman {
    public int id;
    public String employee_name;
    public int employee_salary;
    public int employee_age;
    public String profile_image;

    public Sportsman() {
    }

    public Sportsman(int id, String employee_name, int employee_salary, int employee_age, String profile_image) {
        this.id = id;
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
        this.employee_age = employee_age;
        this.profile_image = profile_image;
    }

}
