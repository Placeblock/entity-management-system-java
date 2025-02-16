package de.codelix.entitymanagementsystem.realtime;


import com.fasterxml.jackson.annotation.JsonTypeName;
import de.codelix.entitymanagementsystem.models.MemberInvite;
import lombok.Getter;

@Getter
@JsonTypeName("member.invite")
public class MemberInviteAction extends Action {
    private MemberInvite data;
}
