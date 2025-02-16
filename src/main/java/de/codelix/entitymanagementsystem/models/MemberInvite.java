package de.codelix.entitymanagementsystem.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MemberInvite {
    private Integer id;
    private Integer inviterId;
    private Integer invitedId;
    private Member inviter;
    private Entity invited;

    @Override
    public String toString() {
        return "MemberInvite{" +
                "id=" + id +
                ", invitedId=" + invitedId +
                ", inviterId=" + inviterId +
                ", invited=" + invited +
                ", inviter=" + inviter +
                '}';
    }
}
