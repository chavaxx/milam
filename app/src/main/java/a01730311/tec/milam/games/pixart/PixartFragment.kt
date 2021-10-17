package a01730311.tec.milam.games.pixart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import a01730311.tec.milam.R
import a01730311.tec.milam.components.Modal
import a01730311.tec.milam.screens.home.ProgressViewModel
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class PixartFragment : Fragment() {

    private lateinit var pixels : RecyclerView
    private lateinit var colorPicker : RecyclerView

    private val pixarteGame : PixarteModel = PixarteModel()
    private lateinit var pixelsAdapter : PixelsAdapter
    private lateinit var colorPickerAdapter : ColorPickerAdapter

    private lateinit var pauseButton: FloatingActionButton

    private lateinit var modal: Modal

    private val progress: ProgressViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        modal = Modal(requireContext(), R.id.pixartFragment, findNavController(), progress, 1)
        pauseButton = view.findViewById(R.id.pauseButton)
        pauseButton.setOnClickListener {
            modal.showPauseMenu()
        }

        pixels = view.findViewById(R.id.pixels)
        colorPicker = view.findViewById(R.id.colorPicker)
        val pixarteLevels = pixarteGame.getLevel()

        pixelsAdapter = PixelsAdapter(requireActivity(), pixarteLevels, pixarteGame.getPixels(), object: PixelsAdapter.PixelClickListener{
            override fun onPixelClicked(position: Int) {
                updateGame(position)
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