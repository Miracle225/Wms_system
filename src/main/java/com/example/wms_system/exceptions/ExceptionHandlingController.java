package com.example.wms_system.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;


@ControllerAdvice
public class ExceptionHandlingController {
    private static final String EMPTY_URI = null;

    @ResponseBody
    @ExceptionHandler(AcceptanceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse acceptanceNotFoundHandler(AcceptanceNotFoundException exception, HttpServletRequest sr) {
        return bulidErrorResponse(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(), exception.getMessage(), sr.getRequestURI());
    }

    @ResponseBody
    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse customerNotFoundHandler(CustomerNotFoundException exception, HttpServletRequest sr) {
        return bulidErrorResponse(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(), exception.getMessage(), sr.getRequestURI());
    }
    @ResponseBody
    @ExceptionHandler(GoodNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse goodNotFoundHandler(GoodNotFoundException exception, HttpServletRequest sr) {
        return bulidErrorResponse(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(), exception.getMessage(), sr.getRequestURI());
    }
    @ResponseBody
    @ExceptionHandler(OperationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse operationNotFoundHandler(OperationNotFoundException exception, HttpServletRequest sr) {
        return bulidErrorResponse(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(), exception.getMessage(), sr.getRequestURI());
    }
    @ResponseBody
    @ExceptionHandler(WarehouseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse warehouseNotFoundHandler(WarehouseNotFoundException exception, HttpServletRequest sr) {
        return bulidErrorResponse(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(), exception.getMessage(), sr.getRequestURI());
    }
    @ResponseBody
    @ExceptionHandler(WarehouseSectorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse warehouseSectorNotFoundHandler(WarehouseSectorNotFoundException exception, HttpServletRequest sr) {
        return bulidErrorResponse(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(), exception.getMessage(), sr.getRequestURI());
    }

    @ResponseBody
    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse orderNotFoundHandler(OrderNotFoundException exception, HttpServletRequest sr) {
        return bulidErrorResponse(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(), exception.getMessage(), sr.getRequestURI());
    }

    @ResponseBody
    @ExceptionHandler(ProviderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse providerNotFoundHandler(ProviderNotFoundException exception, HttpServletRequest sr) {
        return bulidErrorResponse(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(), exception.getMessage(), sr.getRequestURI());
    }

    @ResponseBody
    @ExceptionHandler(AcceptanceItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse acceptanceItemNotFoundHandler(AcceptanceItemNotFoundException exception, HttpServletRequest sr) {
        return bulidErrorResponse(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(), exception.getMessage(), sr.getRequestURI());
    }
    @ResponseBody
    @ExceptionHandler(CustomerAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorResponse customerAlreadyExistsHandler(CustomerAlreadyExists exception) {
        return bulidErrorResponse(HttpStatus.CONFLICT.name(), HttpStatus.CONFLICT.value(), exception.getMessage(), EMPTY_URI);
    }

    @ResponseBody
    @ExceptionHandler(GoodAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorResponse goodAlreadyExistsHandler(GoodAlreadyExists exception) {
        return bulidErrorResponse(HttpStatus.CONFLICT.name(), HttpStatus.CONFLICT.value(), exception.getMessage(), EMPTY_URI);
    }

    @ResponseBody
    @ExceptionHandler(SectorAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorResponse sectorAlreadyExistsHandler(SectorAlreadyExists exception) {
        return bulidErrorResponse(HttpStatus.CONFLICT.name(), HttpStatus.CONFLICT.value(), exception.getMessage(), EMPTY_URI);
    }

    @ResponseBody
    @ExceptionHandler(UserAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorResponse userAlreadyExistsHandler(UserAlreadyExists exception) {
        return bulidErrorResponse(HttpStatus.CONFLICT.name(), HttpStatus.CONFLICT.value(), exception.getMessage(), EMPTY_URI);
    }

    @ResponseBody
    @ExceptionHandler(UserDuplicateEmail.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorResponse userDuplicateEmailHandler(UserDuplicateEmail exception) {
        return bulidErrorResponse(HttpStatus.CONFLICT.name(), HttpStatus.CONFLICT.value(), exception.getMessage(), EMPTY_URI);
    }

    @ResponseBody
    @ExceptionHandler(WarehouseAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorResponse warehouseAlreadyExistsHandler(WarehouseAlreadyExists exception) {
        return bulidErrorResponse(HttpStatus.CONFLICT.name(), HttpStatus.CONFLICT.value(), exception.getMessage(), EMPTY_URI);
    }

    private ErrorResponse bulidErrorResponse(String status, int statusCode, String message, String url){
        return new ErrorResponse(status, statusCode, message, url, LocalDateTime.now());
    }

}
