package com.project.tickr.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.tickr.core.result.AppError
import com.project.tickr.core.result.onError
import com.project.tickr.core.result.onSuccess
import com.project.tickr.domain.model.Profile
import com.project.tickr.domain.usecase.auth.RegisterUseCase
import com.project.tickr.domain.usecase.auth.ValidateEmailUseCase
import com.project.tickr.domain.usecase.auth.ValidateNameUseCase
import com.project.tickr.domain.usecase.auth.ValidatePasswordUseCase
import com.project.tickr.core.validation.ValidationResult
import com.project.tickr.domain.usecase.profile.UpsertProfileUseCase
import com.project.tickr.presentation.common.AuthErrorStore
import com.project.tickr.presentation.common.toUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Clock

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase,
    private val validateEmail: ValidateEmailUseCase,
    private val validatePassword: ValidatePasswordUseCase,
    private val validateName: ValidateNameUseCase,
    private val authErrorStore: AuthErrorStore,
    private val upsertProfile: UpsertProfileUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterUiState())
    val state: StateFlow<RegisterUiState> = _state.asStateFlow()

    private val _events = Channel<RegisterEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    fun onAction(action: RegisterAction) {
        when (action) {
            is RegisterAction.FullNameChanged -> {
                _state.update { it.copy(fullName = action.value, fullNameError = null) }
            }
            is RegisterAction.EmailChanged -> {
                _state.update { it.copy(email = action.value, emailError = null) }
            }
            is RegisterAction.PasswordChanged -> {
                val result = validatePassword(action.value)
                _state.update {
                    it.copy(
                        password = action.value,
                        passwordStrength = result.strength,
                        passwordRequirements = result.requirements,
                    )
                }
            }
            RegisterAction.TogglePasswordVisibility -> {
                _state.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
            }
            RegisterAction.Submit -> submit()
            RegisterAction.NavigateToLogin -> {
                viewModelScope.launch { _events.send(RegisterEvent.NavigateToLogin) }
            }
            RegisterAction.GoogleClicked -> {
                // TODO(user): implementasi Google/OAuth (Supabase) — jangan panggil apa pun
            }
        }
    }

    private fun submit() {
        val s = _state.value
        var hasError = false

        val nameResult = validateName(s.fullName)
        if (nameResult is ValidationResult.Invalid) {
            _state.update { it.copy(fullNameError = nameResult.reason.toUiText()) }
            hasError = true
        }

        val emailResult = validateEmail(s.email)
        if (emailResult is ValidationResult.Invalid) {
            _state.update { it.copy(emailError = emailResult.reason.toUiText()) }
            hasError = true
        }

        if (hasError) return

        _state.update { it.copy(isLoading = true, fullNameError = null, emailError = null) }

        viewModelScope.launch {
            registerUseCase(s.fullName, s.email, s.password)
                .onSuccess { session ->
                    // Create profile row in Supabase when session is available
                    // (email confirmation OFF → userId is non-empty after signUp)
                    if (session.userId.isNotEmpty()) {
                        upsertProfile(
                            Profile(
                                id = session.userId,
                                fullName = session.fullName,
                                updatedAt = Clock.System.now().toString(),
                            )
                        )
                    }
                    _state.update { it.copy(isLoading = false) }
                    _events.send(RegisterEvent.NavigateToSuccess)
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }
                    when {
                        error is AppError.Validation && error.field == "email" -> {
                            _state.update { it.copy(emailError = error.reason.toUiText()) }
                        }
                        else -> {
                            authErrorStore.lastRegisterError = mapError(error)
                            _events.send(RegisterEvent.NavigateToFailed)
                        }
                    }
                }
        }
    }

    private fun mapError(error: AppError): String = when (error) {
        is AppError.Network -> "Koneksi gagal. Periksa koneksi internet Anda."
        is AppError.Unauthorized -> "Email atau kata sandi tidak valid."
        is AppError.Validation -> error.reason
        is AppError.Unknown -> error.detail.ifBlank { "Terjadi kesalahan. Coba lagi." }
        else -> "Terjadi kesalahan. Coba lagi."
    }
}
