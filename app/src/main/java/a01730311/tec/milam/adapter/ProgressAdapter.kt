package a01730311.tec.milam.adapter

import a01730311.tec.milam.R
import a01730311.tec.milam.components.GameCard
import a01730311.tec.milam.games.GameViewModel
import a01730311.tec.milam.screens.home.HomeFragment
import a01730311.tec.milam.screens.home.ProgressFragment
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class ProgressAdapter(private val context: ProgressFragment, private val dataset: List<GameCard>, private val viewModel: GameViewModel):
    RecyclerView.Adapter<ProgressAdapter.ProgressViewHolder>() {

    class ProgressViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val cardView: MaterialCardView = view.findViewById(R.id.game_card_back)
        val imagePlaceholder: ImageView = view.findViewById(R.id.game_card_back_image)
        val title: TextView = view.findViewById(R.id.game_card_back_title)
        val description: TextView = view.findViewById(R.id.game_card_back_description)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProgressViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.game_card_back, parent, false)

        return ProgressAdapter.ProgressViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ProgressViewHolder, position: Int) {
        val card = dataset[position]
        val nameOfGame = context.resources.getString(card.labelText)

        holder.cardView.alpha = 1f
        holder.cardView.setCardBackgroundColor(context.resources.getInteger(card.backgroundColor))
        holder.imagePlaceholder.setImageDrawable(context.resources.getDrawable(card.srcCompat))
        holder.title.text = nameOfGame
        holder.description.text = viewModel.getDescriptionScore(card.gameID)
    }

    override fun getItemCount(): Int {
        return  dataset.size
    }

}