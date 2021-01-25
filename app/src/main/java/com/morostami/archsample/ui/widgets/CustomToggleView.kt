package com.morostami.archsample.ui.widgets

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import com.morostami.archsample.R

class CustomToggleView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : LinearLayout(context, attrs) {

    init {
        initView(context, attrs, defStyle)
    }

    var tvStartTitle: TextView? = null
    var tvEndTitle: TextView? = null
    var imgEndIcon: ImageView? = null

    private var selectedIcon: Drawable? = null
    private var deselectedIcon: Drawable? = null

    var startTitle: String = ""
        set(value) {
            field = value
            updateStartTitle()
        }
    var endTitle: String = ""
        set(value) {
            field = value
            updateEndTitle()
        }
    var showEndTitle: Boolean = false
        set(value) {
            field = value
            updateEndTitle()
        }
    var showEndImage: Boolean = true
        set(value) {
            field = value
            updateSelectionImage()
        }
    @DrawableRes var selectedIconRes: Int = R.drawable.ic_empty_circle
    @DrawableRes var deselectedIconRes: Int = R.drawable.ic_checked_circle

    var isChecked = false
        set(value){
            field = value
            isSelected = value
            updateSelectionImage()
        }
    @StyleRes var startTitleStyle: Int = R.style.Widget_Toggle_Option_Start_Title_Style
        set(value) {
            field = value
            updateStartTitle()
        }
    @StyleRes var endTitleStyle: Int = R.style.Widget_Toggle_Option_End_Title_Style
        set(value) {
            field = value
            updateEndTitle()
        }

    private fun initView(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.CustomToggleView)
        startTitle = a.getString(R.styleable.CustomToggleView_ctv_start_title) ?: ""
        startTitleStyle = a.getResourceId(R.styleable.CustomToggleView_ctv_start_title_style, R.style.Widget_Toggle_Option_Start_Title_Style)
        endTitle = a.getString(R.styleable.CustomToggleView_ctv_end_title) ?: ""
        endTitleStyle = a.getResourceId(R.styleable.CustomToggleView_ctv_end_title_style, R.style.Widget_Toggle_Option_End_Title_Style)
        showEndTitle = a.getBoolean(R.styleable.CustomToggleView_ctv_show_end_title, false)
        showEndImage = a.getBoolean(R.styleable.CustomToggleView_ctv_show_end_image, true)
        isChecked = a.getBoolean(R.styleable.CustomToggleView_ctv_is_selected, false)
        selectedIcon = a.getDrawable(R.styleable.CustomToggleView_ctv_selected_drawable) ?:
        ContextCompat.getDrawable(context, R.drawable.ic_empty_circle)
        deselectedIcon = a.getDrawable(R.styleable.CustomToggleView_ctv_deselected_drawable) ?:
        ContextCompat.getDrawable(context ,R.drawable.ic_checked_circle)
        initializeView(context)
        a.recycle()
    }

    private fun initializeView(context: Context) {
        val parent: View = LayoutInflater.from(context).inflate(R.layout.view_custom_toggle, this)
        tvStartTitle = parent.findViewById(R.id.tvStartTitle)
        tvEndTitle = parent.findViewById(R.id.tvEndTitle)
        imgEndIcon = parent.findViewById(R.id.ivEndIcon)
        setValues()
    }

    private fun setValues() {
        updateStartTitle()
        updateEndTitle()
        updateSelectionImage()
    }

    private fun updateStartTitle() {
        tvStartTitle?.apply {
            text = startTitle
            if (startTitleStyle != -1) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setTextAppearance(startTitleStyle)
                } else {
                    setTextAppearance(context, startTitleStyle)
                }
            }
        }
    }

    private fun updateEndTitle() {
        tvEndTitle?.apply {

            text = endTitle
            if (endTitleStyle != -1) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setTextAppearance(endTitleStyle)
                } else {
                    setTextAppearance(context, endTitleStyle)
                }
            }
            visibility = if (showEndTitle)
                View.VISIBLE
            else
                View.GONE

        }
    }

    private fun updateSelectionImage() {
        imgEndIcon?.apply {

            setImageDrawable(deselectedIcon)
            if (showEndImage)
                visibility = View.VISIBLE
            else
                visibility = View.INVISIBLE

            if (isChecked) {
                setImageDrawable(selectedIcon)
            } else {
                setImageDrawable(deselectedIcon)
            }
        }
    }
}