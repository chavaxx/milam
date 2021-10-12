package a01730311.tec.milam.components

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentActivity

class MyToast(private val activity: Context?) {
    private val toastDuration: Int = Toast.LENGTH_SHORT
    private lateinit var instance: Toast

    fun showMessage(message: String) {
        instance = Toast.makeText(activity, message, toastDuration)
        instance.show()
    }
}