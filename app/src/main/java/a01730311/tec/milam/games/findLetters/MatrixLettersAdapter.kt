package a01730311.tec.milam.games.findLetters

import a01730311.tec.milam.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.findnumber.LetterButon
import kotlin.math.min

class MatrixLettersAdapter(
    private val context: Context,
    private val level: FindLetterLevels,
    private val chosenLetters: List<LetterButon>,
    private val letterClickListener: LetterClickListener
) :
    RecyclerView.Adapter<MatrixLettersAdapter.ViewHolder>() {

    interface LetterClickListener {
        fun onLetterClicked(position: Int)
    }

    //gives a length to every letter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val letterWidth = parent.width / level.getWidth()
        val letterHeight = parent.height / level.getHeight()
        val letterSideLength = min(letterHeight, letterWidth)
        val view : View = LayoutInflater.from(context).inflate(R.layout.letter_space, parent, false )
        val layoutParams = view.findViewById<Button>(R.id.txtButton).layoutParams
        layoutParams.width = letterSideLength
        layoutParams.height = letterSideLength
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    //it indicates how many letters has to render
    override fun getItemCount() = level.getNumPieces()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtButton: Button = itemView.findViewById(R.id.txtButton)

        fun bind(position: Int) {
            //if letter has been selected, it gives a different color
            if (chosenLetters[position].isSelected) {
                txtButton.setTextColor(ContextCompat.getColor(context, R.color.blue_buttons))
            }
            //it renders the text according to the position in the array of choseLetters
            txtButton.text = chosenLetters[position].letter.toString()
            //it specifies a listener
            txtButton.setOnClickListener{
                //Log.i("LetterClicked", "Clicked on position $position")
                letterClickListener.onLetterClicked(position)
            }
        }
    }
}
