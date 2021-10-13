package a01730311.tec.milam.screens.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import a01730311.tec.milam.R
import a01730311.tec.milam.adapter.AvatarAdapter
import a01730311.tec.milam.components.MyToast
import a01730311.tec.milam.components.Profile
import a01730311.tec.milam.data.Datasource
import a01730311.tec.milam.screens.login.UserViewModel
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView


class ChooseAvatarFragment : Fragment() {


    private var selectedAvatar: Int = -1
    private lateinit var myAvatars: List<Profile>
    private lateinit var toast: MyToast
    private val viewModel: UserViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View =  inflater.inflate(R.layout.fragment_registro_elegir_icono, container, false)

        toast = MyToast(activity)
        setNavigation(view)
        loadAvatars(view)

        return view

    }

    private fun loadAvatars(view: View) {


        myAvatars = Datasource().loadAvatars()

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewAvatars)

        recyclerView.adapter = AvatarAdapter(this, myAvatars, ::selectedAvatar)


        recyclerView.setHasFixedSize(true)
    }

    private fun setNavigation(view: View) {
        val button: Button = view.findViewById(R.id.button_next_confirm)
        val backButton: Button = view.findViewById(R.id.button_back_icons)
        button.setOnClickListener{
            if (selectedAvatar == -1) {
                toast.showMessage("Selecciona un icono de arriba")
            } else {
                viewModel.setAvatarID(myAvatars[selectedAvatar].iconID)
                val action = ChooseAvatarFragmentDirections.actionRegistroElegirIconoToConfirmarPerfil()
                findNavController().navigate(action)
            }
        }
        backButton.setOnClickListener{
            findNavController().popBackStack()
        }
    }

}