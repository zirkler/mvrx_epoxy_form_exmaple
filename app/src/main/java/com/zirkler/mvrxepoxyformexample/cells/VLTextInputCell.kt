package com.zirkler.mvrxepoxyformexample.cells

import android.content.Context
import android.graphics.Typeface
import android.text.InputType
import android.text.Spanned
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.*
import com.jakewharton.rxbinding3.widget.textChanges
import com.zirkler.mvrxepoxyformexample.R
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.cell_text_input.view.*
import java.util.concurrent.TimeUnit

typealias VLTextChangeCallback = ((CharSequence?) -> Unit)

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class VLTextInputCell@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var textChangeDisposable: Disposable? = null

    init {
        inflate(context, R.layout.cell_text_input, this)
    }

    @ModelProp
    fun setTextInput(text: CharSequence?) {
        if (setTextOnlyIfDifferent(editText, text)) {
            // If the text changed then we move the cursor to the end of the new text.
            // This allows us to fill in text programmatically if needed,
            // like a search suggestion, but if the user is typing and the view is rebound
            // we won't lose their cursor position.
            editText.setSelection(editText.length())
        }
    }

    var _textchangeCallback: VLTextChangeCallback? = null
    @CallbackProp
    fun textChangeCallback(callback: VLTextChangeCallback?) {
        _textchangeCallback = callback
        this.textChangeDisposable = this
            .editText
            .textChanges()
            //.debounce(500, TimeUnit.MILLISECONDS)
            .subscribe {
                _textchangeCallback?.invoke(it.toString())
            }
    }

    enum class VLTextInputType {
        PW, EMAIL, TEXT
    }

    @TextProp
    fun setPlaceholder(placeholder: CharSequence) {
        editText.hint = placeholder
    }

    @ModelProp
    fun setInputType(inputType: VLTextInputType) {
        editText.inputType = when (inputType) {
            VLTextInputType.PW -> InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            VLTextInputType.EMAIL -> InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            VLTextInputType.TEXT -> InputType.TYPE_CLASS_TEXT
        }
        editText.typeface = Typeface.SANS_SERIF
    }

    @OnViewRecycled
    fun onRecycle() {
        _textchangeCallback = null
        textChangeDisposable?.dispose()
        textChangeDisposable = null
    }

    /**
     * @return True if the TextView's text got updated, otherwise false.
     */
    private fun setTextOnlyIfDifferent(textView: TextView, text: CharSequence?): Boolean {
        if (!isTextDifferent(text, textView.text)) {
            // Previous text is the same. No op.
            return false
        }

        textView.text = text ?: ""
        return true
    }

    /**
     * @return True if str1 is different from str2.
     *
     *
     * This is adapted from how the Android DataBinding library binds its text views.
     */
    private fun isTextDifferent(str1: CharSequence?, str2: CharSequence?): Boolean {
        if (str1 === str2) {
            return false
        }
        if (str1 == null || str2 == null) {
            return true
        }
        val length = str1.length
        if (length != str2.length) {
            return true
        }

        if (str1 is Spanned) {
            return str1 != str2
        }

        for (i in 0 until length) {
            if (str1[i] != str2[i]) {
                return true
            }
        }
        return false
    }
}