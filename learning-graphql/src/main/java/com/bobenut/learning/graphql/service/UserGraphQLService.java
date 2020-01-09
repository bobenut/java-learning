package com.bobenut.learning.graphql.service;

import java.io.IOException;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bobenut.learning.graphql.datafetcher.UsersDataFetcher;
import com.bobenut.learning.graphql.datafetcher.UsersPageableDataFetcher;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Component
public class UserGraphQLService {
	
	@Autowired
	private UsersDataFetcher usersDataFetcher;
	
	@Autowired
	private UsersPageableDataFetcher  usersPageableDataFetcher;
	
	private GraphQL graphQL;
	
	@PostConstruct
	public void init() throws IOException {
        URL url = Resources.getResource("user.graphqls");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
	}
	
	 private GraphQLSchema buildSchema(String sdl) {
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
		RuntimeWiring runtimeWiring = buildWiring();
		SchemaGenerator schemaGenerator = new SchemaGenerator();
		return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
	 }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> 
                	typeWiring.dataFetcher("getUsers", usersDataFetcher)
                	.dataFetcher("getUsersPageable", usersPageableDataFetcher))
                .build();
    }
    
    public GraphQL getGraphQL(){
        return graphQL;
    }
    
    /**
     * <h1>通过组织id删除组织</h1>
     * <br>
	 * <p>按照规则组合客户ID，生成对应的数据库名称。</p>
     * <h2>验证参数：</h2>
     * <ul>
     *   <li>组织不存在的返回失败信息。</li>
     *   <li>当前组织的节点类型是固定的时候，返回失败信息。</li>
     * </ul>
	 * <br>
     * <h2>删除：</h2>
     * <ul>
     *   <li>通过组织id删除组织表中对应组织的记录，逻辑删除。</li>
     *   <li>删除组织后，需要判断父组织是否存在子组织，存在的话，将父组织的层级关系修改为末节点。</li>
     *   <li>更新组织索引表中的数据。</li>
     *   <li>检查是否包含自定义字段，如果包含，删除自定义数据表中的数据。</li>
     * </ul>
     * <br>
     * <h3>修改历史：</h3>
     * <ul>
     *   <li>2019-12-01 小明</li>
     *   <li>2019-12-31 小王</li>
     * </ul>
     * <br>
     * @param id 组织id
     */
    public void deleteOrg(String id) {
    	
    }
}
