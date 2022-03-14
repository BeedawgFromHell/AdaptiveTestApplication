package com.example.adaptivetestapplication.tools

import android.app.Activity
import android.view.WindowMetrics
import androidx.window.layout.WindowMetricsCalculator

object UiMetrics {
    fun getCurrentWidth(activity: Activity): Float {
        val metrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(activity)
        return metrics.bounds.width() / activity.resources.displayMetrics.density
    }
}