package a01730311.tec.milam.games.pixart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import a01730311.tec.milam.R
import a01730311.tec.milam.screens.home.ProgressViewModel
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class PixartFragment : Fragment() {

    private lateinit var pixels : RecyclerView
    private lateinit var colorPicker : RecyclerView

    private lateinit var pixarteGame : PixarteModel
    private lateinit var pixelsAdapter : PixelsAdapter
    private lateinit var colorPickerAdapter : ColorPickerAdapter

    private lateinit var pauseButton: FloatingActionButton
    private lateinit var pauseDialog: Dialog
    private lateinit var closeDialogButton: ImageView
    private lateinit var retryButton: MaterialCardView
    private lateinit var exitGameButton: MaterialCardView
    private val level: ProgressViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pauseDialog = Dialog(requireContext())
        pauseButton = view.findViewById(R.id.pauseButton)
        pauseButton.setOnClickListener {
            onPressPause()
        }

        val levelProgressViewModel = level.getMaxLevel("pix_art").toInt() -1
        pixarteGame = PixarteModel(levelProgressViewModel)

        pixels = view.findViewById(R.id.pixels)
        colorPicker = view.findViewById(R.id.colorPicker)
        val pixarteLevels = pixarteGame.getLevel()

        pixelsAdapter = PixelsAdapter(requireActivity(), pixarteLevels, pixarteGame.getPixels(), object: PixelsAdapter.PixelClickListener{
            override fun onPixelClicked(position: Int) {
                updateGame(position)
                if(pixarteGame.getHasWon()){
                    //TODO: CHANGE OF LEVEL
                }
            }

        })
        pixels.adapter = pixelsAdapter
        pixels.setHasFixedSize(true)
        pixels.layoutManager = GridLayoutManager(activity, pixarteLevels.getWidth())


        colorPickerAdapter = ColorPickerAdapter(requireActivity(), pixarteLevels, pixarteLevels.getColors(), object: ColorPickerAdapter.ColorPickerClickListener{
            override fun onColorPickerClicked(position: Int) {
                updateColor(position)
            }

        })
        colorPicker.adapter = colorPickerAdapter
        colorPicker.setHasFixedSize(true)
        colorPicker.layoutManager = GridLayoutManager(activity, pixarteLevels.getColors().size)

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

    fun updateGame(position: Int){
        pixarteGame.changeColorPixel(position)
        pixelsAdapter.notifyItemChanged(position)
    }

    fun updateColor(position: Int){
        pixarteGame.changePickerColor(position)
    }

}