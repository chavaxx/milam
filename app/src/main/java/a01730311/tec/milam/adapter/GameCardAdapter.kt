package a01730311.tec.milam.adapter

import a01730311.tec.milam.GameCard
import a01730311.tec.milam.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class GameCardAdapter(private val context: Context, private val dataset: List<GameCard>):RecyclerView.Adapter<GameCardAdapter.GameCardViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a GameCard object.
    class GameCardViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val cardView: MaterialCardView = view.findViewById(R.id.game_card)
        val imagePlaceholder: ImageView = view.findViewById(R.id.game_card_image)
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameCardViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.game_card, parent, false)

        return GameCardViewHolder(adapterLayout)
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: GameCardViewHolder, position: Int) {
        val card = dataset[position]
        holder.cardView.setCardBackgroundColor(context.resources.getInteger(card.backgroundColor))
       holder.imagePlaceholder.setImageDrawable(context.resources.getDrawable(card.srcCompat))
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    override fun getItemCount(): Int {
        return  dataset.size
    }
}