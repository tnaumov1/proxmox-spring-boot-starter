package dev.tnaumov.proxmox.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "proxmox.pve")
public class PveProperties {

    /**
     * The base URL for the Proxmox VE.
     */
    private String url;

    /**
     * The API credentials for the Proxmox VE.
     */
    private PveToken token;

    /**
     * Gets the Proxmox VE url.
     *
     * @return the Proxmox VE base url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the Proxmox VE base url.
     *
     * @param url the Proxmox VE base url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public PveToken getToken() {
        return token;
    }

    public void setToken(PveToken token) {
        this.token = token;
    }

    public static class PveToken {

        /**
         * The API token id for the Proxmox VE.
         */
        private String id;

        /**
         * The API token secret for the Proxmox VE.
         */
        private String secret;

        /**
         * Gets the API token id.
         *
         * @return the API token id
         */
        public String getId() {
            return id;
        }

        /**
         * Sets the API token id.
         *
         * @param id the API token id
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * Gets the API token secret.
         *
         * @return the API token secret
         */
        public String getSecret() {
            return secret;
        }

        /**
         * Sets the API token secret.
         *
         * @param secret the API token secret
         */
        public void setSecret(String secret) {
            this.secret = secret;
        }
    }

}
