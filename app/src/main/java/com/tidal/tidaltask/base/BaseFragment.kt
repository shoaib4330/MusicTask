package com.tidal.tidaltask.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.tidal.tidaltask.util.Constants
import com.tidal.tidaltask.views.CustomToolbar
import dagger.android.support.DaggerFragment

abstract class BaseFragment: DaggerFragment() {

    lateinit var fragmentHelper: FragmentNavigationHelper

    var toolbar: CustomToolbar? = null

    private var v: View? = null

    internal var toolbarTitle = ""

    private var dialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            fragmentHelper = context as FragmentNavigationHelper
            toolbar = fragmentHelper.getCustomToolbar()
        } catch (e: Exception) {
            //log(e.toString())
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(getLayoutId(), container, false)
        v!!.isClickable = true
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view, savedInstanceState)
    }

    fun getHelper(): FragmentNavigationHelper {
        return fragmentHelper
    }

    fun getParentView(): View? {
        return this.v
    }

    open fun backButtonVisibility() = View.VISIBLE

    abstract fun getLayoutId(): Int


    open fun initViews(parent: View, savedInstanceState: Bundle?) {

    }

    open fun getTitle(): String {
        return Constants.EMPTY_STRING
    }

    override fun onDestroyView() {
        super.onDestroyView()
        hideKeyboard()
    }

    fun onKeyDown(): Boolean {
        return false
    }

    protected fun hideKeyboard() {
        val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(getView()!!.windowToken, 0)
    }

    protected fun showKeyboard() {
        val inputMethodManager = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    protected fun hideKeyboard(input: EditText) {
        val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(input.windowToken, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    interface FragmentNavigationHelper {

        fun addFragment(f: BaseFragment, layoutId: Int, clearBackStack: Boolean, addToBackstack: Boolean)

        fun addFragment(f: BaseFragment, clearBackStack: Boolean, addToBackstack: Boolean)

        fun replaceFragment(f: BaseFragment, layoutId: Int, clearBackStack: Boolean, addToBackstack: Boolean)

        fun replaceFragment(f: BaseFragment, clearBackStack: Boolean, addToBackstack: Boolean)

        fun clearFragmentBackStack()

        fun updateToolbarTitle(title: String)

        fun showLoadingDialog()

        fun hideLoadingDialog()

        fun onBack()

        fun getCustomToolbar(): CustomToolbar
    }
}