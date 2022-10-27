package ru.unit.core_utils.dimen.ktx

import android.content.Context
import android.util.TypedValue

fun Int.dp(context: Context): Int = if (this == 0) 0 else
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        context.resources.displayMetrics
    ).toInt()