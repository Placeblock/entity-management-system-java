package de.codelix.entitymanagementsystem.realtime;

import com.fasterxml.jackson.annotation.JsonTypeName;
import de.codelix.entitymanagementsystem.dto.TeamCreateData;
import lombok.Getter;

@Getter
@JsonTypeName("team.create")
public class TeamCreateAction extends Action {
    private TeamCreateData data;
}
