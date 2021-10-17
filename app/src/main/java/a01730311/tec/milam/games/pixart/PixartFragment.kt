package a01730311.tec.milam.games.pixart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import a01730311.tec.milam.R
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class PixartFragment : Fragment() {

    private lateinit var pauseButton: FloatingActionButton
    private lateinit var pauseDialog: Dialog
    private lateinit var closeDialogButton: ImageView
    private lateinit var retryButton: MaterialCardView
    private lateinit var exitGameButton: MaterialCardView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pauseDialog = Dialog(requireContext())
        pauseButton = view.findViewById(R.id.pauseButton)
        pauseButton.setOnClickListener {
            onPressPause()
        }
    }

    private fun onPressPause() {
        //build dialog
        pauseDialog.setContentView(R.layout.pause_dialog)
        pauseDialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        pauseDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // bind buttons
        closeDialogButton = pauseDialog.findViewById(R.id.close_pause_dialog)
        retryButton = pauseDialog.findViewById(R.id.retry_button)
        exitGameButton = pauseDialog.findViewById(R.id.exit_game_button)

        // close dialog
        closeDialogButton.setOnClickListener {
            pauseDialog.hide()
        }

        // reload game
        retryButton.setOnClickListener {
            //reload game
        }

        //exit game
        exitGameButton.setOnClickListener {

            pauseDialog.hide()

            findNavController().popBackStack()

        }

        pauseDialog.show()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pixart, container, false)
    }

}