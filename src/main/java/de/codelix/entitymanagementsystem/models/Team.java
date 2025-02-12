package de.codelix.entitymanagementsystem.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    private Integer id;
    private String name;
    private Float hue;

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hue=" + hue +
                '}';
    }
}
