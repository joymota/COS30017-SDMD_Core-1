package com.example.assignment1

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var opResult: Int = 0

    private val mediaPlayer: MediaPlayer by lazy {
        MediaPlayer.create(this, R.raw.crash_impact_sweetener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val plus1 = findViewById<Button>(R.id.plus)
        val minus1 = findViewById<Button>(R.id.minus)
        val reset = findViewById<Button>(R.id.reset)
        val answer = findViewById<TextView>(R.id.answer)

        answer.text = opResult.toString()

        savedInstanceState?.let {
            opResult = savedInstanceState.getInt("ANSWER")
            answer.text = opResult.toString()
        }

        plus1.setOnClickListener {
            plus1()
            answer.text = opResult.toString()
            if(opResult == 15) {
                mediaPlayer.start()
                Log.i("LIFECYCLE", "soundstate $opResult")
            }
        }

        minus1.setOnClickListener {
            minus1()
            answer.text = opResult.toString()
        }

        reset.setOnClickListener {
            reset()
            answer.text = opResult.toString()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("ANSWER",opResult)
        Log.i("LIFECYCLE", "saveInstanceState $opResult")
    }

    private fun plus1() {
        if (opResult < 15) {
            opResult++
        }
    }

    private fun minus1() {
        if (opResult > 0) {
            opResult--
        }

    }

    private fun reset() {
        opResult = 0
    }

}