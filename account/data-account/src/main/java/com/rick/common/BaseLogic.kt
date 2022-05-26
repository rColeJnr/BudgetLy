package com.rick.common


interface BaseLogic<EVENT> {

    fun onEvent(event: EVENT)

}