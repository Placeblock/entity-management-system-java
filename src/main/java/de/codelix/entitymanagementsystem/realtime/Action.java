package de.codelix.entitymanagementsystem.realtime;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = EntityRenameAction.class, name = "entity.rename"),
        @JsonSubTypes.Type(value = MemberCreateAction.class, name = "member.create"),
        @JsonSubTypes.Type(value = MemberRemoveAction.class, name = "member.remove"),
        @JsonSubTypes.Type(value = MemberInviteAction.class, name = "member.invite"),
        @JsonSubTypes.Type(value = InviteAcceptAction.class, name = "member.invite.accept"),
        @JsonSubTypes.Type(value = InviteDeclineAction.class, name = "member.invite.decline"),
        @JsonSubTypes.Type(value = TeamCreateAction.class, name = "team.create"),
        @JsonSubTypes.Type(value = TeamRenameAction.class, name = "team.rename"),
        @JsonSubTypes.Type(value = TeamRecolorAction.class, name = "team.recolor")
})
public abstract class Action {
    private String type;

}
