package de.codelix.entitymanagementsystem.realtime;

import com.fasterxml.jackson.annotation.JsonTypeName;
import de.codelix.entitymanagementsystem.models.Member;
import lombok.Getter;

@Getter
@JsonTypeName("member.create")
public class MemberCreateAction extends Action {
    private Member data;
}
