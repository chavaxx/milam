package a01730311.tec.milam.games.magicNumber

import a01730311.tec.milam.R
import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val game = magicNumberModel()
    private lateinit var question:TextView
    private lateinit var score:TextView
    fun startGame(){
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
            currentButton = findViewById(game.getButtonID()[i])
            if(i == correctButton){
                currentButton.text = correctNumber.toString()
                currentButton.setTextColor(colors[game.getRandom().nextInt(0,14)])
                currentButton.setOnClickListener {correctAnswer()}
            }else{
            currentButton.text = game.getNewValues(correctNumber).toString()
            currentButton.setTextColor(colors[game.getRandom().nextInt(0,14)])
            currentButton.setOnClickListener { wrongAnswer()};
            }
        }
    }
    fun correctAnswer(){
        Toast.makeText(this, "Respuesta correcta, muy bien!", Toast.LENGTH_SHORT).show()
        game.setLevel()
        startGame()
    }

    fun wrongAnswer(){
        Toast.makeText(this, "Respuesta incorrecta, intenta nuevamente", Toast.LENGTH_SHORT).show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        question = findViewById(R.id.question)
        score = findViewById(R.id.score)
        startGame()
    }
}