//package com.example.heapspring.controller;
//
//import com.example.heapspring.DTOs.ErrorResponseDTO;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class ExceptionAdvice {
//
//    @ExceptionHandler(InternalError.class)
//    public ResponseEntity<ErrorResponseDTO> handleInternalServer(Exception e){
//        ErrorResponseDTO errorResponseDTO=new ErrorResponseDTO();
//        errorResponseDTO.setErrorMessage(e.getMessage());
//        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}
