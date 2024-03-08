package com.example.demo.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        // Xử lý lỗi ở đây và trả về trang lỗi tương ứng
        return "errorPage"; // Thay "errorPage" bằng tên của template HTML trang lỗi của bạn
    }
}