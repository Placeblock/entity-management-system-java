package de.codelix.entitymanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateTeamDto {
    private String name;
    private float hue;
    private int entityId;
}
