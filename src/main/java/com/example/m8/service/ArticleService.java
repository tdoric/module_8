package com.example.m8.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.m1.dao.ArticleDao;
import com.example.m1.model.Article;
import com.example.m1.model.ArticleAccount;
import com.example.m8.request.ArticleRequest;
import com.example.m8.response.ArticleResponse;

@Service
public class ArticleService {
	
	@Autowired
	ArticleDao articleDao;
	
	public ResponseEntity<ArticleResponse> processAddArticle(Integer userId,ArticleRequest request){
		Article article = new Article();
		article.setTitle(request.getTitle());
		
		Integer articleId = articleDao.insertArticle(article);
		
		//TODO check if coexist
		List<ArticleAccount> list = new ArrayList<>();
		ArticleAccount mainAuthor= new ArticleAccount();
		mainAuthor.setArticleId(articleId);
		mainAuthor.setUserId(userId);
		list.add(mainAuthor);
		if(!request.getCoAuthors().isEmpty()) {
			for(Integer coworker: request.getCoAuthors()) {
				ArticleAccount articleAcc= new ArticleAccount();
				articleAcc.setArticleId(articleId);
				articleAcc.setUserId(coworker);
				list.add(articleAcc);
			}
		}
		articleDao.insertAuthorsOfArticle(list);
		return ResponseEntity
				.badRequest()
				.body(new ArticleResponse("Error: YOU are not the owner!"));
	}


}
