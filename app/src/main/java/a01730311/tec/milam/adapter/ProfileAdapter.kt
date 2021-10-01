package a01730311.tec.milam.adapter

import a01730311.tec.milam.screens.login.LoginFragment
import a01730311.tec.milam.R
import a01730311.tec.milam.components.Profile
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class ProfileAdapter(private val context: LoginFragment, private val dataset: List<Profile>):RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    class ProfileViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val avatar: ImageView = view.findViewById(R.id.profile_image)
        val username: TextView = view.findViewById(R.id.profile_text)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.profile_card, parent, false)

        return ProfileViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val card = dataset[position]
        holder.avatar.setImageDrawable(context.resources.getDrawable(card.iconID))
        holder.username.text = card.username
    }

    override fun getItemCount(): Int {
        return  dataset.size
    }
}