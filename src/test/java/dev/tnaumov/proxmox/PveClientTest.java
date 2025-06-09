package dev.tnaumov.proxmox;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import dev.tnaumov.proxmox.client.PveClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Basic integration test for PveClient
 * <p>
 * Note: Proxmox VE API is mocked by WireMock and no actual API calls are happening.
 *
 */
@ActiveProfiles("test")
@SpringBootTest
public class PveClientTest {

    @Autowired
    private PveClient pveClient;

    @RegisterExtension
    private final static WireMockExtension wm = WireMockExtension.newInstance()
            .options(wireMockConfig().port(8006))
            .configureStaticDsl(true)
            .build();


    @Test
    void should_GetRoot() {
        String rootResponse = """
                {
                  "data": [
                    {
                      "subdir": "version"
                    },
                    {
                      "subdir": "cluster"
                    },
                    {
                      "subdir": "nodes"
                    },
                    {
                      "subdir": "storage"
                    },
                    {
                      "subdir": "access"
                    },
                    {
                      "subdir": "pools"
                    }
                  ]
                }
                """;

        stubFor(get("/api2/json/")
                .withHeader("Accept", equalTo("application/json"))
                .withHeader("Authorization", equalTo("PVEAPIToken=user@realm!token-id=generated-random-uuid"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(rootResponse)));

        String root = pveClient.get("/", String.class);

        assertThat(root).isEqualTo(rootResponse);
    }


}
