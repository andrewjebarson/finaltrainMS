package com.sella.exception;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.sella.model.Error;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler{
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<FieldError>  errors=ex.getBindingResult().getFieldErrors();
		List<String>erMsg=new ArrayList<String>();
		for(FieldError fe:errors) {
			erMsg.add(fe.getDefaultMessage());
		}
			Error er=new Error();
			er.setCode(HttpStatus.BAD_REQUEST.toString());
			er.setErMsg(erMsg.toString());
		return new ResponseEntity<>(er,headers,status);
	}
}