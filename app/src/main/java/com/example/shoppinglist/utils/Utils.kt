package com.example.shoppinglist.utils

import android.content.res.Resources

object Utils {

    fun convertPxToDp(px: Float): Float {
        return (px / Resources.getSystem().displayMetrics.density)

    }

    fun convertDpToPx(dp: Float): Float {
        return (dp * Resources.getSystem().displayMetrics.density)
    }
}