package com.github.chacha89.todos.team.service;

import com.github.chacha89.todos.exception.TeamCreateException;
import com.github.chacha89.todos.team.dto.requestDto.TeamCreateRequestDto;
import com.github.chacha89.todos.team.dto.responseDto.TeamCreateResponseDto;
import com.github.chacha89.todos.team.entity.Team;
import com.github.chacha89.todos.team.repository.TeamRepository;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    // 속성
    private final TeamRepository teamRepository;

    // 생성자
    private TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    // 기능
    /**
     * 팀 생성 기능
     */
    public TeamCreateResponseDto createTeamService(TeamCreateRequestDto requestDto) {
        // 1. 데이터 준비
        String teamName = requestDto.getTeamName();
        String description = requestDto.getDescription();

        // 2. 예외
        if(teamName.isBlank() || description.isBlank()) {
            throw new TeamCreateException(400, "팀 생성 입력 항목 중 빈 항목이 있습니다.");
        }

        // 3. 엔티티 생성
        Team newTeam = new Team(teamName, description);

        // 4. 저장
        teamRepository.save(newTeam);

        // 5. 응답
        return new TeamCreateResponseDto(200, "팀 생성이 성공적으로 완료되었습니다.");
    }

}
