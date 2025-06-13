package dev.tnaumov.proxmox.autoconfigure;

import dev.tnaumov.proxmox.client.PveClient;
import dev.tnaumov.proxmox.config.PveProperties;
import dev.tnaumov.proxmox.service.PveVersionService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

/**
 * {@link EnableAutoConfiguration Auto-configuration} for Proxmox VE API integration.
 */
@Configuration
@EnableConfigurationProperties(PveProperties.class)
@ConditionalOnProperty(prefix = "proxmox.pve", name = "url")
public class PveAutoConfiguration {

    /**
     * Creates a RestClient for Proxmox VE API calls.
     *
     * @return the {@link RestClient} bean
     */
    @Bean
    @ConditionalOnMissingBean
    RestClient pveRestClient() {
        return RestClient.create();
    }

    /**
     * Creates a PveClient client.
     *
     * @param pveProperties configuration properties for PVE
     * @param pveRestClient the REST client used for API calls
     * @return the {@link PveClient} bean
     */
    @Bean
    @ConditionalOnMissingBean
    PveClient pveClient(PveProperties pveProperties, RestClient pveRestClient) {
        return new PveClient(pveProperties, pveRestClient);
    }

    /**
     * Creates a PveVersionService service.
     *
     * @param pveClient the REST client used for API calls
     * @return the {@link PveVersionService} bean
     */
    @Bean
    @ConditionalOnMissingBean
    PveVersionService pveVersionService(PveClient pveClient) {
        return new PveVersionService(pveClient);
    }

}
