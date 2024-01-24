package com.example.oauthmail.service;

import com.example.oauthmail.dto.request.auth.IdCheckRequestDto;
import com.example.oauthmail.dto.response.auth.IdCheckResponseDto;
import com.example.oauthmail.repository.UserRepository;
import com.example.oauthmail.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AuthServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("존재할때")
    public void testIdCheckWithExistingId() {
        // Arrange
        String existingId = "existingUserId";
        when(userRepository.existsByUserId(existingId)).thenReturn(true);

        IdCheckRequestDto requestDto = new IdCheckRequestDto();
        requestDto.setId(existingId);

        // Act
        ResponseEntity<? super IdCheckResponseDto> response = authService.idCheck(requestDto);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode()); // Assuming duplicateId() sets CONFLICT status
        // Additional assertions for the body can be made here
    }
    @Test
    public void testIdCheckWithNewId() {
        // Arrange
        String newId = "newUserId";
        when(userRepository.existsByUserId(newId)).thenReturn(false);

        IdCheckRequestDto requestDto = new IdCheckRequestDto();
        requestDto.setId(newId);

        // Act
        ResponseEntity<? super IdCheckResponseDto> response = authService.idCheck(requestDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

}
