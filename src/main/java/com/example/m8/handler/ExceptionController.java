package com.example.m8.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.m8.exception.ErrorExc;
import com.example.m8.response.ArticleResponse;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler({ ErrorExc.class })
    public ResponseEntity<ArticleResponse>  handleException(ErrorExc exc) {
		return ResponseEntity
				.badRequest()
				.body(new ArticleResponse(exc.getMessage()));
    }

}


