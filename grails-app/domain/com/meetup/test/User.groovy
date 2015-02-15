package com.meetup.test

import org.restapidoc.annotation.RestApiObject
import org.restapidoc.annotation.RestApiObjectField

@RestApiObject(name = "user", description = "User")
class User implements Serializable{
	static mapWith = "mongo"


	static belongsTo = [organization: Organization]

	@RestApiObjectField(description = "username for the User")
	String username;
	@RestApiObjectField(description = "email of the User")
	String emailId;
	@RestApiObjectField(description = "name of the User")
	String name;
	@RestApiObjectField(description = "Photo image URL", mandatory = false)
	String logoImageUrl;


    static constraints = {
			name maxSize: 100, blank: false,size: 2..50
			emailId nullable: false, email: true,unique:true
    }

	static mapping = {
		compoundIndex name:"2d", emailId:1
	}
}
