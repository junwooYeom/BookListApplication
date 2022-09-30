package com.junwooyeom.booklistapplication

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard() {
    val imm = this.requireActivity().getSystemService(android.app.Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    view?.let {
        imm.hideSoftInputFromWindow(it.windowToken, 0)
    }
}
