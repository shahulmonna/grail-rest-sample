package com.meetup.test

import org.restapidoc.annotation.RestApiMethod
import org.restapidoc.annotation.RestApiParam
import org.restapidoc.annotation.RestApiParams
import org.restapidoc.pojo.RestApiParamType

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	@RestApiMethod(description="Get Users",listing = true)
	@RestApiParams(params=[
			@RestApiParam(name="max", type="int", paramType = RestApiParamType.PATH,
					description = "max limit")
	])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond User.list(params), [status: OK]
    }

	@RestApiMethod(description="Get a User")
	@RestApiParams(params=[
			@RestApiParam(name="username", type="String", paramType = RestApiParamType.PATH,description = "username of the User")
	])
	def show() {
		respond User.findByUsername(params.username), [status: OK]
	}
    @Transactional
		@RestApiMethod(description="Create User")
    def save(User userInstance) {
        if (userInstance == null) {
            render status: NOT_FOUND
            return
        }

        userInstance.validate()
        if (userInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        userInstance.save flush:true
        respond userInstance, [status: CREATED]
    }

    @Transactional
		@RestApiMethod(description="Update User")
    def update(User userInstance) {
        if (userInstance == null) {
            render status: NOT_FOUND
            return
        }

        userInstance.validate()
        if (userInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        userInstance.save flush:true
        respond userInstance, [status: OK]
    }

    @Transactional
		@RestApiMethod(description="Delete User")
    def delete(User userInstance) {

        if (userInstance == null) {
            render status: NOT_FOUND
            return
        }

        userInstance.delete flush:true
        render status: NO_CONTENT
    }
}
