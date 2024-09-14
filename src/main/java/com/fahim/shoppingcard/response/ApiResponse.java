package com.fahim.shoppingcard.response;

import com.fahim.shoppingcard.dto.ImageDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
public class ApiResponse {
    private String message;
    private Object data;

    public ApiResponse(String uploadSuccessful, List<ImageDto> imageDtos) {
        this.message = uploadSuccessful;
        this.data = imageDtos;
    }

    public ApiResponse(String uploadSuccessful, String message) {
        this.message = message;
        this.data = uploadSuccessful;
    }

    public ApiResponse(String message) {
        this.message = message;
    }
}
