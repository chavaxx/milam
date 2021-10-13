package a01730311.tec.milam.screens.login

import a01730311.tec.milam.adapter.ProfileAdapter
import a01730311.tec.milam.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View =  inflater.inflate(R.layout.fragment_login, container, false)

        //WindowCompat.setDecorFitsSystemWindows(requireActivity().window, false)
        val window = activity?.window
        window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.blue_buttons )
        setNavigation(view)
        loadCards(view)

        return view
    }

    private fun loadCards(view: View) {


        viewModel.setSharedPreferences(activity)
        val navController = findNavController()
        val myProfiles = viewModel.getProfiles()

        if (myProfiles.size == 0 ) {
            val action = LoginFragmentDirections.actionLoginFragmentToRegistroUsuario()
            navController.navigate(action)
        }

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