package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Member extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;
//    @ManyToOne(fetch = FetchType.LAZY) //지연 로딩을 세팅하면 연관된 걸 프록시로 가져옴
    @ManyToOne(fetch = FetchType.EAGER) //즉시 로딩을 세팅하면 연관된 걸 실제 객체로 가져옴
    @JoinColumn(name = "TEAM_ID",insertable = false, updatable = false) //읽기 전용이 된다.
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", team=" + team +
                '}';
    }
}
