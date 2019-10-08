package com.zirkler.mvrxepoxyformexample.DemoForm

import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.zirkler.mvrxepoxyformexample.arch_core.VLRecyclerViewBaseFragment
import com.zirkler.mvrxepoxyformexample.arch_core.simpleController
import com.zirkler.mvrxepoxyformexample.cells.VLTextInputCell
import com.zirkler.mvrxepoxyformexample.cells.VLTextInputType
import com.zirkler.mvrxepoxyformexample.cells.vLTextInputCell
import com.zirkler.mvrxepoxyformexample.cells.vLTextLabelCell

class DemoFormFragment: VLRecyclerViewBaseFragment() {

    val viewModel: DemoFormViewModel by fragmentViewModel()

    override fun epoxyController() = simpleController {
        withState(viewModel) { state ->

            vLTextLabelCell {
                id("state_dump")
                text("\n\n$state\n\n")
            }

            vLTextLabelCell {
                id("firstname_label")
                text("Firstname:")
            }
            vLTextInputCell {
                id("firstname")
                placeholder("Firstname")
                inputType(VLTextInputType.TEXT)
                textInput(state.firstname)
                textChangeCallback { viewModel.setFirstname(it) }
            }

            vLTextLabelCell {
                id("lastname_label")
                text("Lastname:")
            }
            vLTextInputCell {
                id("lastname")
                placeholder("Lastname")
                inputType(VLTextInputType.TEXT)
                textInput(state.lastname)
                textChangeCallback { viewModel.setLastname(it) }
            }

            vLTextLabelCell {
                id("lorem_ipsum_1")
                text("\n\nLorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.\n\n")
            }

            vLTextLabelCell {
                id("company_label")
                text("Company:")
            }
            vLTextInputCell {
                id("company")
                placeholder("Company:")
                inputType(VLTextInputType.TEXT)
                textInput(state.company)
                textChangeCallback { viewModel.setCompany(it) }
            }

            vLTextLabelCell {
                id("position_label")
                text("Position:")
            }
            vLTextInputCell {
                id("position")
                placeholder("Position:")
                inputType(VLTextInputType.TEXT)
                textInput(state.position)
                textChangeCallback { viewModel.setPosition(it) }
            }

            vLTextLabelCell {
                id("email_label")
                text("Email:")
            }
            vLTextInputCell {
                id("email")
                placeholder("Email:")
                inputType(VLTextInputType.EMAIL)
                textInput(state.email)
                textChangeCallback { viewModel.setEmail(it) }
            }

            vLTextLabelCell {
                id("repeat_email_label")
                text("Repeat Email:")
            }
            vLTextInputCell {
                id("repeat_email")
                placeholder("Email:")
                inputType(VLTextInputType.EMAIL)
                textInput(state.email2)
                textChangeCallback { viewModel.setEmail2(it) }
            }

            vLTextLabelCell {
                id("lorem_ipsum_2")
                text("\n\nLorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.\n\n")
            }

            vLTextLabelCell {
                id("password_label")
                text("Password:")
            }
            vLTextInputCell {
                id("password")
                placeholder("Password")
                inputType(VLTextInputType.PW)
                textInput(state.password)
                textChangeCallback { viewModel.setPassword(it) }
            }

            vLTextLabelCell {
                id("password_repeat_label")
                text("Repeat Password:")
            }
            vLTextInputCell {
                id("repeat_password")
                placeholder("Password:")
                inputType(VLTextInputType.PW)
                textInput(state.password2)
                textChangeCallback { viewModel.setPassword2(it) }
            }
        }
    }
}