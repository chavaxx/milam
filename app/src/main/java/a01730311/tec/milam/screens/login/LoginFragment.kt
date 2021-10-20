package a01730311.tec.milam.screens.login

import a01730311.tec.milam.adapter.ProfileAdapter
import a01730311.tec.milam.R
import a01730311.tec.milam.components.ModalAbout
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
import com.google.android.material.card.MaterialCardView
import com.google.android.material.floatingactionbutton.FloatingActionButton


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    private val viewModel: UserViewModel by activityViewModels()
    private lateinit var modal: ModalAbout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        return  inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //WindowCompat.setDecorFitsSystemWindows(requireActivity().window, false)
        val window = activity?.window
        window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.blue_buttons )
        setNavigation(view)
        loadCards(view)
    }

    private fun loadCards(view: View) {


        // loads data from view model
        viewModel.setSharedPreferences(activity)

        val navController = findNavController()
        val myProfiles = viewModel.getProfiles()

        // if there are no profiles, the user is sent to register
        if (myProfiles.isEmpty()) {
            val action = LoginFragmentDirections.actionLoginFragmentToRegistroUsuario()
            navController.navigate(action)
        }

        // loads profiles to recycler view
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewProfiles)
        recyclerView.adapter = ProfileAdapter(this, myProfiles, viewModel, navController)
        recyclerView.setHasFixedSize(true)
    }

    private fun setNavigation(view: View) {

        val signupButton: MaterialCardView = view.findViewById(R.id.signup_button)
        val loginInfoButton: FloatingActionButton = view.findViewById(R.id.login_info_button)
        modal = ModalAbout(requireContext())


        signupButton.setOnClickListener{
            val action = LoginFragmentDirections.actionLoginFragmentToRegistroUsuario()
            findNavController().navigate(action)
        }

        loginInfoButton.setOnClickListener {
            modal.showInfo(getString(R.string.log_in_help), getString(R.string.log_in))
        }
    }

}