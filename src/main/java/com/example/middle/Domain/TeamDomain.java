package com.example.middle.Domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter

public class TeamDomain {
        private Integer id;
        private String teamName;
        private String homePlace;
        private LocalDate foundingDate;
        private String history;
}
