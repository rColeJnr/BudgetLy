package com.rick.options

class NativeLib {

    /**
     * A native method that is implemented by the 'options' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'options' library on application startup.
        init {
            System.loadLibrary("options")
        }
    }
}