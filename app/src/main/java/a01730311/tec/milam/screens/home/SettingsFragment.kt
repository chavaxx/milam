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
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.w3c.dom.Text


class SettingsFragment : Fragment() {


    private lateinit var goBackButton: LinearLayout
    private lateinit var goCreditsButton: TextView
    private lateinit var logoutButton: MaterialCardView
    private lateinit var deleteProfileButton: MaterialCardView
    private lateinit var avatarLayout: MaterialCardView
    private lateinit var editProfilePicture: FloatingActionButton
    private lateinit var usernameEditText: TextInputEditText
    private lateinit var editTextLayout: TextInputLayout
    private lateinit var avatarImageView: ImageView
    private val viewModel: UserViewModel by activityViewModels()
    private lateinit var toast: MyToast

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_settings, container, false)

        toast = MyToast(activity)

        setControls(view)
        setNavigation(view)
        return view
    }


    // binds interactions
    private fun setControls(view: View) {
        usernameEditText = view.findViewById(R.id.edit_username)
        editTextLayout = view.findViewById(R.id.text_field_user)
        avatarImageView = view.findViewById(R.id.avatarImageView)
        avatarImageView.setImageDrawable(ContextCompat.getDrawable(requireContext(), viewModel.getIconID()))
        avatarLayout = view.findViewById(R.id.avatarLayout)
    }


    private fun setNavigation(view: View) {
        goBackButton = view.findViewById(R.id.goBackHomeFromSettings)

        usernameEditText.setText(viewModel.getUsername())
        editProfilePicture = view.findViewById(R.id.edit_profile_picture)
        logoutButton = view.findViewById(R.id.logout_account_button)
        deleteProfileButton = view.findViewById(R.id.delete_account_button)
        goCreditsButton = view.findViewById(R.id.creditsLabel)

        goCreditsButton.setOnClickListener{
            val action = SettingsFragmentDirections.actionSettingsFragmentToCreditsFragment()
            findNavController().navigate(action)
        }

        goBackButton.setOnClickListener{
            findNavController().popBackStack()
        }

        editProfilePicture.setOnClickListener {
            val action = SettingsFragmentDirections.actionSettingsFragmentToEditAvatarFragment()
            findNavController().navigate(action)
        }

        deleteProfileButton.setOnClickListener {
            // asks to the user if it's sure about deleting profile
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Eliminar perfil ")
                .setMessage("Tus datos ser??n eliminados")
                .setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton("Aceptar") { dialog, _ ->
                    dialog.dismiss()
                    viewModel.deleteProfile()
                    val navOptions = NavOptions.Builder().setPopUpTo(findNavController().graph.id, true).build()
                    val action = SettingsFragmentDirections.actionSettingsFragmentToLoginFragment()
                    findNavController().navigate(action, navOptions)
                }
                .show()

        }

        avatarLayout.setOnClickListener {
            val action = SettingsFragmentDirections.actionSettingsFragmentToEditAvatarFragment()
            findNavController().navigate(action)
        }

        editTextLayout.setEndIconOnClickListener {
            if (viewModel.isValidUsername(usernameEditText.text.toString())) {
                viewModel.editUsername(usernameEditText.text.toString())
                toast.showMessage("??Listo! Tu nuevo nombre est?? listo :)")
            } else {
                toast.showMessage("Alguien m??s tiene ese nombre :(")
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