package com.example.oauthmail.service.impl;


import com.example.oauthmail.common.CertificationNumber;
import com.example.oauthmail.dto.request.auth.EmailCertificationRequestDto;
import com.example.oauthmail.dto.request.auth.IdCheckRequestDto;
import com.example.oauthmail.dto.response.ResponseDto;
import com.example.oauthmail.dto.response.auth.EmailCertificationResponseDto;
import com.example.oauthmail.dto.response.auth.IdCheckResponseDto;
import com.example.oauthmail.entity.Certification;
import com.example.oauthmail.provider.EmailProvider;
import com.example.oauthmail.repository.CertificationRepository;
import com.example.oauthmail.repository.UserRepository;
import com.example.oauthmail.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private  final UserRepository userRepository;
    private final CertificationRepository certificationRepository;

    private  final EmailProvider emailProvider;
    @Override
    public ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto idCheckRequestDto) {
        try {
            String userId = idCheckRequestDto.getId();
            boolean isExistId = userRepository.existsByUserId(userId);
            if(isExistId) return IdCheckResponseDto.duplicateId();



        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return IdCheckResponseDto.success();
    }

    @Override
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto) {
        try {
            String userId = dto.getId();
            String email = dto.getEmail();
            boolean isExistId = userRepository.existsByUserId(userId);
            if(isExistId) return EmailCertificationResponseDto.duplicateId();
            String certificationNumber = CertificationNumber.getCertificationNumber(); // 임의의 4자리수 받아오기
            boolean succeeded = emailProvider.sendCertificationMail(email, certificationNumber);
            if(!succeeded) return EmailCertificationResponseDto.mailSendFail();


            Certification certification = new Certification(userId,email,certificationNumber);
            certificationRepository.save(certification);


        }catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }


        return EmailCertificationResponseDto.success();
    }
}
