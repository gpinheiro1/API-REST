package com.movilepay.business.service

import com.movilepay.business.model.User
import com.movilepay.business.repository.UserRepository
import com.movilepay.business.repository.UserStoreRepository
import com.movilepay.business.service.UserFixture.getInvalidUser
import com.movilepay.business.service.UserFixture.getValidUser
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import java.lang.IllegalArgumentException
import java.util.UUID
import kotlin.test.assert
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@ExtendWith(MockKExtension::class)
class UserServiceTests {

    @MockK
    private lateinit var _mockedUserRepository: UserRepository
    @MockK
    private lateinit var _mockedAddressService: AddressService
    @MockK
    private lateinit var _mockedUserStoreRepository: UserStoreRepository

    private lateinit var userService: UserService

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        setUpMockUserRepository()
        setUpMockUserStoreRepository()
        setUpMockAddressRepository()
        userService = UserService(
            _mockedUserRepository,
            _mockedAddressService,
            _mockedUserStoreRepository
        )
    }

    @Test
    fun `should validate if user model is created`() {
        val userRequest = getValidUser()
        val result: User = userService.create(userRequest)
        assertNotNull(result)
    }

    @Test
    fun `throws IllegalArgumentException when an user is NOT created `() {
        val userRequest = getInvalidUser()
        assertThrows<IllegalArgumentException> {
            userService.create(userRequest)
        }
        verify(exactly = 0) { _mockedUserStoreRepository.create(any()) }
        verify(exactly = 0) { _mockedUserRepository.create(any()) }
        verify(exactly = 0) { _mockedAddressService.create(any(), any()) }
    }

    @Test
    fun `should validate if User model is returned when updated`() {
        val userRequest = getValidUser()
        val result = userService.updateUser(userRequest)

        assertNotNull(result)
    }

    @Test
    fun `throws IllegalArgumentException when update request is NOT valid`() {
        val userRequest = getInvalidUser()
        verify(exactly = 0) { _mockedAddressService.updateAddress(any()) }
        verify(exactly = 0) { _mockedUserRepository.update(any()) }
        assertThrows<IllegalArgumentException> {
            userService.updateUser(userRequest)
        }
    }

    @Test
    fun `should return user when selected by id`() {
        val userId = UUID.randomUUID().toString()
        val result = userService.getUserById(userId)
        assertNotNull(result)
    }

    @Test
    fun `throws exception when user selected by id is null`() {
        val userId = UUID.randomUUID().toString()
        every { _mockedUserRepository.getById(any()) } returns null
        assertThrows<IllegalArgumentException> {
            userService.getUserById(userId)
        }
    }

    @Test
    fun `should select all users`() {
        val users: List<User> = (0..4).map { getValidUser() }
        every { _mockedUserRepository.getAll(any(), any()) } returns users
        val result: Collection<User> = userService.getAllUsers(0, users.size)
        assertEquals(users.size, result.size, "Cannot select all users")
    }

    private fun setUpMockUserRepository() {
        every { _mockedUserRepository.create(any()) } returns getValidUser()
        every { _mockedUserRepository.update(any()) } returns getValidUser()
        every { _mockedUserRepository.getById(any()) } returns getValidUser()
        every { _mockedUserRepository.delete(any()) } just Runs
    }

    private fun setUpMockUserStoreRepository() {
        every { _mockedUserStoreRepository.create(any()) } just Runs
        every { _mockedUserStoreRepository.update(any()) } just Runs
    }

    private fun setUpMockAddressRepository() {
        every { _mockedAddressService.create(any(), any()) } returns AddressFixture.getValidAddress()
        every { _mockedAddressService.deleteByUserId(any()) } just Runs
        every { _mockedAddressService.updateAddress(any()) } returns AddressFixture.getValidAddress()
    }

}
