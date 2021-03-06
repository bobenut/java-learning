package com.bobenut.learning.graphql.datafetcher;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bobenut.learning.graphql.domain.User;
import com.bobenut.learning.graphql.mapper.UserMapper;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class UsersDataFetcher implements DataFetcher<List<User>> {
	
	private final static String PAGE_INDEX = "pageIndex";
	
	private final static String PAGE_SIZE = "pageSize";
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<User> get(DataFetchingEnvironment dataFetchingEnviroment){
		Map<String, Object> fetchingArguments = dataFetchingEnviroment.getArguments();
		Page<User> page = null;
		if (fetchingArguments.containsKey(PAGE_INDEX) && fetchingArguments.containsKey(PAGE_SIZE)) {
			page = new Page<User>((int)fetchingArguments.get(PAGE_INDEX), (int)fetchingArguments.get(PAGE_SIZE));
		}

		IPage<User> users = userMapper.getUsers(page);
		return users.getRecords();
	}
}
