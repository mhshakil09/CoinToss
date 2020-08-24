package com.example.cointoss

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    // to declare globally
    private var historyDisplay : String = " "
    private var tempHistory : Int = 0
    private var tempHistory1 : Int = 0
    private var tempHistory2 : Int = 0
    private var tempHistory3 : Int = 0
    private var tempCount : Int = 0
    private var tempHistory3Count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        // to hide the notification bar from the app activity
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
//        // to hide the title bar from the app activity
//        supportActionBar?.hide()

//      disabling view of Views
        tvHistory.visibility = View.INVISIBLE
        tvHistory1.visibility = View.INVISIBLE
        tvHistory2.visibility = View.INVISIBLE
        tvHistory3.visibility = View.INVISIBLE



//        tvHistoryDisplay.text = "none"

        btnToss.setOnClickListener {

            //
            tempHistoryCheck()

            //
            var toss = Random.nextInt(1, 3)
            tempHistory = toss


            //
            tvCoin.setBackgroundResource(R.drawable.circle_tv)
            tvCoin.text = "coin"

//            tvCoin.setBackgroundColor(Color.parseColor("#4CAF50"))

            //
            coinAnimation(toss)


        }

    }

    // // to hide the notification bar from the app activity
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus){
            this.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
    }


    public fun tvHistoryUpdate (){

        tempHistory3 = tempHistory2
        tempHistory2 = tempHistory1
        tempHistory1 = tempHistory

        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val fadeOutAndIn = AnimationUtils.loadAnimation(this, R.anim.fade_out_and_fade_in)

        when (tempHistory3Count){
            0 -> {
                when (tempHistory3){
                    1 -> {
                        tvHistory3.text = "head"
                        tvHistory3.setBackgroundResource(R.drawable.circle_tv_head)
                        tvHistory3.visibility = View.VISIBLE
                        tempHistory3Count = 1
                    }
                    2 -> {
                        tvHistory3.text = "tail"
                        tvHistory3.setBackgroundResource(R.drawable.circle_tv_tail)
                        tvHistory3.visibility = View.VISIBLE
                        tempHistory3Count = 1
                    }
                }
            }
            1 -> {
                when (tempHistory3){
                    1 -> {
                        tvHistory3.startAnimation(fadeOutAndIn)
//                        tvHistory3.startAnimation(fadeIn)
                        val handler = Handler()
                        handler.postDelayed({
                            tvHistory3.text = "head"
                            tvHistory3.setBackgroundResource(R.drawable.circle_tv_head)
                        }, 500)
//                        tvHistory3.visibility = View.VISIBLE
                    }
                    2 -> {
                        tvHistory3.startAnimation(fadeOutAndIn)
//                        tvHistory3.startAnimation(fadeIn)
                        val handler = Handler()
                        handler.postDelayed({
                            tvHistory3.text = "tail"
                            tvHistory3.setBackgroundResource(R.drawable.circle_tv_tail)
                        }, 500)
//                        tvHistory3.visibility = View.VISIBLE
                    }
                }
            }

        }

        when (tempHistory2){
            1 -> {
                tvHistory2.text = "head"
                tvHistory2.setBackgroundResource(R.drawable.circle_tv_head)
                tvHistory2.visibility = View.VISIBLE
            }
            2 -> {
                tvHistory2.text = "tail"
                tvHistory2.setBackgroundResource(R.drawable.circle_tv_tail)
                tvHistory2.visibility = View.VISIBLE
            }

        }

        when (tempHistory1){
            1 -> {
                tvHistory1.text = "head"
                tvHistory1.startAnimation(fadeIn)
                tvHistory1.setBackgroundResource(R.drawable.circle_tv_head)
                tvHistory1.visibility = View.VISIBLE
            }
            2 -> {
                tvHistory1.text = "tail"
                tvHistory1.startAnimation(fadeIn)
                tvHistory1.setBackgroundResource(R.drawable.circle_tv_tail)
                tvHistory1.visibility = View.VISIBLE
            }
        }

    }


    private fun tempHistoryCheck (){

        btnToss.isEnabled = false

        if (tempCount == 1) {
            tvHistoryUpdate()

//          fade in and starts animation
            tvHistory.visibility = View.VISIBLE
        }
        tempCount = 1

    }


    // animating like coin is flipping
    private fun coinAnimation(toss: Int){

        //perfectly working
        tvCoin.animate().apply {
            duration = 500
            rotationXBy(9000f)
            rotationYBy(2500f)
            translationYBy(-500f)
        }.withStartAction {
            tvCoin.animate().apply {
                duration = 2000
                rotationBy(-3600f)
            }
        }.withEndAction {
            tvCoin.animate().apply {
                rotationXBy(-9000f)
                rotationYBy(-2500f)
                translationYBy(500f)

            }
        }.start()

        // trying to display the output at the end of the animation
        val handler = Handler()
        handler.postDelayed({ convert(toss) }, 2400)


    }

    // showing the output
    private fun convert(toss: Int) {
        when (toss) {
            1 -> {
                tvCoin.setBackgroundResource(R.drawable.circle_tv_head)
                tvCoin.text = "head"

            }
            2 -> {
                tvCoin.setBackgroundResource(R.drawable.circle_tv_tail)
                tvCoin.text = "tail"

            }
        }

        val handler = Handler()
        handler.postDelayed({ btnToss.isEnabled = true }, 200)
    }


}