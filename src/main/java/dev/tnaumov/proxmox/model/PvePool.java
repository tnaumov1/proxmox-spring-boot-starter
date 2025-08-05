package dev.tnaumov.proxmox.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.annotation.Nullable;

import java.util.List;

public record PvePool(
        @JsonProperty("poolid")
        String poolId,
        @JsonProperty("comment") @Nullable
        String comment,
        @JsonProperty("members") @Nullable
        List<Member> members
) {
    public record Member(
            @JsonProperty("id")
            String id,
            @JsonProperty("node")
            String node,
            @JsonProperty("type")
            Type type,
            @JsonProperty("storage") @Nullable
            String storage,
            @JsonProperty("vmid") @Nullable
            Integer vmid
    ) {
        public enum Type {
            QEMU("qemu"),
            LXC("lxc"),
            OPENVZ("openvz"),
            STORAGE("storage");

            private final String value;

            Type(String value) {
                this.value = value;
            }

            @JsonValue
            public String getValue() {
                return value;
            }

            @JsonCreator
            public static Type fromValue(String value) {
                for (Type type : Type.values()) {
                    if (type.value.equals(value)) {
                        return type;
                    }
                }
                throw new IllegalArgumentException("Unknown type: " + value);
            }
        }
    }
}