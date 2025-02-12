package de.codelix.entitymanagementsystem.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private int id;
    private int teamId;
    private int entityId;

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", teamId=" + teamId +
                ", entityId=" + entityId +
                '}';
    }
}
