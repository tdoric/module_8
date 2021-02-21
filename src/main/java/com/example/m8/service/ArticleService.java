package com.example.m8.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.m1.dao.AccountDao;
import com.example.m1.dao.ArticleDao;
import com.example.m1.model.Article;
import com.example.m1.model.ArticleAccount;
import com.example.m8.exception.ErrorExc;
import com.example.m8.request.ArticleRequest;
import com.example.m8.response.ArticleResponse;

@Service
@Transactional
public class ArticleService {
	
	@Autowired
	ArticleDao articleDao;
	@Autowired
	AccountDao accountDao;
	
	public ResponseEntity<ArticleResponse> processAddArticle(Integer userId,ArticleRequest request) throws ErrorExc{
		
		
		if(!request.getCoAuthors().isEmpty()) {
			for(Integer coworker: request.getCoAuthors()) {
				if(!accountDao.checkIfExist(coworker)) {
					throw new ErrorExc("Error: coauthor "+coworker+" not exist!");
				}
			}
		}
		
		Article article = new Article();
		article.setTitle(request.getTitle());
		
		Integer articleId;
		try{
			articleId=articleDao.insertArticle(article);
		}catch (DuplicateKeyException e) {
			throw new ErrorExc("Article with same title exist!");
		}
		ArticleAccount mainAuthor= new ArticleAccount();
		mainAuthor.setArticleId(articleId);
		mainAuthor.setUserId(userId);
		List<ArticleAccount> list = new ArrayList<>();
		list.add(mainAuthor);
		for(Integer coworker: request.getCoAuthors()) {
				ArticleAccount articleAcc= new ArticleAccount();
				articleAcc.setArticleId(articleId);
				articleAcc.setUserId(coworker);
				list.add(articleAcc);
		}
		
		articleDao.insertAuthorsOfArticle(list);
		return ResponseEntity.ok(new ArticleResponse("Added article successfully!"));
		
		
	}

}
