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

@RequestMapping("/uaa/api/v1/users")
@RestController
public class UserController {
	@Autowired
	private UserGraphQLService userGraphQLService;
	
	@PostMapping
	public ResponseEntity<Object> getAllUsers(@RequestBody String query) {
		ExecutionResult execute = userGraphQLService.getGraphQL().execute(query);
		return new ResponseEntity<>(execute, HttpStatus.OK);
	}
}
