package com.example.cointoss

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnToss.setOnClickListener {
            //
            var toss = Random.nextInt(1, 3)
            //
            tvCoin.setBackgroundResource(R.drawable.circle_tv)
            tvCoin.text = "coin"
//            tvCoin.setBackgroundColor(Color.parseColor("#4CAF50"))
            //
            coinAnimation()
            //
            val handler = Handler()
            handler.postDelayed({ convert(toss) }, 2000)

        }

    }

    private fun coinAnimation(){
        tvCoin.animate().apply {
            duration = 200
            rotationXBy(9000f)
            translationYBy(-500f)
        }.withStartAction {
            tvCoin.animate().apply {
                duration = 2000
//                rotationYBy(-3600f)
                rotationBy(-3600f)
            }
        }.withEndAction {
            tvCoin.animate().apply {
                rotationXBy(-9000f)
                translationYBy(500f)

            }
        }.start()


    }

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
    }


}