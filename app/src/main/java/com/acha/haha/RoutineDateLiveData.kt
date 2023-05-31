package com.acha.haha

import androidx.lifecycle.MutableLiveData

class RoutineDateLiveData private constructor(){

    private var currentProgress : MutableLiveData<String> = MutableLiveData()

    fun getLiveProgress() : MutableLiveData<String> {
        return currentProgress
    }

    companion object {
        private val mInstance : RoutineDateLiveData =
            RoutineDateLiveData()

        fun getInstance() : RoutineDateLiveData {
            return mInstance
        }
    }
}