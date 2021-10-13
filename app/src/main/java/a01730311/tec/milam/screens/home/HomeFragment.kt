package a01730311.tec.milam.screens.home

import a01730311.tec.milam.adapter.GameCardAdapter
import a01730311.tec.milam.data.Datasource
import a01730311.tec.milam.R
import a01730311.tec.milam.screens.login.UserViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.color.MaterialColors


class HomeFragment : Fragment() {

    private lateinit var progressLabel: TextView
    private lateinit var userAvatar: TextView
    private lateinit var labelUsername: TextView
    private val viewModel: UserViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setTranslationZ(requireView(), 100f)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View =  inflater.inflate(R.layout.fragment_home, container, false)


        val window = activity?.window
        window?.statusBarColor = MaterialColors.getColor(view, R.attr.colorSecondary)
        loadGameCards(view)
        setControls(view)

        return view
    }

    private fun loadGameCards(view: View) {

        val myGameCards = Datasource().loadGameCards()

        val navController = findNavController()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_cards)
        recyclerView.adapter = GameCardAdapter(this, myGameCards, navController)


        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true)
    }


    private fun setControls(view: View) {
        labelUsername = view.findViewById(R.id.labelUsername)
        labelUsername.text = "¡Hola de nuevo, " + viewModel.getUsername() + "!"
        progressLabel = view.findViewById(R.id.progressLabel)
        progressLabel.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToProgressFragment()
            findNavController().navigate(action)
        }
        userAvatar = view.findViewById(R.id.userAvatar)
        userAvatar.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSettingsFragment()
            findNavController().navigate(action)
        }
    }


}