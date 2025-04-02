package com.user.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO
{
    private String playerTag;
    private String playerName;
    private int townHallLevel;
    private int currentTrophies;
    private int bestTrophies;
    private int warStars;
    private int builderHallLevel;
    private int builderHallTrophies;
    private int bestBuilderHallTrophies;
}
