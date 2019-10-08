package com.zirkler.mvrxepoxyformexample.cells

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.zirkler.mvrxepoxyformexample.R
import kotlinx.android.synthetic.main.cell_text_label.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class VLTextLabelCell@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.cell_text_label, this)
        orientation = VERTICAL
    }

    @TextProp
    fun setText(text: CharSequence) {
        this.label.text = text
    }
}
