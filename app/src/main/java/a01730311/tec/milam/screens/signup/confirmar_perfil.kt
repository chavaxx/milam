package a01730311.tec.milam.screens.signup

import android.graphics.Color
import android.os.Bundle
import a01730311.tec.milam.R
import a01730311.tec.milam.screens.login.UserViewModel
import android.media.Image
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [confirmar_perfil.newInstance] factory method to
 * create an instance of this fragment.
 */
class confirmar_perfil : Fragment() {


    private val viewModel: UserViewModel by activityViewModels()
    private lateinit var avatar: ImageView;
    private lateinit var username: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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
            viewModel.saveProfile()
            val action = confirmar_perfilDirections.actionConfirmarPerfilToHomeFragment()
            findNavController().navigate(action)
            WindowCompat.setDecorFitsSystemWindows(requireActivity().window, true)
        }
        backButton.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    private fun setProfileInfo(view: View) {
        avatar = view.findViewById(R.id.confirm_profile_avatar)
        username = view.findViewById(R.id.confirm_profile_username)
        val selectedAvatar = viewModel.getAvatarID()
        avatar.setImageDrawable(ContextCompat.getDrawable(requireContext(), selectedAvatar))
        username.text = viewModel.getUsername()
    }

}

