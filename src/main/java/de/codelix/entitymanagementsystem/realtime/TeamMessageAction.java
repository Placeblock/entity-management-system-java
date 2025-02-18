package de.codelix.entitymanagementsystem.realtime;

import com.fasterxml.jackson.annotation.JsonTypeName;
import de.codelix.entitymanagementsystem.models.TeamMessage;
import lombok.Getter;

@Getter
@JsonTypeName("team.message")
public class TeamMessageAction extends Action {
    private TeamMessage data;
}
