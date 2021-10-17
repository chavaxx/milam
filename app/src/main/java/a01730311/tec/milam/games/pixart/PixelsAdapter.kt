package a01730311.tec.milam.games.pixart

import a01730311.tec.milam.R
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.min

class PixelsAdapter(
    private val context: Context,
    private val pixarteLevels: PixarteLevels,
    private val pixelsCollection: List<PixelState>,
    private val pixelClickListener : PixelClickListener) :
    RecyclerView.Adapter<PixelsAdapter.ViewHolder>() {

    interface PixelClickListener {
        fun onPixelClicked(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val pixelWidth = parent.width / pixarteLevels.getWidth()
        val pixelHeight = parent.height / pixarteLevels.getHeight()
        val pixelLength = min(pixelHeight, pixelWidth)
        val view = LayoutInflater.from(context).inflate(R.layout.pixel_color, parent, false)
        val layoutParams = view.findViewById<LinearLayout>(R.id.pixelButton).layoutParams
        layoutParams.width = pixelLength
        layoutParams.height = pixelLength
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = pixarteLevels.numPixels

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val pixelColor = itemView.findViewById<LinearLayout>(R.id.pixelButton)
        private val textPixelColor = itemView.findViewById<TextView>(R.id.numberPixel)

        fun bind(position: Int){
            if(pixelsCollection[position].identifier == -1) {
                pixelColor.setBackgroundResource(0)
                return
            }
            if(pixelsCollection[position].hasColor) {
                textPixelColor.setTextColor(Color.parseColor("#ffffff"))
                pixelColor.setBackgroundColor(Color.parseColor(pixelsCollection[position].color))
            }
            if(pixelsCollection[position].hasCorrectColor) {
                textPixelColor.text = ""
            }
            else{
                textPixelColor.text = pixelsCollection[position].identifier.toString()
            }
            pixelColor.setOnClickListener{
                pixelClickListener.onPixelClicked(position)
            }
        }
    }

}
