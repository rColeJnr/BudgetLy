package com.rick.core


interface BaseLogic<EVENT> {

    fun onEvent(event: EVENT)

}