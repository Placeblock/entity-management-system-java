package de.codelix.entitymanagementsystem.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberInvite {
    private int id;
    private int invitedId;
    private int inviterId;
    private int teamId;

    @Override
    public String toString() {
        return "MemberInvite{" +
                "id=" + id +
                ", invitedId=" + invitedId +
                ", inviterId=" + inviterId +
                ", teamId=" + teamId +
                '}';
    }
}
