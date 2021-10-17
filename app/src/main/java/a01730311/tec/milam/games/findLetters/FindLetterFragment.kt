package a01730311.tec.milam.games.findLetters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import a01730311.tec.milam.R
import a01730311.tec.milam.screens.home.ProgressViewModel
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class FindLetterFragment : Fragment() {
    private lateinit var matrixLetters : RecyclerView
    private lateinit var viewLetterToFind : TextView
    private lateinit var punctuationTxt : TextView
    private lateinit var findLetterGame: FindLetterModel
    private lateinit var adapter: MatrixLettersAdapter
    private val level: ProgressViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_find_letter, container, false)

        viewLetterToFind = view.findViewById(R.id.letterToFind)
        punctuationTxt = view.findViewById(R.id.punctuationTxt)
        matrixLetters = view.findViewById(R.id.matrixLetters)

        findLetterGame = FindLetterModel(level.getMaxLevel("letters").toInt() -1)

        val txtToRender : String = findLetterGame.renderPunctuation()
        punctuationTxt.setText(txtToRender)

        val letterToFind : String = findLetterGame.getLetterToFind()
        viewLetterToFind.setText(letterToFind)

        adapter = MatrixLettersAdapter(requireActivity(), findLetterGame.getLvl(), findLetterGame.getChosenLetters(), object : MatrixLettersAdapter.LetterClickListener{
            override fun onLetterClicked(position: Int) {
                updateGame(position)
                if(findLetterGame.getHasWon()){
                    //TODO: CHANGE OF LEVEL
                }
            }

        })
        matrixLetters.adapter = adapter
        matrixLetters.setHasFixedSize(true)
        matrixLetters.layoutManager = GridLayoutManager(activity, findLetterGame.getLvl().getWidth())

        return view
    }

    private fun updateGame(position : Int){
        findLetterGame.selectLetter(position)
        val txtToRender : String = findLetterGame.renderPunctuation()
        punctuationTxt.setText(txtToRender)
        adapter.notifyItemChanged(position)
    }

}