package com.processing.taskmanagementsystem.dto.response.authentication;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserAuthenticationResponse {
    private String accessToken;
}