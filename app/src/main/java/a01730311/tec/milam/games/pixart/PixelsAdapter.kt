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

    //gives a length to every pixel
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

    //it indicates how many pixels has to render
    override fun getItemCount(): Int = pixarteLevels.numPixels

    //it changes the state of every single element
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val pixelColor = itemView.findViewById<LinearLayout>(R.id.pixelButton)
        private val textPixelColor = itemView.findViewById<TextView>(R.id.numberPixel)

        fun bind(position: Int){
            //if the pixel is not valid, it doesn't have border or a color
            if(pixelsCollection[position].identifier == -1) {
                pixelColor.setBackgroundResource(0)
                return
            }
            //if the pixel has a color it renders it
            if(pixelsCollection[position].hasColor) {
                textPixelColor.setTextColor(Color.parseColor("#ffffff"))
                pixelColor.setBackgroundColor(Color.parseColor(pixelsCollection[position].color))
            }
            //if it has the correct color the text becomes empty
            if(pixelsCollection[position].hasCorrectColor) {
                textPixelColor.text = ""
            }
            //if not it receives its identifier
            else{
                textPixelColor.text = pixelsCollection[position].identifier.toString()
            }
            //it sets the click listener
            pixelColor.setOnClickListener{
                pixelClickListener.onPixelClicked(position)
            }
        }
    }

}
