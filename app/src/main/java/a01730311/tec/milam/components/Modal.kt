package a01730311.tec.milam.components

import a01730311.tec.milam.R
import a01730311.tec.milam.screens.home.ProgressViewModel
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.NavController
import com.google.android.material.card.MaterialCardView

// class to display modals in games
class Modal(context: Context, val id: Int, val findNavController: NavController, private val scoreViewModel: ProgressViewModel, private val numberOfLevels: Int) {
    private val pauseDialog: Dialog = Dialog(context)
    private lateinit var closeDialogButton: ImageView
    private lateinit var retryButton: MaterialCardView
    private lateinit var exitGameButton: MaterialCardView
    private lateinit var nextLevelButton: MaterialCardView

    private fun bindCommonButtons() {
        // sets background and size
        pauseDialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        pauseDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // bind buttons
        retryButton = pauseDialog.findViewById(R.id.retry_button)
        exitGameButton = pauseDialog.findViewById(R.id.exit_game_button)



        // reload game
        retryButton.setOnClickListener {
            pauseDialog.hide()
            findNavController.run {
                popBackStack()
                navigate(id)
            }
        }


    }

    fun showPauseMenu() {
        //build dialog
        pauseDialog.setContentView(R.layout.pause_dialog)
        bindCommonButtons()


        closeDialogButton = pauseDialog.findViewById(R.id.close_pause_dialog)
        // close dialog
        closeDialogButton.setOnClickListener {
            pauseDialog.hide()
        }

        //exit game
        exitGameButton.setOnClickListener {
            pauseDialog.hide()

            findNavController.popBackStack()

        }

        // shows dialog
        pauseDialog.show()
    }

    fun showFailureMenu() {
        pauseDialog.setContentView(R.layout.failure_dialog)
        bindCommonButtons()

        //exit game
        exitGameButton.setOnClickListener {
            pauseDialog.hide()

            findNavController.popBackStack()

        }

        pauseDialog.show()
    }


    fun showSuccessMenu(game: String, score: Int) {
        //build dialog
        pauseDialog.setContentView(R.layout.success_dialog)

        bindCommonButtons()

        // bind next level button
        nextLevelButton = pauseDialog.findViewById(R.id.success_game_button)
        nextLevelButton.setOnClickListener {

            if(scoreViewModel.getMaxLevel(game).toInt() < numberOfLevels) {
                scoreViewModel.setScore(game,score,(scoreViewModel.getMaxLevel(game).toInt() + 1).toString())
            }
            pauseDialog.hide()
            findNavController.run {
                popBackStack()
                navigate(id)
            }

        }

        // exits game and store score
        exitGameButton.setOnClickListener {

            if(scoreViewModel.getMaxLevel(game).toInt() < numberOfLevels) {
                scoreViewModel.setScore(game,score,(scoreViewModel.getMaxLevel(game).toInt() + 1).toString())
            }

            pauseDialog.hide()

            findNavController.popBackStack()

        }

        pauseDialog.show()
    }

}