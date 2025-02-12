package de.codelix.entitymanagementsystem.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    private int entityID;
    private String pin;

    @Override
    public String toString() {
        return "Token{" +
                "entityID=" + entityID +
                ", pin='" + pin + '\'' +
                '}';
    }
}
