package a01730311.tec.milam.adapter

import a01730311.tec.milam.screens.home.HomeFragment
import a01730311.tec.milam.screens.home.HomeFragmentDirections
import a01730311.tec.milam.components.GameCard
import a01730311.tec.milam.R
import a01730311.tec.milam.games.GameViewModel
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView


// adapter in charge of cards of every game in home fragment
class GameCardAdapter(private val context: HomeFragment, private val dataset: List<GameCard>, private val findNavController: NavController, private val viewModel: GameViewModel):RecyclerView.Adapter<GameCardAdapter.GameCardViewHolder>() {

    // set of animations
    private lateinit var frontAnimatorSet: AnimatorSet
    private lateinit var backAnimatorSet: AnimatorSet
    private val scale: Float = context.resources.displayMetrics.density

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a GameCard object.
    class GameCardViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val cardView: MaterialCardView = view.findViewById(R.id.game_card_front)
        val imagePlaceholder: ImageView = view.findViewById(R.id.game_card_image)
        val title: TextView = view.findViewById(R.id.game_card_title)
        val description: TextView = view.findViewById(R.id.game_card_description)
        val cardView2: MaterialCardView = view.findViewById(R.id.game_card_back)
        val imagePlaceholder2: ImageView = view.findViewById(R.id.game_card_back_image)
        val title2: TextView = view.findViewById(R.id.game_card_back_title)
        val description2: TextView = view.findViewById(R.id.game_card_back_description)
        val button: MaterialCardView = view.findViewById(R.id.game_card_button)
        var isFront = true
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameCardViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.game_card, parent, false)

        frontAnimatorSet = AnimatorInflater.loadAnimator(context.requireContext(), R.animator.front_card_animation) as AnimatorSet
        backAnimatorSet = AnimatorInflater.loadAnimator(context.requireContext(), R.animator.back_card_animator) as AnimatorSet

        return GameCardViewHolder(adapterLayout)
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: GameCardViewHolder, position: Int) {
        val card = dataset[position]
        val nameOfGame = context.resources.getString(card.labelText)



        // animates the interaction of the card
        holder.cardView.setOnClickListener {

            holder.cardView.cameraDistance = 8000 * scale
            holder.cardView2.cameraDistance = 8000 * scale

            if (holder.isFront) {
                frontAnimatorSet.setTarget(holder.cardView)
                backAnimatorSet.setTarget(holder.cardView2)
                frontAnimatorSet.start()
                backAnimatorSet.start()
                holder.button.isEnabled = false
                holder.isFront = false
            } else {
                frontAnimatorSet.setTarget(holder.cardView2)
                backAnimatorSet.setTarget(holder.cardView)
                backAnimatorSet.start()
                frontAnimatorSet.start()
                holder.button.isEnabled = true
                holder.isFront = true
            }
        }

        if (holder.isFront) {

            // sets the data to every card
            holder.cardView.setCardBackgroundColor(context.resources.getInteger(card.backgroundColor))
            holder.imagePlaceholder.setImageDrawable(context.resources.getDrawable(card.srcCompat))
            holder.description.text = context.resources.getString(card.descriptionText)
            holder.title.text = nameOfGame

            // send data to preview page
            holder.button.setOnClickListener {
                viewModel.setGame(card)
                val action = HomeFragmentDirections.actionHomeFragmentToPreviewFragment()
                findNavController.navigate(action)
            }


            holder.cardView2.setCardBackgroundColor(context.resources.getInteger(card.backgroundColor))
            holder.imagePlaceholder2.setImageDrawable(context.resources.getDrawable(card.srcCompat))
            holder.title2.text = nameOfGame
            holder.description2.text = viewModel.getDescriptionScore(card.gameID)

        }
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    override fun getItemCount(): Int {
        return  dataset.size
    }

}