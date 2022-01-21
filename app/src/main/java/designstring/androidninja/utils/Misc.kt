package designstring.androidninja.utils

import android.content.Context
import android.util.Log
import android.widget.Toast

private val TAG = "AndroidNinja"

fun debugLog(message: String){
    Log.d(TAG, message)
}

suspend fun tryIt(f: suspend () -> Unit, context: Context? = null){
    try {
        f()
    } catch (e: Exception){
        debugLog("Error: ${e.message}")
        context?.let { Toast.makeText(it, "Error: ${e.message}", Toast.LENGTH_SHORT).show() }
    }
}