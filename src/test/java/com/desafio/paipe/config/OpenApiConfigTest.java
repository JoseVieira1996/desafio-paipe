package com.desafio.paipe.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.SpecVersion;
import io.swagger.v3.oas.models.info.Info;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OpenApiConfig.class})
@ExtendWith(SpringExtension.class)
class OpenApiConfigTest {
    @Autowired
    private OpenApiConfig openApiConfig;


    @Test
    void testCustomOpenAPI() {
        OpenAPI actualCustomOpenAPIResult = (new OpenApiConfig()).customOpenAPI();
        Info info = actualCustomOpenAPIResult.getInfo();
        assertEquals("1.0", info.getVersion());
        assertEquals("3.0.1", actualCustomOpenAPIResult.getOpenapi());
        assertEquals("API Documentation", info.getTitle());
        assertEquals("This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3.",
                info.getDescription());
        assertNull(actualCustomOpenAPIResult.getComponents());
        assertNull(actualCustomOpenAPIResult.getExternalDocs());
        assertNull(actualCustomOpenAPIResult.getPaths());
        assertNull(info.getContact());
        assertNull(info.getLicense());
        assertNull(info.getSummary());
        assertNull(info.getTermsOfService());
        assertNull(actualCustomOpenAPIResult.getSecurity());
        assertNull(actualCustomOpenAPIResult.getServers());
        assertNull(actualCustomOpenAPIResult.getTags());
        assertNull(actualCustomOpenAPIResult.getWebhooks());
        assertNull(actualCustomOpenAPIResult.getExtensions());
        assertNull(info.getExtensions());
        assertEquals(SpecVersion.V30, actualCustomOpenAPIResult.getSpecVersion());
    }

}
