package com.carmobileapp.core.data.repository

import com.carmobileapp.core.domain.model.User
import com.carmobileapp.core.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor() : UserRepository {
    
    private val _currentUser = MutableStateFlow<User?>(null)
    
    override suspend fun getCurrentUser(): User? {
        return _currentUser.value
    }
    
    override suspend fun updateUser(user: User) {
        _currentUser.value = user
    }
    
    override suspend fun updateUserPreferences(preferences: com.carmobileapp.core.domain.model.UserPreferences) {
        val currentUser = _currentUser.value
        if (currentUser != null) {
            _currentUser.value = currentUser.copy(preferences = preferences)
        }
    }
    
    override fun observeCurrentUser(): Flow<User?> {
        return _currentUser.asStateFlow()
    }
    
    override suspend fun signOut() {
        _currentUser.value = null
    }
}