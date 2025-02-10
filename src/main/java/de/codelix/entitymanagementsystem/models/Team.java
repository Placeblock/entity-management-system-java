package de.codelix.entitymanagementsystem.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Team {
    private final int id;
    private final String name;
    private final float hue;
}
