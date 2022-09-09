package dev.cbyrne.discogs.feature.discover.view.model

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cbyrne.discogs.common.data.model.user.UserIdentity
import dev.cbyrne.discogs.common.repository.user.UserRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    val identity: UserIdentity
        get() = userRepository.identity ?: error("No identity")
}