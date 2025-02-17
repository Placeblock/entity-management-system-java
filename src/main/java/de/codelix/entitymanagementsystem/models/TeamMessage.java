package de.codelix.entitymanagementsystem.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamMessage {
    private int id;
    private int memberId;
    private int teamId;
    private Member member;
    private Team team;
    private String message;

    @Override
    public String toString() {
        return "TeamMessage{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", teamId=" + teamId +
                ", member=" + member +
                ", team=" + team +
                ", message='" + message + '\'' +
                '}';
    }
}
