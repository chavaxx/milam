package a01730311.tec.milam.screens.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import a01730311.tec.milam.R
import a01730311.tec.milam.screens.login.UserViewModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController


class registro_usuario : Fragment() {

    private var _binding: registro_usuario? = null

    private val binding get() = _binding!!
    private lateinit var usernameInput: EditText

    private var toastText: String = ""
    private val toastDuration: Int = Toast.LENGTH_SHORT

    private val viewModel: UserViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View =  inflater.inflate(R.layout.fragment_registro_usuario, container, false)

        setInputText(view)
        setNavigation(view)

        return view

    }

    private fun setNavigation(view: View) {
        val button: Button = view.findViewById(R.id.button_next)
        val backButton: Button = view.findViewById(R.id.button_back_icons)
        button.setOnClickListener{
            val username = usernameInput.text
            if (username.length > 1) {
                viewModel.setUsername(username.toString())
                if (viewModel.isValidUsername()) {
                    val action = registro_usuarioDirections.actionRegistroUsuarioToRegistroElegirIcono()
                    findNavController().navigate(action)
                } else {
                    toastText = "Intenta con un nombre distinto"
                    val toast = Toast.makeText(activity, toastText, toastDuration)
                    toast.show()
                }
            } else {
                toastText = "Introduce tu nombre con al menos 2 letras"
                val toast = Toast.makeText(activity, toastText, toastDuration)
                toast.show()
                usernameInput.requestFocus()
            }

        }
        backButton.setOnClickListener{
            findNavController().popBackStack()
        }
    }
    
    private fun setInputText(view: View ) {
        usernameInput = view.findViewById(R.id.username_input)
    }
}