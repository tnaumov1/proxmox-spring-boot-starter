# Proxmox Spring Boot Starter

Starter for integrating a Spring Boot application with Proxmox VE API

## Features

- Autoconfiguration for Proxmox VE API integration
- Authentication via API Token
- Specialized `PveClient` for API calls

## Requirements

- Java 17+
- Spring Boot 3.5.X

## Getting Started

### Add the following dependency to your `pom.xml`

```xml
<dependency>
    <groupId>dev.tnaumov</groupId>
    <artifactId>proxmox-spring-boot-starter</artifactId>
    <version>0.1.0</version>
</dependency>
```

### Generate token in Proxmox VE
https://pve.proxmox.com/pve-docs/pve-admin-guide.html#pveum_tokens

### Specify Proxmox VE url and token information in your `application.properties` file

```properties
proxmox.pve.url=https://your.cluster:8006
proxmox.pve.token.id=user@realm!token-id
proxmox.pve.token.secret=generated-random-uuid
```

## Usage

### PveClient

Use PveClient to make requests to PVE API

```java
@Service
public class MyService {
    private final PveClient pveClient;

    public MyService(PveClient pveClient) {
        this.pveClient = pveClient;
    }
    
    // Call Proxmox VE via API here
}
```