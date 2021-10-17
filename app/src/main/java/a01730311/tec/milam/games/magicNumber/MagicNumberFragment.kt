package a01730311.tec.milam.games.magicNumber

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import a01730311.tec.milam.R
import a01730311.tec.milam.components.Modal
import a01730311.tec.milam.screens.home.ProgressViewModel
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


class MagicNumberFragment : Fragment() {
    private val game = MagicNumberModel()
    private lateinit var question: TextView
    private lateinit var score: TextView


    private lateinit var pauseButton: FloatingActionButton

    private lateinit var modal: Modal

    private val progress: ProgressViewModel by activityViewModels()

    private fun startGame(view: View){
        game.getOperationToSolve()
        val correctNumber = game.getCorrectNumber(game.getNumber1(),game.getNumber2(),game.getOperation())
        val correctButton = game.getRandom().nextInt(0,8)
        val colors = game.getColors()
        score.text = "Ejercicios resueltos: " + (game.getLevel()-1).toString()
        var sign = ""
        if(game.getOperation()) sign = "+"
        else sign = "-"
        question.text = game.getNumber1().toString() + " " + sign + " " + game.getNumber2().toString() + " = ___"
        for(i in game.getButtonID().indices){
            val currentButton: Button = view.findViewById(game.getButtonID()[i])
            if(i == correctButton){
                currentButton.text = correctNumber.toString()
                currentButton.setTextColor(colors[game.getRandom().nextInt(0,14)])
                currentButton.setOnClickListener {correctAnswer(view)}
            }else{
                currentButton.text = game.getNewValues(correctNumber).toString()
                currentButton.setTextColor(colors[game.getRandom().nextInt(0,14)])
                currentButton.setOnClickListener { wrongAnswer()};
            }
        }
    }

    private fun correctAnswer(view: View){
        Toast.makeText(activity, "Respuesta correcta, muy bien!", Toast.LENGTH_SHORT).show()
        if (progress.getScore("magic_number")!! < game.getLevel()) {
            progress.setScore("magic_number", game.getLevel())
        }
        game.setLevel()
        startGame(view)
    }

    private fun wrongAnswer(){
        modal.showFailureMenu()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        modal = Modal(requireContext(), R.id.magicNumberFragment, findNavController(), progress, 1)
        pauseButton = view.findViewById(R.id.pauseButton)
        pauseButton.setOnClickListener {
            modal.showPauseMenu()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_magic_number, container, false)
        question = view.findViewById(R.id.question)
        score = view.findViewById(R.id.score)
        startGame(view)
        return view
    }

}