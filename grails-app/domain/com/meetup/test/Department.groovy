package com.meetup.test

import org.restapidoc.annotation.RestApiObject
import org.restapidoc.annotation.RestApiObjectField

import javax.annotation.Resource

/**
 * Created by syedshahul on 12/2/15.*/

@RestApiObject(name = "department", description = "Department")
class Department implements Serializable {
	static mapWith = "mongo"

	@RestApiObjectField(description = "Department Id")
	String deptId;
	@RestApiObjectField(description = "Department Name")
	String name;
}