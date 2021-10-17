package a01730311.tec.milam.games.memo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import a01730311.tec.milam.R
import a01730311.tec.milam.screens.home.ProgressViewModel
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MemoFragment : Fragment() {
    private lateinit var adapter: MemoryAdapter
    private lateinit var memoryGame: MemoryGame
    private lateinit var board:RecyclerView
    private lateinit var clRoot: ConstraintLayout
    private lateinit var currentLevel: TextView
    private val level: ProgressViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val memoryLevels: MemoryLevels= MemoryLevels.values()[level.getMaxLevel("memorandum").toInt() -1]
        currentLevel = view.findViewById(R.id.levelLabel)
        clRoot = view.findViewById(R.id.clRoot)
        board = view.findViewById(R.id.board)
        board.setHasFixedSize(true)

        memoryGame = MemoryGame(memoryLevels)
        //currentLevel.setText("Nivel de Memorama: $memoryGame.")
        adapter = MemoryAdapter(requireContext(), memoryLevels, memoryGame.cards, object: MemoryAdapter.CardClickListener{
            override fun onCardClicked(position: Int) {
                updateGameWithFlip(position)
                if(memoryGame.haveWonGame())Snackbar.make(clRoot, "¡Ganaste, Felicidades!",Snackbar.LENGTH_SHORT).show()
                level.setScore("memorandum",0,(level.getMaxLevel("memorandum").toInt()+1).toString())
                if(memoryGame.haveWonGame())Snackbar.make(clRoot, "¡Ganaste, Felicidades!",Snackbar.LENGTH_SHORT).show()
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