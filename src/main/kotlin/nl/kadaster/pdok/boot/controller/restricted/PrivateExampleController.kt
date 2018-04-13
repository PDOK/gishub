package nl.kadaster.pdok.boot.controller.restricted

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import nl.kadaster.pdok.boot.controller.RouteConstants.PRIVATE
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(value = PRIVATE + "/example")
@Api(tags = arrayOf("Public Example Controller"), description = "Example Controller")
class PrivateExampleController {


    @ResponseBody
    @RequestMapping(value = "secure", method = arrayOf(RequestMethod.GET))
    @ApiOperation(value = "Check public controller is accessible", notes = "", response = String::class)
    @PreAuthorize("isAuthenticated()")
    fun getSecured(): String {
        return "Secured!!"
    }

}