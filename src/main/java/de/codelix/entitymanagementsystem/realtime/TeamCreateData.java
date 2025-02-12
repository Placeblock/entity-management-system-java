package de.codelix.entitymanagementsystem.realtime;

import de.codelix.entitymanagementsystem.models.Member;
import de.codelix.entitymanagementsystem.models.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeamCreateData {
    private Team team;
    private Member member;
}
