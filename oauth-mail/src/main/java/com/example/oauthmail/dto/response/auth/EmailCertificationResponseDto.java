package com.example.oauthmail.dto.response.auth;

import com.example.oauthmail.common.ResponseCode;
import com.example.oauthmail.common.ResponseMessage;
import com.example.oauthmail.dto.response.ResponseDto;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class EmailCertificationResponseDto extends ResponseDto {
  
  private EmailCertificationResponseDto() {
    super();
  }

  public static ResponseEntity<EmailCertificationResponseDto> success() {
    EmailCertificationResponseDto responseBody = new EmailCertificationResponseDto();
    return ResponseEntity.status(HttpStatus.OK).body(responseBody);
  }

  public static ResponseEntity<ResponseDto> duplicateId() {
    ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATION_ID, ResponseMessage.DUPLICATION_ID);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
  }

  public static ResponseEntity<ResponseDto> mailSendFail() {
    ResponseDto responseBody = new ResponseDto(ResponseCode.MAIL_FAIL, ResponseMessage.MAIL_FAIL);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
  }

}
