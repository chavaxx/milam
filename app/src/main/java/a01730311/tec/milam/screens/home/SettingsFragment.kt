package a01730311.tec.milam.screens.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import a01730311.tec.milam.R
import a01730311.tec.milam.components.MyToast
import a01730311.tec.milam.screens.login.UserViewModel
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton


class SettingsFragment : Fragment() {


    private lateinit var goBackButton: LinearLayout
    private lateinit var logoutButton: LinearLayout
    private lateinit var editProfilePicture: FloatingActionButton
    private lateinit var usernameEditText: EditText
    private lateinit var avatarImageView: ImageView
    private lateinit var saveNewUsername: FloatingActionButton
    private val viewModel: UserViewModel by activityViewModels()
    private lateinit var toast: MyToast

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_settings, container, false)

        toast = MyToast(activity)
        setNavigation(view)
        setControls(view)
        return view
    }

    private fun setControls(view: View) {
        usernameEditText = view.findViewById(R.id.editUsername)
        usernameEditText.setText(viewModel.getUsername())
        avatarImageView = view.findViewById(R.id.avatarImageView)
        avatarImageView.setImageDrawable(ContextCompat.getDrawable(requireContext(), viewModel.getAvatarID()))
    }


    private fun setNavigation(view: View) {
        goBackButton = view.findViewById(R.id.goBackHomeFromSettings)
        editProfilePicture = view.findViewById(R.id.edit_profile_picture)
        saveNewUsername = view.findViewById(R.id.save_new_username)
        logoutButton = view.findViewById(R.id.logout)

        goBackButton.setOnClickListener{
            findNavController().popBackStack()
        }

        editProfilePicture.setOnClickListener {
            val action = SettingsFragmentDirections.actionSettingsFragmentToEditAvatarFragment()
            findNavController().navigate(action)
        }

        saveNewUsername.setOnClickListener {
            if (viewModel.isValidUsername(usernameEditText.text.toString())) {
                viewModel.edit()
                viewModel.editUsername(usernameEditText.text.toString())
                toast.showMessage("¡Listo! Tu nuevo nombre está listo :)")
            } else {
                toast.showMessage("Alguien más tiene ese nombre :(")
            }

        }

        logoutButton.setOnClickListener {
            viewModel.logout()
            val navOptions = NavOptions.Builder().setPopUpTo(findNavController().graph.id, true).build()
            val action = SettingsFragmentDirections.actionSettingsFragmentToLoginFragment()
            findNavController().navigate(action, navOptions)
        }
    }


}