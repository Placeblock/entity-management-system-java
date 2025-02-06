package de.codelix.entitymanagementsystem;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

/**
 * The identity of a single person. Multiple accounts of different
 * Applications may be linked to one entity and requested if necessary.
 */
@Getter
@RequiredArgsConstructor
public class Entity {
    private final UUID uuid;
    private final String name;
}
