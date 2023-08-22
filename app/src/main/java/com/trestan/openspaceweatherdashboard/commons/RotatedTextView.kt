package com.trestan.openspaceweatherdashboard.commons

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.withSave


class RotatedTextView: AppCompatTextView {
    private var textPaint = TextPaint()
    private var layout1: Layout? = null
    private var padLeft = 0
    private var padTop = 0
    constructor(context: Context): super(context)
    constructor(context: Context, attributeSet: AttributeSet?): super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, int: Int): super(context, attributeSet, int)

    override fun setText(text: CharSequence, type: BufferType) {
        super.setText(text, type)
        layout1 = null
    }

    override fun setTextSize(size: Float) {
        super.setTextSize(size)
        textPaint.textSize = size  // sp
    }

    override fun setTextSize(unit: Int, size: Float) {
        super.setTextSize(unit, size)
        textPaint.textSize = size  // sp
    }

    private fun makeLayout(): Layout {
        if (layout1 == null) {
            textPaint.textAlign = Paint.Align.LEFT
            textPaint.drawableState = drawableState
            textPaint.textSize = textSize
            textPaint.color = Color.Gray.toArgb()

            layout1 = StaticLayout.Builder.obtain(
                text,
                0,
                text.length,
                textPaint,
                height
            ).build()
            padLeft = compoundPaddingLeft
            padTop = extendedPaddingTop
        }
        return layout1!!
    }

    override fun onDraw(canvas: Canvas) {
        if (layout == null) return
        canvas.withSave {
            translate(0f, height.toFloat())
            rotate(-90f)
            translate(padLeft.toFloat(), padTop.toFloat())
            makeLayout().draw(this)
        }
    }
}