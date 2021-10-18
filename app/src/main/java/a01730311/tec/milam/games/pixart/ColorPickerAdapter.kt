package a01730311.tec.milam.games.pixart

import a01730311.tec.milam.R
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.min

class ColorPickerAdapter(
    private val context: Context,
    private val pixarteLevels: PixarteLevels,
    private val colors: Array<String>,
    private val colorPickerClickListener: ColorPickerAdapter.ColorPickerClickListener) :
    RecyclerView.Adapter<ColorPickerAdapter.ViewHolder>(){

    interface ColorPickerClickListener {
        fun onColorPickerClicked(position: Int)
    }

    companion object{
        private const val MARGIN_SIZE = 10
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val colorPickWidth = parent.width / colors.size - (2 * MARGIN_SIZE)
        val colorPickHeight = parent.height / 1
        val colorPickLength = min(colorPickHeight, colorPickWidth)
        val view = LayoutInflater.from(context).inflate(R.layout.color_pick, parent, false)
        val layoutParams = view.findViewById<TextView>(R.id.colorText).layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.setMargins(MARGIN_SIZE, 0, MARGIN_SIZE, 0)
        layoutParams.width = colorPickLength
        layoutParams.height = colorPickLength

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = colors.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val picker = itemView.findViewById<TextView>(R.id.colorText)

        fun bind(position: Int){
            picker.setBackgroundColor(Color.parseColor(colors[position]))
            picker.setText("${position+1}")
            picker.setOnClickListener{
                colorPickerClickListener.onColorPickerClicked(position)
            }
        }
    }

}
