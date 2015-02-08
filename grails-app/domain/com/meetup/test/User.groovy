package com.meetup.test

class User implements Serializable{
	static mapWith = "mongo"


	static belongsTo = [organization: Organization]

	String username;
	String emailId;
	String name;


    static constraints = {
    }
}
