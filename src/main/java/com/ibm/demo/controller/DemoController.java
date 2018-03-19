package com.ibm.demo.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class DemoController {
	private final Logger log = LoggerFactory.getLogger( this.getClass() );

	@Value( "${test.message:}" )
	private String message;

	@Autowired
	private Registration registration;

	@Autowired
	private DiscoveryClient discoveryClient;

	@GetMapping( "/instance-info" )
	public ServiceInstance showInfo() {
		log.info( "Message: " + message + ", is empty: " + StringUtils.isEmpty( message ) );

		// DiscoveryClient的getLocalServiceInstance方法已被標註為@Deprecated, 建議不要使用
		List<ServiceInstance> list = discoveryClient.getInstances( registration.getServiceId() );

		return list.isEmpty() ? null : list.get( 0 );
	}
}