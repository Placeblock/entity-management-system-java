package de.codelix.entitymanagementsystem.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberInvite {
    private final int id;
    private final int invitedId;
    private final int inviterId;
    private final int teamId;
}
