package com.zirkler.mvrxepoxyformexample.DemoForm

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext


data class DemoFormState(
    val firstname: CharSequence? = null,
    val lastname: CharSequence? = null,
    val company: CharSequence? = null,
    val position: CharSequence? = null,
    val email: CharSequence? = null,
    val email2: CharSequence? = null,
    val password: CharSequence? = null,
    val password2: CharSequence? = null
): MvRxState

class DemoFormViewModel(
    state: DemoFormState
) : BaseMvRxViewModel<DemoFormState>(state, debugMode = true) {

    fun setFirstname(newFirstname: CharSequence?) { setState { copy(firstname = newFirstname) } }
    fun setLastname(newLastname: CharSequence?) { setState { copy(lastname = newLastname) } }
    fun setCompany(newCompany: CharSequence?) { setState { copy(company = newCompany) } }
    fun setPosition(newPosition: CharSequence?) { setState { copy(position = newPosition) } }
    fun setEmail(newEmail: CharSequence?) { setState { copy(email = newEmail) } }
    fun setEmail2(newEmail2: CharSequence?) { setState { copy(email2 = newEmail2) } }
    fun setPassword(newPassword: CharSequence?) { setState { copy(password = newPassword) } }
    fun setPassword2(newPassword2: CharSequence?) { setState { copy(password2 = newPassword2) } }

    companion object :
        MvRxViewModelFactory<DemoFormViewModel, DemoFormState> {
        override fun create(viewModelContext: ViewModelContext, state: DemoFormState): DemoFormViewModel? {
            return DemoFormViewModel(
                state = state
            )
        }
    }
}