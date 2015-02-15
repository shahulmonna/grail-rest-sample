package com.meetup.test

import grails.converters.JSON
import grails.rest.RestfulController
import org.restapidoc.annotation.RestApi
import org.restapidoc.annotation.RestApiMethod
import org.restapidoc.annotation.RestApiParam
import org.restapidoc.annotation.RestApiParams
import org.restapidoc.pojo.RestApiParamType

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@RestApi(name = "Department Services", description = "Methods for managing Department")
class DepartmentController extends RestfulController<Department>{

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	DepartmentController() {
		super(Department)
	}

	@Override
	@RestApiMethod(description="List Departments",listing = true)
	@RestApiParams(params=[
			@RestApiParam(name="max", type="int", paramType = RestApiParamType.PATH, description = "Max limit")
	])
	Object index() {
		params.max = Math.min(10, 100)
		return super.index(params.max)
	}

	@RestApiMethod(description="Get a Department")
	@RestApiParams(params=[
			@RestApiParam(name="deptId", type="string", paramType = RestApiParamType.PATH, description = "The Department Id")
	])
	@Override
	Object show() {
		respond Department.findByDeptId(params.deptId)
	}


	@Override
	@RestApiMethod(description="Add a Department")
	Object save() {
		return super.save()
	}


	@RestApiMethod(description="Edit a Department")
	@RestApiParams(params=[
			@RestApiParam(name="deptId", type="string", paramType = RestApiParamType.PATH, description = "The book id")
	])
	Object update() {
		def dept =Department.findByDeptId(params.deptId)
		if (!dept) {
			response.status = 404
			return
		}
		dept.properties = params
		if (!dept.save(flush: true)) {
			response.status = 400
			render dept.errors.toString()
		} else {
			render (dept as JSON).toString()
		}
	}

	@Override
	Object delete() {
		return super.delete()
	}
}
