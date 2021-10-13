package a01730311.tec.milam.games.magicNumber

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import a01730311.tec.milam.R
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class magicNumberFragment : Fragment() {
    private val game = magicNumberModel()
    private lateinit var question: TextView
    private lateinit var score: TextView
    fun startGame(view: View){
        game.getOperationToSolve()
        var correctNumber = game.getCorrectNumber(game.getNumber1(),game.getNumber2(),game.getOperation())
        var correctButton = game.getRandom().nextInt(0,8)
        var colors = game.getColors()
        score.text = "Ejercicios resueltos: " + (game.getLevel()-1).toString()
        var sign = ""
        if(game.getOperation()) sign = "+"
        else sign = "-"
        question.text = game.getNumber1().toString() + " " + sign + " " + game.getNumber2().toString() + "___?"
        for(i in game.getButtonID().indices){
            var currentButton: Button
            currentButton = view.findViewById(game.getButtonID()[i])
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
    fun correctAnswer(view: View){
        Toast.makeText(activity, "Respuesta correcta, muy bien!", Toast.LENGTH_SHORT).show()
        game.setLevel()
        startGame(view)
    }

    fun wrongAnswer(){
        Toast.makeText(activity, "Respuesta incorrecta, intenta nuevamente", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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