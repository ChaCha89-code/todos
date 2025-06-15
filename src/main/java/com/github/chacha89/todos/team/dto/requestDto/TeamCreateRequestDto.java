package com.github.chacha89.todos.team.dto.requestDto;

public class TeamCreateRequestDto {
    // 속성
    private String teamName;
    private String description;

    // 생성자

    public TeamCreateRequestDto(String teamName, String description) {
        this.teamName = teamName;
        this.description = description;
    }

    // 기능

    public String getTeamName() {
        return teamName;
    }

    public String getDescription() {
        return description;
    }
}
