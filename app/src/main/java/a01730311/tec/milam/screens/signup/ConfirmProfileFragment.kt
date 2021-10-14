package a01730311.tec.milam.screens.signup

import android.os.Bundle
import a01730311.tec.milam.R
import a01730311.tec.milam.screens.login.UserViewModel
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController


class ConfirmProfileFragment : Fragment() {


    private val viewModel: UserViewModel by activityViewModels()
    private lateinit var avatar: ImageView
    private lateinit var username: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View =  inflater.inflate(R.layout.fragment_confirmar_perfil, container, false)

        setNavigation(view)
        setProfileInfo(view)

        return view

    }

    private fun setNavigation(view: View) {
        val button: Button = view.findViewById(R.id.button_confirm_profile)
        val backButton: Button = view.findViewById(R.id.button_back_icons)
        button.setOnClickListener{
            viewModel.signup()
            val navOptions = NavOptions.Builder().setPopUpTo(findNavController().graph.startDestination, true).build()
            val action = ConfirmProfileFragmentDirections.actionConfirmarPerfilToHomeFragment()
            findNavController().navigate(action, navOptions)
        }
        backButton.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    private fun setProfileInfo(view: View) {
        avatar = view.findViewById(R.id.confirm_profile_avatar)
        username = view.findViewById(R.id.confirm_profile_username)
        val selectedAvatar = viewModel.avatarChosen
        avatar.setImageDrawable(ContextCompat.getDrawable(requireContext(), selectedAvatar))
        username.text = viewModel.userChosen
    }

}

