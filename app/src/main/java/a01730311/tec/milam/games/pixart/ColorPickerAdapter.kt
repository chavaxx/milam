package a01730311.tec.milam.games.pixart

import a01730311.tec.milam.R
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import kotlin.math.min

class ColorPickerAdapter(
    private val context: Context,
    private val pixarteLevels: PixarteLevels,
    private val colors: Array<String>,
    private val colorPickerClickListener: ColorPickerAdapter.ColorPickerClickListener) :
    RecyclerView.Adapter<ColorPickerAdapter.ViewHolder>(){

    private var selectedColor: Int = 0

    interface ColorPickerClickListener {
        fun onColorPickerClicked(position: Int)
    }

    companion object{
        private const val MARGIN_SIZE = 10
    }

    //it defines the length of every cell according to the min of height and width,
    //also defines the margins
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val colorPickWidth = parent.width / colors.size - (2 * MARGIN_SIZE)
        val colorPickHeight = parent.height / 1
        val colorPickLength = min(colorPickHeight.toUInt(), colorPickWidth.toUInt())
        val view = LayoutInflater.from(context).inflate(R.layout.color_pick, parent, false)
        val layoutParams = view.findViewById<TextView>(R.id.colorText).layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.setMargins(MARGIN_SIZE, 0, MARGIN_SIZE, 0)
        layoutParams.width = colorPickLength.toInt()
        layoutParams.height = colorPickLength.toInt()

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    //it indicates how many color picker has to render
    override fun getItemCount(): Int = colors.size

    //it changes the state of every single element
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val picker = itemView.findViewById<MaterialCardView>(R.id.colorPick)
        private val text = itemView.findViewById<TextView>(R.id.colorText)

        fun bind(position: Int){
            //it gives the position and background color according to the string in the array colors
            picker.setCardBackgroundColor(Color.parseColor(colors[position]))
            text.text ="${position + 1}"
            //when picker is clicked, it calls its listener and it makes the cells of
            //the pixels becomes of its color

            if (selectedColor == position) {
                picker.strokeWidth = 5
            } else {
                picker.strokeWidth = 0
            }


            picker.setOnClickListener{
                if (selectedColor >= 0) notifyItemChanged(selectedColor)
                selectedColor = position
                notifyItemChanged(selectedColor)

                colorPickerClickListener.onColorPickerClicked(position)
            }
        }
    }

}
