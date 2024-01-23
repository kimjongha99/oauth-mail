package com.example.oauthmail.provider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JwtProviderTest {



    @BeforeEach
    void setUp() {
        jwtProvider = new JwtProvider();
        // Set the secret key directly for testing
        ReflectionTestUtils.setField(jwtProvider, "secretKey", "your-seasdasdasdasdasdascret-key");
    }

    JwtProvider jwtProvider;

    @Test
    @DisplayName("토큰 생성 및 검증")
        public void testCreateToken() {
            String userId = "user123";
            String token = jwtProvider.create(userId);
            assertNotNull(token, "Token should not be null");
            // Further tests can be added here to validate the structure of the token
        }


    }