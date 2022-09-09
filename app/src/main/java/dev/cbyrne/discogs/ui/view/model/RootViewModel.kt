package dev.cbyrne.discogs.ui.view.model

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cbyrne.discogs.common.repository.user.UserRepository
import javax.inject.Inject

@HiltViewModel
class RootViewModel @Inject constructor(
    userRepository: UserRepository
) : ViewModel() {
    val credentials = userRepository.credentials
}
