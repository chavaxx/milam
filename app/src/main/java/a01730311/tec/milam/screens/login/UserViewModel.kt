package a01730311.tec.milam.screens.login

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import a01730311.tec.milam.R
import a01730311.tec.milam.components.Profile

class UserViewModel: ViewModel() {

    private lateinit var username:String
    private var avatarID:Int = 0
    private lateinit var profilesPreferences : SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val profiles: MutableList<Profile> = mutableListOf()
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

    fun getProfiles():MutableList<Profile> {
        return profiles
    }

    fun setSharedPreferences(activity: FragmentActivity?) {
        profilesPreferences = activity?.getPreferences(Context.MODE_PRIVATE)!!
        profilesSet = profilesPreferences.getStringSet("profiles", profilesSet) as MutableSet<String>
        for (user in profilesSet) {
            val newProfile = Profile(user, profilesPreferences.getInt(user, R.drawable.pikachu))
            if (!profiles.contains(newProfile))
                profiles.add(newProfile)
        }
    }

    fun isValidUsername():Boolean {
        return !profilesSet.contains(username)
    }

    fun saveAvatar(newAvatarID: Int) {
        setAvatarID(newAvatarID)
        editor.putInt(username, newAvatarID)
        editor.apply()
    }

    fun edit() {
        editor = profilesPreferences.edit()
    }

    fun saveProfile() {
        profilesSet.add(username)
        saveAvatar(avatarID)
        editor.putStringSet("profiles", profilesSet)
        editor.apply()
    }

}