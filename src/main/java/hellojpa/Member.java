package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Member {
    @Id
    private Long id;
    @Column(name = "name") //컬럼 매핑
    private String username;
    private Integer age;
    @Enumerated(EnumType.STRING) //enum 타입 매핑
    private RoleType roleType;
    @Temporal(TemporalType.TIMESTAMP) //날짜 타입 매핑
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    private LocalDate testLocalDate; //년월
    private LocalDateTime testLocalDateTime; //년월일

    @Lob //큰 값을 쓰고 싶을 때 -> BLOB, CLOB 매핑
    private String description;

    @Transient //DB와 연결하지 말라는 어노테이션(메모리에서만 사용하고 싶을 때)
    private int temp;

    public Member(){

    }
}
