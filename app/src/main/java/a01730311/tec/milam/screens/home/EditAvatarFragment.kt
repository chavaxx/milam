package a01730311.tec.milam.screens.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import a01730311.tec.milam.R
import a01730311.tec.milam.adapter.AvatarAdapter
import a01730311.tec.milam.adapter.ProfileAdapter
import a01730311.tec.milam.components.MyToast
import a01730311.tec.milam.data.Datasource
import a01730311.tec.milam.screens.login.UserViewModel
import android.os.Build
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView


class EditAvatarFragment : Fragment() {


    private lateinit var goBackButton: LinearLayout;
    private lateinit var saveButton: MaterialCardView
    private var selectedAvatar: Int = -1;
    private val viewModel: UserViewModel by activityViewModels()
    private val myAvatars = Datasource().loadAvatars();
    private lateinit var toast: MyToast


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit_avatar, container, false)

        toast = MyToast(activity)
        setNavigation(view);
        loadAvatars(view);

        return view
    }

    // manage navigation interactions
    private fun setNavigation(view: View) {
        goBackButton = view.findViewById(R.id.goBackSettingsFromSettings)
        goBackButton.setOnClickListener{
            findNavController().popBackStack()
        }

        saveButton = view.findViewById(R.id.save_avatar)
        saveButton.setOnClickListener {
            if (selectedAvatar != -1) {
                viewModel.editAvatar(myAvatars[selectedAvatar])
                findNavController().popBackStack()
                toast.showMessage("¡Listo! Tu nuevo icono ha sido guardado :)")
            } else {
                toast.showMessage("Selecciona un icono")
            }
        }

    }


    // loads avatars to recycler view
    private fun loadAvatars(view: View) {

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewAvatars)

        recyclerView.adapter = AvatarAdapter(this, myAvatars, ::selectedAvatar)

        recyclerView.setHasFixedSize(true)
    }


}