package de.codelix.entitymanagementsystem.realtime;

import com.fasterxml.jackson.annotation.JsonTypeName;
import de.codelix.entitymanagementsystem.models.Team;
import lombok.Getter;

@Getter
@JsonTypeName("team.rename")
public class TeamRenameAction extends Action {
    private Team data;
}
