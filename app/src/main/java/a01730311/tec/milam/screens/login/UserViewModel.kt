package a01730311.tec.milam.screens.login

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import a01730311.tec.milam.R
import a01730311.tec.milam.components.Profile
import java.util.*
import kotlin.collections.HashMap

class UserViewModel: ViewModel() {

    var userChosen : String = ""
    var avatarChosen : Int = 1
    private lateinit var profile: Profile
    private lateinit var profilesPreferences : SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val profiles: HashMap<String, Profile> = HashMap()
    private var profilesSet: MutableSet<String> = mutableSetOf()
    private var identifiers: MutableSet<String> = mutableSetOf()



    // GETTERS
    fun getUsername():String {
        return profile.username
    }

    fun getIconID():Int {
        return profile.iconID
    }

    fun getProfiles(): List<Profile> {
        return profiles.values.toList()
    }

    fun getID():String {
        return profile.id
    }

    // get users from shared preferences
    fun setSharedPreferences(activity: FragmentActivity?) {
        profilesPreferences = activity?.getPreferences(Context.MODE_PRIVATE)!!
        identifiers = profilesPreferences.getStringSet("profiles", identifiers) as MutableSet<String>

        for (id in identifiers) {
            val username = profilesPreferences.getString(id + "_username","")!!
            val newProfile = Profile(id, username, profilesPreferences.getInt(id + "_avatar", R.drawable.pikachu))

            profiles[id] = newProfile
            profilesSet.add(username)
        }
    }

    // checks if the user is not already stored
    fun isValidUsername(user: String):Boolean {
        return !profilesSet.contains(user)
    }



    fun editAvatar(newAvatarID: Int) {
        edit()

        profile.iconID = newAvatarID
        editor.putInt(profile.id + "_avatar", newAvatarID)

        editor.apply()
    }


    fun editUsername(newUsername: String) {
        edit()

        profilesSet.remove(profile.username)

        // saving new username
        editor.putString(profile.id + "_username", newUsername)
        profile.username = newUsername
        profilesSet.add(newUsername)

        editor.apply()
    }


    fun signup() {
        edit()

        val id = saveID()
        saveUsername(id)
        saveAvatar(id)
        profile = Profile(id, userChosen, avatarChosen)

        editor.apply()
    }

    fun login(id: String) {
        profile = profiles[id]!!
    }

    fun logout() {
        profile = Profile("","",0)
    }

    fun deleteProfile() {
        edit()

        editor.remove(profile.id + "_username")
        profilesSet.remove(profile.username)
        identifiers.remove(profile.id)
        profiles.remove(profile.id)
        editor.putStringSet("profiles", identifiers)

        editor.apply()
        logout()
    }



    private fun edit() {
        editor = profilesPreferences.edit()
    }

    private fun saveID():String {
        val id = UUID.randomUUID().toString()
        identifiers.add(id)
        editor.putStringSet("profiles", identifiers)

        return id
    }

    private fun saveUsername(id: String) {
        editor.putString(id + "_username", userChosen)
        profilesSet.add(userChosen)
    }

    private fun saveAvatar(id: String) {
        editor.putInt(id + "_avatar", avatarChosen)
    }
}