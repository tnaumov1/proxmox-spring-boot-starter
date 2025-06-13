package dev.tnaumov.proxmox.service;

import dev.tnaumov.proxmox.client.PveClient;
import dev.tnaumov.proxmox.model.PveResponse;
import dev.tnaumov.proxmox.model.PveVersion;
import org.springframework.core.ParameterizedTypeReference;

/**
 * Service for retrieving PVE version.
 */
public class PveVersionService {
    private final PveClient pveClient;

    public PveVersionService(PveClient pveClient) {
        this.pveClient = pveClient;
    }

    /**
     * Retrieves the current version information from the PVE server.
     *
     * @return a {@link PveVersion} object containing the version details
     */
    public PveResponse<PveVersion> getVersion() {
        return pveClient.get("/version", new ParameterizedTypeReference<>() {});
    }

}
