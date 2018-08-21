package com.ibm.demo.config;

/*
 * http://www.mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/
 * 
 * The MyBatis-Spring-Boot-Starter will search, by default, for mappers marked with the @Mapper annotation.
 * You may want to specify a custom annotation or a marker interface for scanning. If so, you must use the @MapperScan annotation.
 * 
 * 滿多設定都可以用設定properties的方式處理, 例如定義mybatis.type-aliases-package / mybatis.type-handlers-package
 * 
 * http://nealma.com/2017/06/02/spring-boot-13-transaction/
 * 
 * 不確定若有多個資料來源, 在自動設定的情況下還是要有人去實作TransactionManagementConfigurer, 才能讓@Transactional有預設的txManager
 * 
 */
//@Configuration
//@MapperScan( basePackages = "com.ibm.**.mapper", sqlSessionFactoryRef = "sqlSessionFactory" )
//@EnableTransactionManagement
public class MyBatisConfig // implements TransactionManagementConfigurer
{
	// @Autowired
	// private DataSource dataSource;
	//
	// @Bean
	// public SqlSessionFactory sqlSessionFactory() throws Exception {
	// SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
	//
	// sessionFactory.setDataSource( dataSource );
	//
	// return sessionFactory.getObject();
	// }
	//
	// @Bean
	// @Override
	// public PlatformTransactionManager annotationDrivenTransactionManager() {
	// return new DataSourceTransactionManager( dataSource );
	// }
}