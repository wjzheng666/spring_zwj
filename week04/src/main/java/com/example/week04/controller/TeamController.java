package com.example.week04.controller;

import com.example.week04.common.Result;
import com.example.week04.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/team")
public class TeamController {

    @Autowired
    private Team team;

    @GetMapping("/info")
    public Result<Team> getTeamInfo() {
        return Result.success(team);
    }
}