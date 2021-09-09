package a01730311.tec.milam

import a01730311.tec.milam.adapter.GameCardAdapter
import a01730311.tec.milam.data.Datasource
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Initialize data.
        val myGameCards = Datasource().loadGameCards()

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_cards)
        recyclerView.adapter = GameCardAdapter(this, myGameCards)

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true)
    }
}