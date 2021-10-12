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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditAvatarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditAvatarFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var goBackButton: LinearLayout;
    private lateinit var saveButton: MaterialCardView
    private var selectedAvatar: Int = -1;
    private val viewModel: UserViewModel by activityViewModels()
    private val myAvatars = Datasource().loadAvatars();
    private lateinit var toast: MyToast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

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

    private fun setNavigation(view: View) {
        goBackButton = view.findViewById(R.id.goBackSettingsFromSettings)
        goBackButton.setOnClickListener{
            findNavController().popBackStack()
        }

        saveButton = view.findViewById(R.id.save_avatar)
        saveButton.setOnClickListener {
            if (selectedAvatar != -1) {
                viewModel.edit()
                viewModel.saveAvatar(myAvatars[selectedAvatar].iconID)
                findNavController().popBackStack()
            } else {
                toast.showMessage("Selecciona un icono")
            }
        }

    }

    private fun loadAvatars(view: View) {



        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewAvatars)

        recyclerView.adapter = AvatarAdapter(this, myAvatars, ::selectedAvatar)

        recyclerView.setHasFixedSize(true)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EditAvatarFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditAvatarFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}