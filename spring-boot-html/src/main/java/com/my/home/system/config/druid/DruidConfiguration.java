package com.my.home.system.config.druid;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DruidConfiguration {


	private static final Logger log = LoggerFactory.getLogger(DruidConfiguration.class);

	@ConditionalOnClass(DruidDataSource.class) // 自动装配

	@ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.alibaba.druid.pool.DruidDataSource", matchIfMissing = true)
	// 这个注解能够控制某个configuration是否生效。
	static class Druid extends DruidConfiguration {

		@Bean
		@ConfigurationProperties(prefix = "spring.datasource")
		@DependsOn("wallFilter")
		public DruidDataSource dataSource(DataSourceProperties properties,WallFilter wallFilter) {
			DruidDataSource druidDataSource = (DruidDataSource) properties.initializeDataSourceBuilder().build();
			DatabaseDriver databaseDriver = DatabaseDriver.fromJdbcUrl(properties.determineUrl());
			String validationQuery = databaseDriver.getValidationQuery();
			if (validationQuery != null) {
				druidDataSource.setValidationQuery(validationQuery);
			}
			  // filter
	        List<Filter> filters = new ArrayList<>();
	        filters.add(wallFilter);
	        druidDataSource.setProxyFilters(filters);
			return druidDataSource;

		}
	}
	
	@Bean(name = "wallFilter")
    @DependsOn("wallConfig")
    public WallFilter wallFilter(WallConfig wallConfig){
        WallFilter wallFilter = new WallFilter();
        wallFilter.setConfig(wallConfig);
        return wallFilter;
    }
 
    @Bean(name = "wallConfig")
    public WallConfig wallConfig(){
        WallConfig wallConfig = new WallConfig();
        wallConfig.setMultiStatementAllow(true);//允许一次执行多条语句
        wallConfig.setNoneBaseStatementAllow(true);//允许一次执行多条语句
        return wallConfig;
    }

}
