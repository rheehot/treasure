package com.board.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * SqlSessionFactory : MySQL과 MyBatis를 연결해주는 객체
 * SqlSessionFactoryBean : SqlSessionFactory를 생성해주는 클래스
 * setDataSource() : datasource를 참조하게 한다.
 * PathMatchingResourcePatternResolver : resource 위치 검색을 돕는 SpringClass
 * getResources()로 경로 검색을 해 SqlSessionFactory에 mapper,mybatis-config를 set 해준다.
 * classpath : resource 폴더를 나타낸다.
 */
@Configuration
@MapperScan("com.board.dao") // 연결할 DAO 인터페이스를 담은 패키지를 등록.
public class MySQLConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:mappers/*.xml"));

        Resource myBatisConfig = new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml");
        sessionFactory.setConfigLocation(myBatisConfig);

        return sessionFactory.getObject();
    }
}
