package dev.tnaumov.proxmox.model;

import java.util.List;

public record PveResponse<D>(
        List<String> errors,
        D data
) {
}
