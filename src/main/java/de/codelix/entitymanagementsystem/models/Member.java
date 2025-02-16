package de.codelix.entitymanagementsystem.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Member {
    private Integer id;
    private Integer teamId;
    private Integer entityId;
    private Entity entity;

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", teamId=" + teamId +
                ", entityId=" + entityId +
                ", entity=" + entity +
                '}';
    }
}
