package com.example.oauthmail.service;


import com.example.oauthmail.dto.request.auth.EmailCertificationRequestDto;
import com.example.oauthmail.dto.request.auth.IdCheckRequestDto;
import com.example.oauthmail.dto.response.auth.EmailCertificationResponseDto;
import com.example.oauthmail.dto.response.auth.IdCheckResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface AuthService {
    ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto idCheckRequestDto); //리스폰스엔트티로 반환할건데 IdCheckResponseDto의 부모 도 가져올수있게. 매개변수는 request
    ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto);

}
