package com.example.m8.controller;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.m4.services.UserDetailsImpl;
import com.example.m8.enums.Role;
import com.example.m8.request.ArticleRequest;
import com.example.m8.response.ArticleResponse;
import com.example.m8.service.ArticleService;

@RestController
@RequestMapping("/api/article")
public class ArticleController {
	
	@Autowired
	ArticleService articleService;
	
	@PostMapping("/create")
	public ResponseEntity<ArticleResponse> registerUser(@Valid @RequestBody ArticleRequest request) {
		UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		if(authorities.contains(new SimpleGrantedAuthority(Role.WRITER.value))) {
			return articleService.processAddArticle(user.getId(), request);
		}else {
			return ResponseEntity
					.badRequest()
					.body(new ArticleResponse("Error: No auhtority to write article!"));
		}
		
	}

}
