package com.example.umc10th.domain.mission.exception;

import com.example.umc10th.domain.mission.exception.code.RestaurantErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;

public class RestaurantException extends ProjectException {
    public RestaurantException(RestaurantErrorCode code) {
        super(code);
    }
}
