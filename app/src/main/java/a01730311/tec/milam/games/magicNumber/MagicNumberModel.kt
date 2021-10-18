package a01730311.tec.milam.games.magicNumber

import a01730311.tec.milam.R
import android.graphics.Color
import android.view.View
import android.widget.Button
import kotlin.random.Random

//the model of the magic number fragment
class MagicNumberModel{
    //initialize the variables, the array of button ids, the colors, etc
    private var buttonID = arrayOf<Int>(R.id.number1,R.id.number2,R.id.number3,R.id.number4,R.id.number5,R.id.number6,R.id.number7,R.id.number8,R.id.number9 )
    private val colors = arrayOf(
        Color.parseColor("#D98A29"),
        Color.parseColor("#0CB485"),
        Color.parseColor("#FF8F00"),
        Color.parseColor("#EF6C00"),
        Color.parseColor("#D84315"),
        Color.parseColor("#37474F"),
        Color.parseColor("#fe4da0"),
        Color.parseColor("#ff7774"),
        Color.parseColor("#89e05a"),
        Color.parseColor("#adf032"),
        Color.parseColor("#28b8bf"),
        Color.parseColor("#205374"),
        Color.parseColor("#27a09e"),
        Color.parseColor("#30ce88"),
        Color.parseColor("#7de393"),
        Color.parseColor("#d3f5ee"),
    )
    private var number1 = 0
    private var number2 = 0
    private var operation = true
    //private var correctNumber = 1
    private var Level = 1 //TODO: shared preferenches check
    private var rand = Random

    //function to get the array of button id
    fun getButtonID(): Array<Int>{
        return buttonID
    }
    //function to get the first number of the operation
    fun getNumber1(): Int{
        return number1
    }
    //function to get the second number of the operation
    fun getNumber2(): Int{
        return number2
    }
    //function that returns the sign of the operation
    fun getOperation(): Boolean{
        return operation
    }
    //function that returns the instance of random (so that it is not necesary to create a new one)
    fun getRandom(): Random{
        return rand
    }
    //function to get the array of colors
    fun getColors(): Array<Int>{
        return colors
    }
    //function to get the level
    fun getLevel(): Int{
        return Level
    }
    //function add 1 to the current level
    fun setLevel(){
        Level++
    }

    //function that generates the new operation to solve (starts with substraction when level>10)
    fun getOperationToSolve(){
        number1 = rand.nextInt(1,Level + 1)
        number2 = rand.nextInt(1,Level + 1)
        if(number2>number1){
            var temp = number2
            number2 = number1
            number1 = temp
        }
        if(Level>10) operation = rand.nextBoolean()
    }

    //function that returns the result of the current operation
    fun getCorrectNumber(number1: Int, number2: Int,operation: Boolean):Int{
        return if(operation){
            number1 + number2
        } else number1 - number2
    }
    //function that generates all the dummy results
    fun getNewValues(correctValue: Int):Int{
        var randomNumber: Int = rand.nextInt(1,correctValue+Level)
        if(randomNumber==correctValue){
            randomNumber=randomNumber+randomNumber
        }
        return randomNumber
    }



}