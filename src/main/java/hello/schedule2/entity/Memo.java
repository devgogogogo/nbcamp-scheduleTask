package hello.schedule2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "memo")
@NoArgsConstructor
@AllArgsConstructor
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
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
