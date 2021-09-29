package a01730311.tec.milam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [registro_usuario.newInstance] factory method to
 * create an instance of this fragment.
 */
class registro_usuario : Fragment() {

    private var _binding: registro_usuario? = null

    private val binding get() = _binding!!
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

        setNavigation(view)

        return view

    }

    private fun setNavigation(view: View) {
        val button: Button = view.findViewById(R.id.button_next)
        val backButton: Button = view.findViewById(R.id.button_back_icons)
        button.setOnClickListener{
            val action = registro_usuarioDirections.actionRegistroUsuarioToRegistroElegirIcono()
            findNavController().navigate(action)
        }
        backButton.setOnClickListener{
            findNavController().popBackStack()
        }
    }
    

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment registro_usuario.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            registro_usuario().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}