package dev.tnaumov.proxmox.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public record PveVersion(
        @JsonProperty("repoid")
        String version,
        ConsoleType console,
        String release,
        String repoId
) {
    public enum ConsoleType {
        APPLET("applet"),
        VV("vv"),
        HTML5("html5"),
        XTERMJS("xtermjs");

        private final String value;

        ConsoleType(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @JsonCreator
        public static ConsoleType fromValue(String value) {
            for (ConsoleType type : ConsoleType.values()) {
                if (type.value.equals(value)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Unknown console type: " + value);
        }
    }
}
