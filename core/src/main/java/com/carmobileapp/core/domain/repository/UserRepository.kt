package com.carmobileapp.core.domain.repository

import com.carmobileapp.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getCurrentUser(): User?
    suspend fun updateUser(user: User)
    suspend fun updateUserPreferences(preferences: com.carmobileapp.core.domain.model.UserPreferences)
    fun observeCurrentUser(): Flow<User?>
    suspend fun signOut()
}