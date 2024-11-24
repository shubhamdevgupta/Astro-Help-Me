@file:JvmName("BindingUtil")

package com.androiddev.astrohelpme.utils.helper

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.androiddev.astrohelpme.R
import com.androiddev.astrohelpme.utils.extns.hide
import com.androiddev.astrohelpme.utils.extns.show
import com.androiddev.astrohelpme.utils.extns.textColor


@BindingAdapter("setColorFromStatus")
fun setTextColorWithStatus(textView: TextView, statusId: Int) {
    when (statusId) {
        1 -> textView.textColor(textView.context, R.color.green)
        2 -> textView.textColor(textView.context, R.color.red)
        3 -> textView.textColor(textView.context, R.color.purple_200)
    }
}

@BindingAdapter("setStatusTextColorFromString")
fun setStatusTextColorFromString(textView: TextView, statusId: String?) {
    statusId?.let {
        when (statusId) {
            "1" -> textView.let {
                it.textColor(it.context, R.color.green)
                it.text = "Success"
            }

            "2" -> textView.let {
                it.textColor(it.context, R.color.red)
                it.text = "Failed"
            }

            "3" -> textView.let {
                it.textColor(it.context, R.color.purple_200)
                it.text = "Pending"
            }

            else -> textView.let {
                it.text = "Unknown"
            }
        }
    } ?: run {
        textView.text = "Demo Order"
    }
}


@BindingAdapter("setUserType")
fun setUserType(textView: TextView, userType: String) {
    when (userType) {
        "6" -> textView.text = "Promoter"
        "5" -> textView.text = "Agent"
        "4" -> textView.text = "Master Distributor"
        else -> textView.text = "UnKnown"
    }
}

/*@InverseMethod("buttonToId")
fun idToButton(id: String): Int {
    return if (id == "1")
        R.id.rb_android
    else R.id.rb_ios
}

fun buttonToId(id: Int): String {
    return if (id == R.id.rb_android)
        "1"
    else "2"
}


@InverseMethod("buttonToProductCode")
fun productCodeToButton(id: String): Int {
    return if (id == "SOSCC")
        R.id.rb_premiun
    else R.id.rb_demo
}

fun buttonToProductCode(id: Int): String {
    return if (id == R.id.rb_premiun)
        "SOSCC"
    else "demo"
}*/

@BindingAdapter("setVisibilityFromLiveData")
fun setVisibilityFromLiveData(textView: TextView, data: LiveData<String>) {
    try {
        textView.text = data.value
        if (data.value == "") {
            textView.hide()
            textView.text = ""
        } else {
            textView.show()
        }
    } catch (e: Exception) {
        textView.hide()
    }

}

