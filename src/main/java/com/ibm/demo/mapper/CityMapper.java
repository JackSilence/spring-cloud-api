package com.ibm.demo.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ibm.demo.model.City;

public interface CityMapper {
	@Select( "select * from city where state = #{state}" )
	City findByState( @Param( "state" ) String state );

	City selectCityById( int city_id );
}