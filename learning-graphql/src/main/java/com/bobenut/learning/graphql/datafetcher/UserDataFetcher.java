package com.bobenut.learning.graphql.datafetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bobenut.learning.graphql.domain.User;
import com.bobenut.learning.graphql.mapper.UserMapper;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class UserDataFetcher implements DataFetcher<List<User>> {
	 
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<User> get(DataFetchingEnvironment dataFetchingEnviroment){
		List<User> users = userMapper.getUsers();
		return users;
	}
}
