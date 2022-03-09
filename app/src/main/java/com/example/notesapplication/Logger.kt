package com.example.notesapplication

import android.util.Log

object Logger {
    val TAG:String="Main"
    fun logInfo(msg:String)
    {
        Log.i(TAG,msg)
    }
    fun logDebug(msg:String)
    {
        Log.d(TAG,msg)
    }
    fun logWarn(msg:String)
    {
        Log.w(TAG,msg)
    }
    fun logError(msg:String)
    {
        Log.e(TAG,msg)
    }
    fun logAssert(msg:String)
    {
        Log.wtf(TAG,msg)
    }

}