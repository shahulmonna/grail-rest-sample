package com.meetup.test

import grails.converters.JSON
import grails.converters.XML
import grails.rest.RestfulController
import org.restapidoc.annotation.RestApi
import org.restapidoc.annotation.RestApiMethod
import org.restapidoc.annotation.RestApiParam
import org.restapidoc.annotation.RestApiParams
import org.restapidoc.pojo.RestApiParamType

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@RestApi(name = "Organization Services", description = "Methods for managing Organizations")
class OrganizationController extends RestfulController<Organization> {

    static responseFormats = ['json', 'xml']
/*    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]*/

	OrganizationController(){
		super(Organization)
	}

	@RestApiMethod(description="Get Organizations",listing = true)
	@RestApiParams(params=[
			@RestApiParam(name="max", type="int", paramType = RestApiParamType.PATH,
					description = "max limit")
	])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Organization.list(params), [status: OK]
    }

	@Override
	@RestApiMethod(description="Get a Organization")
	@RestApiParams(params=[
			@RestApiParam(name="id", type="int", paramType = RestApiParamType.PATH,description = "id of the Organization")
	])
	def show() {
		println("id: ${params.id} qs ${params.toQueryString()}")
		def organizationInstance = Organization.read(params.id)
		if (!organizationInstance) {
			response.status = 404
			render "Not found!"
		} else {
			render params.format =="json" ? organizationInstance as JSON :organizationInstance
		}
	}

	@Transactional
	@RestApiMethod(description="Create Organization")
    def save(Organization organizationInstance) {
        if (organizationInstance == null) {
            render status: NOT_FOUND
            return
        }

        organizationInstance.validate()
        if (organizationInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        organizationInstance.save flush:true
        respond organizationInstance, [status: CREATED]
    }

    @Transactional
		@RestApiMethod(description="Update Organization")
    def update(Organization organizationInstance) {
        if (organizationInstance == null) {
            render status: NOT_FOUND
            return
        }

        organizationInstance.validate()
        if (organizationInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        organizationInstance.save flush:true
        respond organizationInstance, [status: OK]
    }

    @Transactional
		@RestApiMethod(description="Delete Organization")
    def delete(Organization organizationInstance) {

        if (organizationInstance == null) {
            render status: NOT_FOUND
            return
        }

        organizationInstance.delete flush:true
        render status: NO_CONTENT
    }
}
