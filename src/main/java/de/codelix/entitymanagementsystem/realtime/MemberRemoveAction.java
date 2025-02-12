package de.codelix.entitymanagementsystem.realtime;

import com.fasterxml.jackson.annotation.JsonTypeName;
import de.codelix.entitymanagementsystem.models.Member;
import lombok.Getter;

@Getter
@JsonTypeName("member.remove")
public class MemberRemoveAction extends Action {
    private Member data;
}
