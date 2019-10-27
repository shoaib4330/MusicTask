package com.tidal.tidaltask.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.InflateException
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.AttrRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.tidal.tidaltask.R
import kotlinx.android.synthetic.main.layout_custom_toolbar.view.*

class CustomToolbar : ConstraintLayout {
    lateinit var mView: View

    constructor(context: Context) : super(context) {
        initLayout()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initLayout()
    }

    constructor(context: Context, attrs: AttributeSet?,
                @AttrRes defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    private fun initLayout() {
//        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        try {
//            mView = inflater.inflate(R.layout.layout_custom_toolbar, this)
//        } catch (e: InflateException) {
//            Log.e("view-error", e.toString())
//        }
//        bindViews()
    }

    private fun bindViews() {
        //ivBack.setImageDrawable(Util.getBackButton(ivBack.context))
    }

    fun setTitle(title: String) {
        //tvTitle.text = title
    }

    fun setRightText(rightText: String, color: Int) {
//        if (tvRight.visibility != View.VISIBLE) {
//            tvRight.visibility = View.VISIBLE
//            hideRightLoading()
//        }
        //tvRight.text = rightText
        //tvRight.setTextColor(color)
    }

    fun showRightText() {
        //tvRight.visibility = View.VISIBLE
    }

    fun hideRightText() {
        //tvRight.visibility = View.GONE
    }

    fun showBackButton() {
        //ivBack.visibility = View.VISIBLE
    }

    fun hideBackButton() {
        //ivBack.visibility = View.GONE
    }

    fun setBackButton(drawable: Drawable?) {
        //ivBack.setImageDrawable(drawable)
    }

    fun setBackButtonClickListener(onClickListener: OnClickListener) {
        showBackButton()
        //ivBack.setOnClickListener(onClickListener)
    }

    fun showRightLoading() {
        //.visibility = View.VISIBLE
    }

    fun hideRightLoading() {
        //pbRightLoading.visibility = View.GONE
    }
}
