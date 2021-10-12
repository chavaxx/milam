package a01730311.tec.milam.screens.signup

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel

class SignupViewModel:ViewModel() {

    private lateinit var username:String
    private var avatarID:Int = 0
    private lateinit var profiles : SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private var profilesSet: MutableSet<String> = mutableSetOf()

    fun setUsername(newUsername: String) {
        username = newUsername
    }

    fun setAvatarID(newAvatarID: Int) {
        avatarID = newAvatarID
    }

    fun getUsername():String {
        return username
    }

    fun getAvatarID():Int {
        return avatarID
    }

    fun setSharedPreferences(activity: FragmentActivity?) {
        profiles = activity?.getPreferences(Context.MODE_PRIVATE)!!
        profilesSet = profiles.getStringSet("profiles", profilesSet) as MutableSet<String>
    }

    fun isValidUsername():Boolean {
        return !profilesSet.contains(username)
    }

    fun saveProfile() {

        editor = profiles.edit()
        profilesSet.add(username)
        editor.putInt(username, avatarID)
        editor.putStringSet("profiles", profilesSet)
        editor.apply()
    }


}