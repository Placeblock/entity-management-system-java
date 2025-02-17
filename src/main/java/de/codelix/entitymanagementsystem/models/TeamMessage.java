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
    private Member member;
    private String message;

    @Override
    public String toString() {
        return "TeamMessage{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", member=" + member +
                ", message='" + message + '\'' +
                '}';
    }
}
