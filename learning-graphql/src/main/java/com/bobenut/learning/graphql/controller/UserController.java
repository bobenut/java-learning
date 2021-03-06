package com.bobenut.learning.graphql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bobenut.learning.graphql.service.UserGraphQLService;

import graphql.ExecutionResult;

/**
 * 描述：
 * 用户数据访问应用服务
 * @version v1
 */
@RequestMapping("/uaa/api/v1/users")
@RestController
public class UserController {
	@Autowired
	private UserGraphQLService userGraphQLService;
	

	/**
	 * 描述：
	 * 查询用户数据，可一次性所有用户数据，支持分页获取
	 * <p>处理1：如果用户不存在，咋地咋地</p>
	 * @param query graphql查询语句
	 * @return 用户数据
	 */
	@PostMapping
	public ResponseEntity<Object> getAllUsers(@RequestBody String query) {
		ExecutionResult execute = userGraphQLService.getGraphQL().execute(query);
		return new ResponseEntity<>(execute, HttpStatus.OK);
	}
}
