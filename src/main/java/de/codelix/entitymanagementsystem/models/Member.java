package de.codelix.entitymanagementsystem.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Member {
    private final int id;
    private final int teamId;
    private final int entityId;
}
