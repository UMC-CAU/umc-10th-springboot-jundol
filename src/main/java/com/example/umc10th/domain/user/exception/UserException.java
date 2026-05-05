package com.example.umc10th.domain.user.exception;

import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;

public class UserException extends ProjectException {
    public UserException(UserErrorCode errorCode) {
        super(errorCode);
    }
}
