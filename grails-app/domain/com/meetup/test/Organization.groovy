package com.meetup.test

import org.restapidoc.annotation.RestApiObject
import org.restapidoc.annotation.RestApiObjectField

@RestApiObject(name = "organization", description = "Organization")
class Organization implements Serializable{
	static mapWith = "mongo"

	@RestApiObjectField(apiFieldName= "users", description = "Users of the Organization",allowedType =  "List",useForCreation = false)
	static hasMany = [user:User]

	@RestApiObjectField(description = "Name of the Organization")
	String name;

    static constraints = {
			name maxSize: 100, blank: false,size: 2..80
    }

	static mapping = {
		index name:true
	}
}
