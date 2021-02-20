package com.example.m8.request;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ArticleRequest {
	
	@NotEmpty
	@Size(max = 150)
	private String title;
	
	List<Integer> coAuthors;

}
