package a01730311.tec.milam.games.memo

import a01730311.tec.milam.R
import android.animation.*
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.core.animation.doOnCancel
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import kotlin.math.min

class MemoryAdapter(
    private val context: Context,
    private val board: MemoryLevels,
    private val cards: List<MemoryCard>,
    private val cardClickListener: CardClickListener
) : RecyclerView.Adapter<MemoryAdapter.ViewHolder>() {

    private lateinit var frontAnimatorSet: AnimatorSet
    private lateinit var backAnimatorSet: AnimatorSet
    private val scale: Float = context.resources.displayMetrics.density

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val button: MaterialCardView = itemView.findViewById(R.id.game_card_memo_button)
        val image: ImageView = itemView.findViewById(R.id.game_card_memo_image)
        val button2 : MaterialCardView = itemView.findViewById(R.id.game_card_memo_copy)
        val image2: ImageView = itemView.findViewById(R.id.game_card_memo_copy_image)
    }

    interface CardClickListener{
        fun onCardClicked(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.game_card_mem,parent,false)
        val viewHeight = parent.height/board.getHeight() - 2*10
        val viewWidth = parent.width/board.getWidth() -2*10
        val side = min(viewHeight,viewWidth)


        frontAnimatorSet = AnimatorInflater.loadAnimator(context, R.animator.front_card_animation) as AnimatorSet
        backAnimatorSet = AnimatorInflater.loadAnimator(context, R.animator.back_card_animator) as AnimatorSet

        val layoutParams = view.findViewById<MaterialCardView>(R.id.game_card_memo_button).layoutParams as ViewGroup.MarginLayoutParams
        val layoutParams2 = view.findViewById<MaterialCardView>(R.id.game_card_memo_copy).layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.width = side
        layoutParams.height = side
        layoutParams2.width = side
        layoutParams2.height = side
        layoutParams.setMargins(0,10,0,10)
        layoutParams2.setMargins(0,10,0,10)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = cards[position]

        println(cards)
        holder.image.setImageResource(if (cards[position].isFaceUp) cards[position].identifier else R.drawable.milam_logo)

        holder.image.alpha = if(cards[position].isMatched) 0.4f else 1.0f
        val colorStateList =  if(cards[position].isMatched) ContextCompat.getColorStateList(context, R.color.color_gray) else null
        ViewCompat.setBackgroundTintList(holder.image, colorStateList)


        holder.button.setOnClickListener{

            cardClickListener.onCardClicked(position)

            //if (!card.isFaceUp) {


              //  holder.image2.setImageResource(card.identifier)

                //holder.button.cameraDistance = 8000 * scale
                //holder.button2.cameraDistance = 8000 * scale

                //frontAnimatorSet.setTarget(holder.button)
                //backAnimatorSet.setTarget(holder.button2)

                //frontAnimatorSet.doOnEnd {
                  //  cardClickListener.onCardClicked(position)
                //}

                //frontAnimatorSet.start()
                //backAnimatorSet.start()

            //} else {
              //  cardClickListener.onCardClicked(position)
            //}

        }
    }

    override fun getItemCount(): Int = board.cardNumber


}
