package a01730311.tec.milam.screens.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import a01730311.tec.milam.R
import a01730311.tec.milam.adapter.AvatarAdapter
import a01730311.tec.milam.components.Profile
import a01730311.tec.milam.data.Datasource
import a01730311.tec.milam.screens.login.UserViewModel
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [registro_elegir_icono.newInstance] factory method to
 * create an instance of this fragment.
 */
class registro_elegir_icono : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var selectedAvatar: Int = -1
    private lateinit var myAvatars: List<Profile>

    private val viewModel: UserViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View =  inflater.inflate(R.layout.fragment_registro_elegir_icono, container, false)

        setNavigation(view)
        loadAvatars(view);

        return view

    }

    private fun loadAvatars(view: View) {


        myAvatars = Datasource().loadAvatars();

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewAvatars)

        recyclerView.adapter = AvatarAdapter(this, myAvatars, ::selectedAvatar)


        recyclerView.setHasFixedSize(true)
    }

    private fun setNavigation(view: View) {
        val button: Button = view.findViewById(R.id.button_next_confirm)
        val backButton: Button = view.findViewById(R.id.button_back_icons)
        button.setOnClickListener{
            if (selectedAvatar == -1) {
                val text = "Selecciona un icono de arriba"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(activity, text, duration)
                toast.show()
            } else {
                viewModel.setAvatarID(myAvatars[selectedAvatar].iconID)
                val action = registro_elegir_iconoDirections.actionRegistroElegirIconoToConfirmarPerfil()
                findNavController().navigate(action)
            }
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
         * @return A new instance of fragment registro_elegir_icono.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            registro_elegir_icono().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}