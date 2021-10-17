package a01730311.tec.milam.games.maze

import a01730311.tec.milam.R
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import androidx.annotation.Nullable

class Maze(context: Context, @Nullable attributeSet: AttributeSet) : View(context, attributeSet), SensorEventListener {
    private var maze : MazeModel
    private var mazeMap : Array<IntArray>
    private var paintSquare = Paint()
    private var paintBall = Paint()
    private var paintLine = Paint()
    private var path = Path()
    private var hasShowedModal = false

    private var listener : MazeListener? = null

    interface MazeListener{
        fun onMazeListened(isFinished: Boolean)
    }

    init {
        //View.inflate(context, R.layout.fragment_maze, this)
        // get screen dimensions
        val metrics : DisplayMetrics = this.resources.displayMetrics

        //get mazeModel obj
        maze = MazeModel(metrics)

        //map
        mazeMap = maze.getMazeMap()

        //ball

        val smAdministrator = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val snsRotation = smAdministrator.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        smAdministrator.registerListener(this, snsRotation, SensorManager.SENSOR_DELAY_FASTEST)

        //paint objects
        paintLine.setColor(Color.GRAY)
        paintLine.setStrokeWidth(3f)

    }

    fun setMazeObjectListener(listener: MazeListener){
        this.listener = listener
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        //draw maze map
        for(i in mazeMap.indices){
            for(j in mazeMap[0].indices){
                if(mazeMap[i][j] == 1){
                    val(left, top, right, bottom) = arrayOf(j*maze.getWidthSquare(), i*maze.getWidthSquare(), (j+1)*maze.getWidthSquare(), (i+1)*maze.getWidthSquare())
                    // fill
                    //paintSquare.setAlpha(255)
                    paintSquare.setStyle(Paint.Style.FILL);
                    paintSquare.setColor(Color.parseColor("#68b4e7"));
                    canvas.drawRect(left, top, right, bottom, paintSquare)
                }
                else if(mazeMap[i][j] == 2){
                    val(left, top, right, bottom) = arrayOf(j*maze.getWidthSquare(), i*maze.getWidthSquare(), (j+1)*maze.getWidthSquare(), (i+1)*maze.getWidthSquare())
                    paintSquare.setColor(Color.parseColor("#fdd99f"));
                    paintSquare.setStyle(Paint.Style.FILL);
                    canvas.drawRect(left, top, right, bottom, paintSquare)

                    val horizontalPlace : Float = j*maze.getWidthSquare()
                    val verticalPlace : Float = i*maze.getWidthSquare()
                    val half: Float = maze.getWidthSquare() / 2

                    path.moveTo(horizontalPlace + half * 0.5f, verticalPlace + half * 0.84f)
                    path.lineTo(horizontalPlace + half * 1.5f, verticalPlace + half * 0.84f)
                    path.lineTo(horizontalPlace + half * 0.68f, verticalPlace + half * 1.45f)
                    path.lineTo(horizontalPlace + half * 1.0f, verticalPlace + half * 0.5f)
                    path.lineTo(horizontalPlace + half * 1.32f,  verticalPlace + half * 1.45f)
                    path.lineTo(horizontalPlace + half * 0.5f, verticalPlace + half * 0.84f)

                    path.close();
                    paintSquare.setColor(Color.BLACK);
                    canvas.drawPath(path, paintSquare)

                }
            }
        }

        //draw ball
        paintBall.color = Color.parseColor("#ff914d")
        canvas.drawCircle(maze.getXBall(), maze.getYBall(), maze.getRadiusBall(), paintBall)

        if(maze.getIsFinished()){
            listener?.onMazeListened(maze.getIsFinished())
        }
        else{
             invalidate()
        }

    }

    override fun onSensorChanged(change: SensorEvent?) {

        val xVelBall : Float = change!!.values[0] * -1f
        val yVelBall : Float = change!!.values[1]
        maze.setVelocity(xVelBall, yVelBall)
        maze.setPosition(xVelBall, yVelBall)

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) { }

}