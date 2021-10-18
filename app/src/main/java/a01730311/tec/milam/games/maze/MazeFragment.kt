package a01730311.tec.milam.games.maze

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import a01730311.tec.milam.R
import a01730311.tec.milam.components.Modal
import a01730311.tec.milam.screens.home.ProgressViewModel
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MazeFragment : Fragment() {

    private lateinit var pauseButton: FloatingActionButton
    private lateinit var modal: Modal
    private lateinit var maze: Maze
    private val progress: ProgressViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        modal = Modal(requireContext(), R.id.mazeFragment, findNavController(), progress, 5)
        pauseButton = view.findViewById(R.id.pauseButton)
        pauseButton.setOnClickListener {
            modal.showPauseMenu()
        }

        val levelProgressViewModel = progress.getMaxLevel("maze").toInt() -1

        maze = view.findViewById(R.id.mazeBoard)
        maze.recreate(levelProgressViewModel)
        maze.setMazeObjectListener(object: Maze.MazeListener{
            override fun onMazeListened(isFinished: Boolean) {
                if(isFinished) {
                    modal.showSuccessMenu("maze", 0)
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_maze, container, false)

        return view
    }

}