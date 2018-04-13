package nl.kadaster.pdok.boot.controller


import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation

import nl.kadaster.pdok.boot.controller.RouteConstants.MOUNT

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = MOUNT + "/example")
@Api(tags = arrayOf("Public Example Controller"), description = "Example Controller")
class ExampleController {


    @RequestMapping(value = "secure", method = arrayOf(RequestMethod.GET))
    @ApiOperation(value = "Check public controller is accessible", notes = "", response = String::class)
    fun getSecured(): String {
        return "free"
    }
}