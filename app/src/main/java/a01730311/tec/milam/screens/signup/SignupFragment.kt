package a01730311.tec.milam.screens.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import a01730311.tec.milam.R
import a01730311.tec.milam.components.MyToast
import a01730311.tec.milam.screens.login.UserViewModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController


class SignupFragment : Fragment() {


    private lateinit var usernameInput: EditText

    private lateinit var toast: MyToast
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

        toast = MyToast(activity)
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
                    val action = SignupFragmentDirections.actionRegistroUsuarioToRegistroElegirIcono()
                    findNavController().navigate(action)
                } else {
                    toast.showMessage("Intenta con un nombre distinto")
                }
            } else {
                toast.showMessage("Introduce tu nombre con al menos 2 letras")
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