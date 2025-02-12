package de.codelix.entitymanagementsystem.dto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class CreateTeamDto {
    private final String name;
    private final float hue;
    private final int entityId;
}
