package de.codelix.entitymanagementsystem.models;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberInvite {
    private int id;
    private int invitedId;
    private int inviterId;

    @Override
    public String toString() {
        return "MemberInvite{" +
                "id=" + id +
                ", invitedId=" + invitedId +
                ", inviterId=" + inviterId +
                '}';
    }
}
