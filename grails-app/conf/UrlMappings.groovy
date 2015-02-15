class UrlMappings {

	static mappings = {

		"/api/users"(controller:"user"){
			action = [GET:"index"]
		}

		"/api/users/$id.$format"(controller:"user"){
			action = [GET:"show",PUT:"update", DELETE:"delete"]
		}



		"/api/dummies"(resource: 'dummy')
		"/api/organizations"(controller:"organization"){
			action = [GET:"index"]
		}

		"/api/organizations/$id.$format"(controller:"organization"){
			action = [GET:"show",PUT:"update", DELETE:"delete"]
		}

		"/api/departments"(controller:"department"){
			action = [GET:"index"]
		}

		"/api/departments/$id.$format"(controller:"department"){
			action = [GET:"show",PUT:"update", DELETE:"delete"]
		}

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

		/*"/api/department/$id.$format"(controller:"department"){
			action = [GET:"index,show",PUT:"update", DELETE:"delete"]
		}*/

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
