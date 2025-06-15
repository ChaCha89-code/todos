package com.github.chacha89.todos.team.entity;

import com.github.chacha89.todos.user.entity.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "team_name", nullable = false, length = 50, unique = true)
    private String teamName;

    @Column(name = "description", nullable = false)
    private String description;

//    @Column(name = "member_count", nullable = false)
//    private Long memberCount;

//    @OneToMany(mappedBy = "team")
//    private List<User> users;

    // 생성자
    public Team() {}

    public Team(String teamName, String description) {
        this.teamName = teamName;
        this.description = description;
    }

    // 기능
    public Long getId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getDescription() {
        return description;
    }

//    public Long getMemberCount() {
//        return memberCount;
//    }

// memberCount 제거한 이유 (나중에 추가로 멤버 카운트를 위한 업데이트 로직이 완성되면 추가)
    // user 테이블에서 계산할 수 있는 값으로 데이터가 동기화되지 않으면 부정확할 수 있기 때문.
    // 저장하지 말고 매번 계산할 수 있음: SELECT COUNT(*) FROM users WHERE team_id = :teamId;

}
