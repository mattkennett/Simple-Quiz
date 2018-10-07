package com.kennett.simplequiz

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class QuizCompleteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_complete)

        val sharedPreferences = getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE)
        val score = sharedPreferences.getInt(SCORE_KEY, 0)

        // The final score is read from Shared Preferences and displayed using a TextView
        val finalScore = findViewById<TextView>(R.id.finalScoreBox).apply{
            text = score.toString()
        }
    }

    fun restartQuiz(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
