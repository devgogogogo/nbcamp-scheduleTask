package hello.schedule2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor //@Entity 를 선언하면 빈생성자는 필수
public class Member extends BaseEntity {

    @Id //entity를 선언하면 id는 필수
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pk값을 자동으로 생성할때 사용하는 어노테이션
    private Long id;


    @Column(nullable = false, unique = true) //not null, 중복없고
    @NotBlank
    private String userName;

    @Column(nullable = false) // not null
    @NotBlank
    private String password;

    private String email; // @Column 안해도 칼럼이 포함됨 (추가 기능?이 없을뿐)


    public Member(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
}
