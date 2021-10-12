package a01730311.tec.milam.adapter

import a01730311.tec.milam.R
import a01730311.tec.milam.components.Profile
import a01730311.tec.milam.screens.home.HomeFragmentDirections
import a01730311.tec.milam.screens.login.LoginFragment
import a01730311.tec.milam.screens.login.LoginFragmentDirections
import a01730311.tec.milam.screens.login.UserViewModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class ProfileAdapter(private val context: LoginFragment, private val dataset: List<Profile>, private val viewModel: UserViewModel, private val navController: NavController):RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {



    class ProfileViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val avatar: ImageView = view.findViewById(R.id.profile_image)
        val username: TextView = view.findViewById(R.id.profile_text)
        val card: MaterialCardView = view.findViewById(R.id.profile_card)
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
        holder.card.setOnClickListener {
            viewModel.setAvatarID(card.iconID)
            viewModel.setUsername(card.username)
            val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
            navController.navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return  dataset.size
    }
}