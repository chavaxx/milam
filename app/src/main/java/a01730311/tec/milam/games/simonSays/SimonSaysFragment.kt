package a01730311.tec.milam.games.simonSays

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import a01730311.tec.milam.R
import android.animation.*
import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SimonSaysFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SimonSaysFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var data : HashMap<Int, Pair<ImageView, MediaPlayer>> = HashMap()
    private lateinit var scoreLbl : TextView
    private var game : simonSaysModel = simonSaysModel()
    private lateinit var maxScore : SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val view: View =  inflater.inflate(R.layout.fragment_simon_says, container, false)
        val startButton : Button = view.findViewById(R.id.startBtn)
        startButton.setOnClickListener{
            onTap(R.id.startBtn)
        }
        loadData(view)
        maxScore = activity?.getPreferences(Context.MODE_PRIVATE)!!
        game.setScore(maxScore.getInt("savedScore",0))
        enableButtons(false)

        scoreLbl = view.findViewById(R.id.scoreLbl)
        scoreLbl.text = "Max score: " + game.getScore()


        return view
    }

    fun loadData(view : View) {
        data[R.id.greenBtn] = Pair(view.findViewById(R.id.greenBtn), MediaPlayer.create(activity, R.raw.green))
        data[R.id.blueBtn] = Pair(view.findViewById(R.id.blueBtn), MediaPlayer.create(activity, R.raw.blue))
        data[R.id.yellowBtn] =
            Pair(view.findViewById(R.id.yellowBtn), MediaPlayer.create(activity, R.raw.yellow))
        data[R.id.redBtn] =
            Pair(view.findViewById(R.id.redBtn), MediaPlayer.create(activity, R.raw.red))

        data[R.id.greenBtn]?.first?.setOnClickListener{
            onTap(R.id.greenBtn)
        }
        data[R.id.blueBtn]?.first?.setOnClickListener{
            onTap(R.id.blueBtn)
        }
        data[R.id.yellowBtn]?.first?.setOnClickListener{
            onTap(R.id.yellowBtn)
        }
        data[R.id.redBtn]?.first?.setOnClickListener{
            onTap(R.id.redBtn)
        }
    }

    fun onTap(id : Int) {
        var buttonID: Int = id
        if (buttonID == R.id.startBtn) {
            scoreLbl.text = "Puntuación: " + game.getScore()
            buttonID = game.startGame()
            println(buttonID)
            data[buttonID]?.let {
                flashAndPlay(
                    it.second,
                    it.first,
                    0
                )
            }
            enableButtons(true)
        } else {
            game.validateButton(buttonID)
            if (game.getIsCorrect()) {
                executeSequence()
                enableButtons(true)
            } else if (!game.getInGame()) {
                SaveState()
                scoreLbl.text = "Has pérdido..."
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        data[R.id.redBtn]?.second?.release()
        data[R.id.blueBtn]?.second?.release()
        data[R.id.yellowBtn]?.second?.release()
        data[R.id.greenBtn]?.second?.release()
    }

    fun enableButtons(state: Boolean) {
        data[R.id.redBtn]?.first?.isEnabled = state
        data[R.id.blueBtn]?.first?.isEnabled = state
        data[R.id.yellowBtn]?.first?.isEnabled = state
        data[R.id.greenBtn]?.first?.isEnabled = state

    }

    fun SaveState() {
        val editor: SharedPreferences.Editor = maxScore.edit()
        editor.putInt("savedScore", game.getScore())
        editor.apply()
    }

    private fun executeSequence() {
        val sequence: ArrayList<Int>? = game.getSequence()
        enableButtons(false)
        if (sequence != null) {
            for (i in 0 until sequence.size) {
                data[sequence[i]]?.let {
                    flashAndPlay(
                        it.second,
                        it.first,
                        1000 * (i + 1)
                    )

                }
            }
        }
    }

    private fun flashAndPlay(newPlayer: MediaPlayer, newButton: ImageView, delay: Int) {
        val player: MediaPlayer = newPlayer

        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 0.95f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.95f)
        val alpha = PropertyValuesHolder.ofFloat(View.ALPHA, 0.8f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(
            newButton, scaleX, scaleY, alpha)

        animator.duration = 400
        animator.repeatCount = 1
        animator.repeatMode = ValueAnimator.REVERSE
        animator.startDelay = delay.toLong()
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                super.onAnimationStart(animation)
                player.start()
            }
        })
        animator.start()

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SimonSaysFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SimonSaysFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}