package a01730311.tec.milam.games.simonSays

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import a01730311.tec.milam.R
import a01730311.tec.milam.screens.home.ProgressViewModel
import android.animation.*
import android.media.MediaPlayer
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.runBlocking
import org.w3c.dom.Text


class SimonSaysFragment : Fragment() {


    private var data : HashMap<Int, Pair<ImageView, MediaPlayer>> = HashMap()
    private lateinit var turnLabel : TextView
    private var game : SimonSaysModel = SimonSaysModel()
    private var animationsRunning: Int = 0

    // TODO: MANEJA EL PROGRESO DE LOS USUARIOS
    private val progressViewModel: ProgressViewModel by activityViewModels()
    private lateinit var currentScore: TextView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val view: View =  inflater.inflate(R.layout.fragment_simon_says, container, false)

        turnLabel = view.findViewById(R.id.turnLabel)
        turnLabel.text = "Espera..."
        currentScore = view.findViewById(R.id.simon_current_score)
        loadData(view)

        game.setMaxScore(progressViewModel.getScore("simon_says")!!)

        enableButtons(false)

        initGame()




        val pauseButton : FloatingActionButton = view.findViewById(R.id.pauseButton)
        pauseButton.setOnClickListener{
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Pausa")
                .setNegativeButton("Salir del juego") { dialog, which ->
                    findNavController().popBackStack()
                }
                .setNeutralButton("Reintentar")  { dialog, which ->
                    initGame()
                }
                .show()

        }

        return view
    }

    fun onFailSequence() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Intenta de nuevo")
            .setNegativeButton("Salir del juego") { dialog, which ->
                findNavController().popBackStack()
            }
            .setPositiveButton("Reintentar") { dialog, which ->
                initGame()
            }
            .show()
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

    fun initGame() {

        val buttonID: Int = game.startGame()

        data[buttonID]?.let {
            flashAndPlay(
                it.second,
                it.first,
                0
            )
        }
        animationsRunning = 0
        enableButtons(true)
    }

    fun onTap(buttonID : Int) {

            data[buttonID]?.let {
                flashAndPlay(
                    it.second,
                    it.first,
                    0
                )
            }

            game.validateButton(buttonID)

            if (game.getIsCorrect()) {
                currentScore.text = "Puntuación actual: " + game.getCurrentScore()
                executeSequence()

            } else if (!game.getInGame()) {
                progressViewModel.setScore("simon_says",game.getMaxScore())
                onFailSequence()
                //scoreLbl.text = "Has pérdido..."
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



    private fun executeSequence() {

        runBlocking {
            val sequence: ArrayList<Int>? = game.getSequence()
            animationsRunning = sequence!!.size
                turnLabel.text = "Espera..."
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

    }


    private fun flashAndPlay(newPlayer: MediaPlayer, newButton: ImageView, delay: Int) {
        val player: MediaPlayer = newPlayer

        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 0.95f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.95f)
        val alpha = PropertyValuesHolder.ofFloat(View.ALPHA, 0.3f)

        val animator = ObjectAnimator.ofPropertyValuesHolder(
            newButton, scaleX, scaleY, alpha
        )

        animator.duration = 300
        animator.repeatCount = 1
        animator.repeatMode = ValueAnimator.REVERSE
        animator.startDelay = delay.toLong()
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                super.onAnimationStart(animation)
                player.start()
            }

            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)


                if (animationsRunning == delay/1000) {
                    animationsRunning = 0
                    turnLabel.text = "Tu turno"
                    enableButtons(true)
                }
            }
        })
        animator.start()


    }


}