package com.ibm.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.demo.mapper.CityMapper;

@RestController
@RefreshScope
public class DemoController {
	private final Logger log = LoggerFactory.getLogger( this.getClass() );

	@Value( "${test.message:}" )
	private String message;

	@Value( "${server.port}" )
	private int port;

	@Autowired
	private Registration registration;

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private CityMapper cityMapper;

	@GetMapping( "/instance-info" )
	public ServiceInstance showInfo() {
		System.out.println( cityMapper.findByState( "CA" ) );
		System.out.println( cityMapper.selectCityById( 1 ) );

		log.info( "Message: " + message + ", is empty: " + StringUtils.isEmpty( message ) );

		// DiscoveryClient的getLocalServiceInstance方法已被標註為@Deprecated, 建議不要使用
		List<ServiceInstance> list = discoveryClient.getInstances( registration.getServiceId() );

		// 注意! 當有HA時, 相同service id拿到的list順序是不定的, 不同時間的順序也可能不同
		log.info( "Uri: " + list.stream().map( ServiceInstance::getUri ).collect( Collectors.toList() ) );

		return list.stream().filter( i -> i.getPort() == port ).findFirst().orElse( null );
	}

	@GetMapping( "/transaction" )
	@Transactional
	public void transaction() {
		log.info( "Result: " + cityMapper.updateCityById( 1 ) );

		throw new RuntimeException( "Rollback!" );
	}
}