package de.codelix.entitymanagementsystem.models;

import lombok.*;

/**
 * The identity of a single person. Multiple accounts of different
 * Applications may be linked to one entity and requested if necessary.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Entity {
    private Integer id;
    private String name;

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
