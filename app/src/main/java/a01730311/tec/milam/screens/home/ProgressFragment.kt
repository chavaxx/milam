package a01730311.tec.milam.screens.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import a01730311.tec.milam.R
import a01730311.tec.milam.adapter.GameCardAdapter
import a01730311.tec.milam.adapter.ProgressAdapter
import a01730311.tec.milam.data.Datasource
import a01730311.tec.milam.games.GameViewModel
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView


class ProgressFragment : Fragment() {

    private lateinit var goBackButton: LinearLayout

    private val gameViewModel: GameViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadGameCards(view)
    }

    private fun loadGameCards(view: View) {

        val myGameCards = Datasource().loadGameCards()

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_cards_back)
        recyclerView.adapter = ProgressAdapter(this, myGameCards, gameViewModel)

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_progress, container, false)

        setNavigation(view)

        return view
    }


    // sets navigation controls
    private fun setNavigation(view: View) {

        goBackButton = view.findViewById(R.id.goBackHomeFromProgress)
        goBackButton.setOnClickListener{
            findNavController().popBackStack()
        }

    }


}