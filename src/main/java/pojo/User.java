package pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String email;
    private String name ;
    private String gender;
    private String status;
}
