package com.bobenut.learning.graphql.datafetcher;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bobenut.learning.graphql.domain.User;
import com.bobenut.learning.graphql.domain.UsersPageable;
import com.bobenut.learning.graphql.mapper.UserMapper;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class UsersPageableDataFetcher implements DataFetcher<UsersPageable> {
	
	private final static String PAGE_INDEX = "pageIndex";
	
	private final static String PAGE_SIZE = "pageSize";
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UsersPageable get(DataFetchingEnvironment dataFetchingEnviroment){
		Map<String, Object> fetchingArguments = dataFetchingEnviroment.getArguments();
		Page<User> page = null;
		if (fetchingArguments.containsKey(PAGE_INDEX) && fetchingArguments.containsKey(PAGE_SIZE)) {
			page = new Page<User>((int)fetchingArguments.get(PAGE_INDEX), (int)fetchingArguments.get(PAGE_SIZE));
		}

		IPage<User> usersOfPage = userMapper.getUsers(page);
		UsersPageable result = new UsersPageable();
		result.setAscs(usersOfPage.ascs());
		result.setDescs(usersOfPage.descs());
		result.setCurrent(usersOfPage.getCurrent());
		result.setSize(usersOfPage.getSize());
		result.setTotal(usersOfPage.getTotal());
		result.setRecords(usersOfPage.getRecords());
		return result;
	}
}
