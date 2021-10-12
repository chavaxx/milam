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
import androidx.core.view.WindowCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    private val viewModel: UserViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View =  inflater.inflate(R.layout.fragment_login, container, false)

        WindowCompat.setDecorFitsSystemWindows(requireActivity().window, false)
        setNavigation(view)
        loadCards(view)

        return view
    }

    private fun loadCards(view: View) {


        viewModel.setSharedPreferences(activity)
        val myProfiles = viewModel.getProfiles()
        val navController = findNavController()

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewProfiles)
        recyclerView.adapter = ProfileAdapter(this, myProfiles, viewModel, navController)
        recyclerView.setHasFixedSize(true)
    }

    private fun setNavigation(view: View) {
        val signupButton: Button = view.findViewById(R.id.signup_button)
        signupButton.setOnClickListener{
            val action = LoginFragmentDirections.actionLoginFragmentToRegistroUsuario()
            findNavController().navigate(action)
        }
    }

}