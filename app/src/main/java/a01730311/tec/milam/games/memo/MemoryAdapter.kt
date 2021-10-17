package a01730311.tec.milam.games.memo

import a01730311.tec.milam.R
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.min

class MemoryAdapter(
    private val context: Context,
    private val board: MemoryLevels,
    private val cards: List<MemoryCard>,
    private val cardClickListener: CardClickListener
) : RecyclerView.Adapter<MemoryAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var imageButton = itemView.findViewById<ImageButton>(R.id.imageButton)

        fun bind(position:Int){
            imageButton.setImageResource(if (cards[position].isFaceUp) cards[position].identifier else R.drawable.ic_launcher_background )

            imageButton.alpha = if(cards[position].isMatched) 0.4f else 1.0f
            val colorStateList =  if(cards[position].isMatched) ContextCompat.getColorStateList(context, R.color.color_gray) else null
            ViewCompat.setBackgroundTintList(imageButton, colorStateList)


            imageButton.setOnClickListener{
                Log.i("memory", "posicion: $position")
                cardClickListener.onCardClicked(position)
            }
        }
    }

    interface CardClickListener{
        fun onCardClicked(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.game_card_mem,parent,false)
        val viewHeight = parent.height/board.getHeight() - 2*10
        val viewWidth = parent.width/board.getWidth() -2*10
        val side = min(viewHeight,viewWidth)
        val layoutParams = view.findViewById<CardView>(R.id.gameCard).layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.width = side
        layoutParams.height = side
        layoutParams.setMargins(0,10,0,10)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = board.cardNumber


}
