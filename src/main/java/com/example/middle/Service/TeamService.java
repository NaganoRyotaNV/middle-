package com.example.middle.Service;

import com.example.middle.Domain.TeamDomain;
import com.example.middle.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public List<TeamDomain> showTeam() {
        return teamRepository.AllDate();
    }
}
