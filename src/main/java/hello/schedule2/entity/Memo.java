package hello.schedule2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity // entity 선언하면 @id는 필수
@Table(name = "memo")
@NoArgsConstructor //@Entity를 선언하면 빈생성자는 필수
@AllArgsConstructor
public class Memo {

    @Id //a
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // not null
    private String username;

    @Column(nullable = false)  //not null
    private String title;

    @Column(columnDefinition = "longtext") // 긴 텍스트를 사용핧때 columnDefinition을 사용한다.
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Memo(String username, String title, String content) {
        this.username = username;
        this.title = title;
        this.content = content;
    }
}
