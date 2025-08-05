package dev.tnaumov.proxmox.service;

import dev.tnaumov.proxmox.client.PveClient;
import dev.tnaumov.proxmox.model.PvePool;
import dev.tnaumov.proxmox.model.PveResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;
import java.util.Map;

/**
 * Service to manage PVE pools.
 */
public class PvePoolService {
    private final PveClient pveClient;

    public PvePoolService(PveClient pveClient) {
        this.pveClient = pveClient;
    }

    /**
     * List pools or get pool configuration from the PVE server.
     *
     * @return a {@link PvePool} object containing the pool details
     */
    public PveResponse<List<PvePool>> getPools() {
        return pveClient.get("/pools", new ParameterizedTypeReference<>() {});
    }

    /**
     * Creates a new pool.
     *
     * @param poolId  the display name of the pool
     * @param comment the text comment for pool
     * @return a {@link PvePool} object containing call execution results
     */
    public PveResponse<Void> createPool(String poolId, String comment) {
        return pveClient.post("/pools",
                new LinkedMultiValueMap<>(Map.of("poolid", List.of(poolId), "comment", List.of(comment))),
                new ParameterizedTypeReference<>() {}
        );
    }

    /**
     * Creates a new pool with the specified display name.
     *
     * @param poolId the display name of the pool
     * @return a {@link PvePool} object containing call execution results
     */
    public PveResponse<Void> createPool(String poolId) {
        return pveClient.post("/pools",
                new LinkedMultiValueMap<>(Map.of("poolid", List.of(poolId))),
                new ParameterizedTypeReference<>() {}
        );
    }

    /**
     * Updates the configuration of an existing pool.
     *
     * @param poolId     the ID of the pool to update (required)
     * @param allowMove  if true, allows adding guests even if they exist in another pool.
     *                   The guests will be removed from their current pools and added to this one
     * @param comment    descriptive text about the pool
     * @param delete     if true, removes the specified VMIDs and/or storage IDs instead of adding them
     * @param storageIds list of storage IDs to add to or remove from the pool
     * @param vmsIds     list of guest VMIDs to add to or remove from the pool
     * @return a {@link PveResponse<Void>} indicating the success or failure of the operation
     */

    public PveResponse<Void> updatePool(String poolId, Boolean allowMove, String comment, Boolean delete, List<String> storageIds, List<String> vmsIds) {
        LinkedMultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();

        if (allowMove != null) {
            queryParams.add("allow-move", String.valueOf(allowMove ? 1 : 0));
        }
        if (comment != null) {
            queryParams.add("comment", comment);
        }
        if (delete != null) {
            queryParams.add("delete", String.valueOf(delete ? 1 : 0));
        }
        if (vmsIds != null) {
            queryParams.add("storage", String.join(",", storageIds));
        }
        if (vmsIds != null) {
            queryParams.add("vms", String.join(",", vmsIds));
        }
        queryParams.add("poolid", poolId);

        return pveClient.put("/pools", queryParams, new ParameterizedTypeReference<>() {});
    }


    /**
     * Deletes an existing pool.
     *
     * @param poolId the display name of the pool
     * @return a {@link PvePool} object containing call execution results
     */
    public PveResponse<Void> deletePool(String poolId) {
        return pveClient.delete("/pools",
                new LinkedMultiValueMap<>(Map.of("poolid", List.of(poolId))),
                new ParameterizedTypeReference<>() {}
        );
    }
}
