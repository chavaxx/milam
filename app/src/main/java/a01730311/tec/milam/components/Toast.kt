package a01730311.tec.milam.components

import android.content.Context
import android.widget.Toast

// general class to show toasts
class MyToast(private val activity: Context?) {
    private val toastDuration: Int = Toast.LENGTH_SHORT
    private lateinit var instance: Toast

    fun showMessage(message: String) {
        instance = Toast.makeText(activity, message, toastDuration)
        instance.show()
    }
}