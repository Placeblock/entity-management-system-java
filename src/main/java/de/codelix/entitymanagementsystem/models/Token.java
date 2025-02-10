package de.codelix.entitymanagementsystem.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Token {
    private final int entityID;
    private final String pin;
}
