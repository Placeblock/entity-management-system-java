package de.codelix.entitymanagementsystem.realtime;

import com.fasterxml.jackson.annotation.JsonTypeName;
import de.codelix.entitymanagementsystem.models.Entity;
import lombok.Getter;

@Getter
@JsonTypeName("entity.rename")
public class EntityRenameAction extends Action {
    private Entity data;
}
