package a01730311.tec.milam.games.memo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import a01730311.tec.milam.R
import a01730311.tec.milam.components.Modal
import a01730311.tec.milam.screens.home.ProgressViewModel
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MemoFragment : Fragment() {
    private lateinit var adapter: MemoryAdapter
    private lateinit var memoryGame: MemoryGame
    private lateinit var board:RecyclerView
    private lateinit var clRoot: ConstraintLayout
    private lateinit var currentLevel: TextView
    private lateinit var pauseButton: FloatingActionButton

    private lateinit var modal: Modal

    private val level: ProgressViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val memoryLevels: MemoryLevels= MemoryLevels.values()[level.getMaxLevel("memorandum").toInt() - 1]
        currentLevel = view.findViewById(R.id.levelLabel)

        // pause binding
        modal = Modal(requireContext(), R.id.memoFragment, findNavController(), level, 11)
        pauseButton = view.findViewById(R.id.pauseButton)
        pauseButton.setOnClickListener {
            modal.showPauseMenu()
        }

        if (level.getMaxLevel("memorandum").toInt() <11) {
            currentLevel.text = "Nivel: " + (level.getMaxLevel("memorandum").toInt()).toString()
        } else {
            currentLevel.text = "Nivel max"
        }

        clRoot = view.findViewById(R.id.clRoot)
        board = view.findViewById(R.id.board)
        board.setHasFixedSize(true)


        memoryGame = MemoryGame(memoryLevels)
        //currentLevel.setText("Nivel de Memorama: $memoryGame.")
        adapter = MemoryAdapter(requireContext(), memoryLevels, memoryGame.cards, object: MemoryAdapter.CardClickListener{
            override fun onCardClicked(position: Int) {
                updateGameWithFlip(position)
                if(memoryGame.haveWonGame()){
                    //Snackbar.make(clRoot, "¡Ganaste, Felicidades!",Snackbar.LENGTH_SHORT).show()
                    modal.showSuccessMenu("memorandum", 0)
                }

            }
        })
        board.adapter = adapter
        board.setHasFixedSize(true)
        board.layoutManager = GridLayoutManager(requireContext(),memoryLevels.getWidth())
    }


    private fun updateGameWithFlip(position: Int) {

        if(memoryGame.haveWonGame()){

            Snackbar.make(clRoot, "¡Ganaste, Felicidades!",Snackbar.LENGTH_SHORT).show()
            return
        }
        if(memoryGame.isCardFaceUp(position)){
            //TODO: CHANGE TO RESPOND BETTER INTERACTIONS
            Snackbar.make(clRoot, "Esa carta ya esta volteada, movimiento no válido",Snackbar.LENGTH_SHORT).show()
            return
        }
        memoryGame.flipCard(position)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_memo, container, false)
    }
}