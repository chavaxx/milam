package a01730311.tec.milam.adapter

import a01730311.tec.milam.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import kotlin.reflect.KMutableProperty0

class AvatarAdapter(private val context: Fragment, private val dataset: List<Int>, private val intReference: KMutableProperty0<Int> ):RecyclerView.Adapter<AvatarAdapter.AvatarViewHolder>() {


    private var selectedAvatar: Int = intReference.get()


    class AvatarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatar: ImageView = view.findViewById(R.id.avatar_image)
        val card: MaterialCardView = view.findViewById(R.id.avatar_card)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AvatarViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.avatar_card, parent, false)

        return AvatarViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: AvatarViewHolder, position: Int) {
        val card = dataset[position]
        holder.avatar.setImageDrawable(
            ContextCompat.getDrawable(context.requireContext(), card ))
        if (selectedAvatar == position) {
            holder.card.strokeWidth = 5
        } else {
            holder.card.strokeWidth = 0
        }

        holder.card.setOnClickListener{
            if (selectedAvatar >= 0)
                notifyItemChanged(selectedAvatar)
            if (position == selectedAvatar) {
                holder.card.strokeWidth = 0
                selectedAvatar = -1
                notifyItemChanged(selectedAvatar)
            } else {
                selectedAvatar = holder.adapterPosition
                notifyItemChanged(selectedAvatar)
            }
            intReference.set(selectedAvatar)
        }

    }

    override fun getItemCount(): Int {
        return  dataset.size
    }

}