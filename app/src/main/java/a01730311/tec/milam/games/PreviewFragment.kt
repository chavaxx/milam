package a01730311.tec.milam.games

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import a01730311.tec.milam.R
import a01730311.tec.milam.components.ModalAbout
import a01730311.tec.milam.screens.home.ProgressViewModel
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.color.MaterialColors
import com.google.android.material.floatingactionbutton.FloatingActionButton


class PreviewFragment : Fragment() {

    private lateinit var imagePreview: ImageView
    private lateinit var gameTitle: TextView
    private lateinit var goBack: LinearLayout
    private lateinit var scoreLabel: TextView
    private lateinit var levelLabel: TextView
    private lateinit var helpButton: FloatingActionButton
    private lateinit var playButton: FloatingActionButton
    private val gameViewModel: GameViewModel  by activityViewModels()
    private lateinit var modal: ModalAbout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_preview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        modal = ModalAbout(requireContext())
        bindViews(view)
    }

    private fun bindViews(view: View) {
        imagePreview = view.findViewById(R.id.game_preview_placeholder)
        imagePreview.setImageDrawable(ContextCompat.getDrawable(requireContext(), gameViewModel.getImage()))
        gameTitle = view.findViewById(R.id.game_title_preview)
        gameTitle.text = getString(gameViewModel.getName())
        goBack = view.findViewById(R.id.goBackHomeFromPreview)
        goBack.setOnClickListener {
            findNavController().popBackStack()
        }
        scoreLabel = view.findViewById(R.id.game_score_preview)
        scoreLabel.text = gameViewModel.getScore()
        levelLabel = view.findViewById(R.id.game_level_preview)
        levelLabel.text = gameViewModel.getLevel()
        helpButton = view.findViewById(R.id.help_button)
        helpButton.setOnClickListener {
            modal.showInfo(getString(gameViewModel.getDescription()), getString(gameViewModel.getName()))
        }
        playButton = view.findViewById(R.id.start_game)
        playButton.setOnClickListener {
            val window = activity?.window
            window?.statusBarColor = MaterialColors.getColor(view, R.attr.statusBarForeground)
            findNavController().navigate(gameViewModel.getDirections())
        }
    }

}