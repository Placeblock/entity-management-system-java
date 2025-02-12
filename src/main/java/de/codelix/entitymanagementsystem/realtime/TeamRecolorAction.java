package de.codelix.entitymanagementsystem.realtime;

import com.fasterxml.jackson.annotation.JsonTypeName;
import de.codelix.entitymanagementsystem.models.Team;
import lombok.Getter;

@Getter
@JsonTypeName("team.recolor")
public class TeamRecolorAction extends Action {
    private Team data;
}
