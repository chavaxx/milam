package a01730311.tec.milam.screens.login

import a01730311.tec.milam.adapter.ProfileAdapter
import a01730311.tec.milam.data.Datasource
import a01730311.tec.milam.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View =  inflater.inflate(R.layout.fragment_login, container, false)

        setNavigation(view)
        loadCards(view)


        return view
    }

    private fun loadCards(view: View) {


        val myProfiles = Datasource().loadProfiles();

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewProfiles)
        recyclerView.adapter = ProfileAdapter(this, myProfiles)
        recyclerView.setHasFixedSize(true)
    }

    private fun setNavigation(view: View) {
        val signupButton: Button = view.findViewById(R.id.signup_button)
        signupButton.setOnClickListener{
            val action = LoginFragmentDirections.actionLoginFragmentToRegistroUsuario()
            findNavController().navigate(action)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}