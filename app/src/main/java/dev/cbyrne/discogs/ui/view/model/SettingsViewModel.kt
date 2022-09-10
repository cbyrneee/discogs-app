package dev.cbyrne.discogs.ui.view.model

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cbyrne.discogs.common.repository.credentials.CredentialsRepository
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val credentialsRepository: CredentialsRepository
) : ViewModel() {
    fun logout() {
        credentialsRepository.credentials = null
    }
}