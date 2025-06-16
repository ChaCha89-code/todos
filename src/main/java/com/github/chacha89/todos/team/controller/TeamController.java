package com.github.chacha89.todos.team.controller;

import com.github.chacha89.todos.team.dto.requestDto.TeamCreateRequestDto;
import com.github.chacha89.todos.team.dto.responseDto.TeamCreateResponseDto;
import com.github.chacha89.todos.team.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teams")
public class TeamController {
    // 속성
    private final TeamService teamService;

    // 생성자
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    // 기능

    /**
     * 팀 생성 API
     * @param requestDto
     */
    @PostMapping
    public ResponseEntity<TeamCreateResponseDto> createTeamAPI(@RequestBody TeamCreateRequestDto requestDto) {
        TeamCreateResponseDto responseDto = teamService.createTeamService(requestDto);
        return ResponseEntity.ok(responseDto);
    }


}
