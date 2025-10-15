
package com.example.ecommerce.integration;

import com.example.ecommerce.EcommerceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = EcommerceApplication.class)
@ActiveProfiles("test")
public class AppIntegrationTest {

    @Test
    public void contextLoads() {
        // simply ensure the context starts with test profile (uses H2)
    }
}
