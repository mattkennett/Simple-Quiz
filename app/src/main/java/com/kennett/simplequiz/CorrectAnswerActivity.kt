package com.kennett.simplequiz

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class CorrectAnswerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_correct_answer)

        val sharedPreferences = getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE)
        val currentScore = sharedPreferences.getInt(SCORE_KEY, 0)

        // If the user is correct, their score should be incremented
        val editor = sharedPreferences.edit()
        editor.putInt(SCORE_KEY, currentScore + 1)
        editor.apply()
    }

    fun nextQuestion(view: View) {
        var intent = Intent(this, QuizQuestionActivity::class.java)
        startActivity(intent)
    }
}
