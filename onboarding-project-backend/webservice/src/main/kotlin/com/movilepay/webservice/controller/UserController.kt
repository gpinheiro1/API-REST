package com.movilepay.webservice.controller

import com.movilepay.api.base.BaseResponse
import com.movilepay.api.user.UserDetailResponse
import com.movilepay.api.user.UserRequest
import com.movilepay.api.user.UserResponse
import com.movilepay.api.user.UserType
import com.movilepay.api.user.UserTypeResponse
import com.movilepay.business.service.UserService
import com.movilepay.webservice.extension.paraCaixaAlta
import com.movilepay.webservice.extension.toUserDetailResponse
import com.movilepay.webservice.parser.UserParser
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(UserController.ROUTE, produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
class UserController(
    private val userService: UserService
) {

    companion object {
        const val ROUTE: String = "/users"
    }

    @GetMapping
    fun getUsers(
        @RequestParam("page", required = false, defaultValue = "0") page : Int,
        @RequestParam("count", required = false, defaultValue = "10") count: Int
    ): ResponseEntity<Collection<UserResponse>> {
            var users = userService.getAllUsers(page, count)
            val response = users.map{ UserParser.parseToUserResponse(it) }
            return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{userId}")
    fun deleteUser(
        @PathVariable("userId") userId: String ) : ResponseEntity<BaseResponse> {
            userService.deleteUser(userId)
            return ResponseEntity.ok(BaseResponse("Success"))
    }

    @GetMapping("/{userId}")
    fun getUserById(
        @PathVariable("userId") userId: String) : ResponseEntity<UserDetailResponse>{
            val user = userService.getUserById(userId) ?:
                return ResponseEntity.notFound().build()
            return ResponseEntity.ok(user.toUserDetailResponse())
    }

    @PostMapping
    fun createUser(
        @RequestBody userRequest: UserRequest
    ): ResponseEntity<UserDetailResponse> {
            var userModel = UserParser.parseToUserModel(userRequest)
            val createdUser = userService.create(userModel)
            return ResponseEntity(userModel.toUserDetailResponse(), HttpStatus.CREATED)
    }


    @PutMapping("/{userId}")
    fun updateUser(
        @PathVariable("userId") userId: String,
        @RequestBody userRequest: UserRequest) : ResponseEntity<UserDetailResponse> {
            val userModel = UserParser.parseToUserModel(userRequest, userId)
            val user = userService.updateUser(userModel)
            val response = UserParser.parseToUserDetailResponse(user)
            return ResponseEntity.ok(response)

    }

    @GetMapping("/types")
    fun getUserTypes(): ResponseEntity<Collection<UserTypeResponse>>{
        val types = UserType.values().map { UserTypeResponse(it.ordinal ,it.name) }
        return ResponseEntity.ok(types)
    }
}