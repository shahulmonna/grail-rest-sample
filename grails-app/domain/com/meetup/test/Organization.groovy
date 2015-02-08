package com.meetup.test

class Organization implements Serializable{
	static mapWith = "mongo"

	static hasMany = [user:User]

	String name;

    static constraints = {
    }
}
