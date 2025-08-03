package dev.tnaumov.proxmox.client;

import dev.tnaumov.proxmox.config.PveProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * A client for interacting with the Proxmox Virtual Environment (PVE) API.
 */
public class PveClient {
    private final PveProperties pveProperties;
    private final RestClient restClient;

    /**
     * Constructs a PveClient for interacting with the Proxmox Virtual Environment (PVE) API.
     *
     * @param pveProperties the configuration properties for Proxmox VE, including the base URL and API token
     * @param restClient    the REST client used to execute HTTP requests to the Proxmox VE API
     */
    public PveClient(PveProperties pveProperties, RestClient restClient) {
        this.pveProperties = pveProperties;
        this.restClient = restClient.mutate()
                .baseUrl(pveProperties.getUrl() + "/api2/json")
                .defaultHeader(AUTHORIZATION, pveAuthorizationToken(pveProperties.getToken()))
                .defaultHeader(ACCEPT, APPLICATION_JSON_VALUE)
                .build();
    }

    /**
     * Sends a GET request to the specified path of the Proxmox VE API.
     *
     * @param path the URI path to send the GET request to
     * @param <T> the type of the response body
     * @param responseType the class type of the expected response body
     * @return the response body parsed into the specified type
     */
    public <T> T get(String path, Class<T> responseType) {
        return restClient.get()
                .uri(path)
                .retrieve()
                .toEntity(responseType)
                .getBody();
    }

    /**
     * Sends a GET request to the specified path of the Proxmox VE API.
     *
     * @param path the URI path to send the GET request to
     * @param <T> the type of the response body
     * @param responseType the parameterized type of the expected response body
     * @return the response body parsed into the specified type
     */
    public <T> T get(String path, ParameterizedTypeReference<T> responseType) {
        return restClient.get()
                .uri(path)
                .retrieve()
                .toEntity(responseType)
                .getBody();
    }

    /**
     * Sends a POST request to the specified path of the Proxmox VE API.
     *
     * @param path the URI path to send the POST request to
     * @param bodyObject the object that represents the request body
     * @param <T> the type of the response body
     * @param responseType the class type of the expected response body
     * @return the response body parsed into the specified type
     */
    public <T> T post(String path, Object bodyObject, Class<T> responseType) {
        return restClient.post()
                .uri(path)
                .body(bodyObject)
                .retrieve()
                .toEntity(responseType)
                .getBody();
    }
    /**
     * Sends a POST request to the specified path of the Proxmox VE API.
     *
     * @param path the URI path to send the POST request to
     * @param bodyObject the object that represents the request body
     * @param <T> the type of the response body
     * @param responseType the parameterized type of the expected response body
     * @return the response body parsed into the specified type
     */
    public <T> T post(String path, Object bodyObject, ParameterizedTypeReference<T> responseType) {
        return restClient.post()
                .uri(path)
                .body(bodyObject)
                .retrieve()
                .toEntity(responseType)
                .getBody();
    }

    /**
     * Sends a PATCH request to the specified path of the Proxmox VE API.
     *
     * @param path the URI path to send the PATCH request to
     * @param bodyObject the object that represents the request body
     * @param <T> the type of the response body
     * @param responseType the class type of the expected response body
     * @return the response body parsed into the specified type
     */
    public <T> T patch(String path, Object bodyObject, Class<T> responseType) {
        return restClient.patch()
                .uri(path)
                .body(bodyObject)
                .retrieve()
                .toEntity(responseType)
                .getBody();
    }

    /**
     * Sends a PATCH request to the specified path of the Proxmox VE API.
     *
     * @param path the URI path to send the PATCH request to
     * @param bodyObject the object that represents the request body
     * @param <T> the type of the response body
     * @param responseType the parameterized type of the expected response body
     * @return the response body parsed into the specified type
     */
    public <T> T patch(String path, Object bodyObject, ParameterizedTypeReference<T> responseType) {
        return restClient.patch()
                .uri(path)
                .body(bodyObject)
                .retrieve()
                .toEntity(responseType)
                .getBody();
    }

    /**
     * Sends a PUT request to the specified path of the Proxmox VE API.
     *
     * @param path the URI path to send the PUT request to
     * @param bodyObject the object that represents the request body
     * @param <T> the type of the response body
     * @param responseType the class type of the expected response body
     * @return the response body parsed into the specified type
     */
    public <T> T put(String path, Object bodyObject, Class<T> responseType) {
        return restClient.put()
                .uri(path)
                .body(bodyObject)
                .retrieve()
                .toEntity(responseType)
                .getBody();
    }

    /**
     * Sends a PUT request to the specified path of the Proxmox VE API.
     *
     * @param path the URI path to send the PUT request to
     * @param bodyObject the object that represents the request body
     * @param <T> the type of the response body
     * @param responseType the parameterized type of the expected response body
     * @return the response body parsed into the specified type
     */
    public <T> T put(String path, Object bodyObject, ParameterizedTypeReference<T> responseType) {
        return restClient.put()
                .uri(path)
                .body(bodyObject)
                .retrieve()
                .toEntity(responseType)
                .getBody();
    }

    /**
     * Sends a DELETE request to the specified path of the Proxmox VE API.
     *
     * @param path the URI path to send the DELETE request to
     * @param <T> the type of the response body
     * @param responseType the class type of the expected response body
     * @return the response body parsed into the specified type
     */
    public <T> T delete(String path, Class<T> responseType) {
        return restClient.delete()
                .uri(path)
                .retrieve()
                .toEntity(responseType)
                .getBody();
    }

    /**
     * Sends a DELETE request to the specified path of the Proxmox VE API.
     *
     * @param path the URI path to send the DELETE request to
     * @param <T> the type of the response body
     * @param responseType the parameterized type of the expected response body
     * @return the response body parsed into the specified type
     */
    public <T> T delete(String path, ParameterizedTypeReference<T> responseType) {
        return restClient.delete()
                .uri(path)
                .retrieve()
                .toEntity(responseType)
                .getBody();
    }

    /**
     * Generates an API token authorization string for the Proxmox VE API.
     *
     * @param token the API token object containing the token ID and secret
     * @return a concatenated authorization token in the format "PVEAPIToken={token-id}={token-secret}"
     */
    public static String pveAuthorizationToken(PveProperties.PveToken token) {
        return "PVEAPIToken=" + token.getId() + "=" + token.getSecret();
    }

}
