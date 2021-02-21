package com.example.m8.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.m4.services.UserDetailsImpl;
import com.example.m8.exception.ErrorExc;
import com.example.m8.request.ArticleRequest;
import com.example.m8.response.ArticleResponse;
import com.example.m8.service.ArticleService;

@RestController
@RequestMapping("/api/article")
public class ArticleController {
	
	@Autowired
	ArticleService articleService;
	
	@PostMapping("/create")
	@PreAuthorize("hasAuthority('Writer')")
	public ResponseEntity<ArticleResponse> registerUser(@Valid @RequestBody ArticleRequest request) throws ErrorExc {
		UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    return articleService.processAddArticle(user.getId(), request);
	}

}
