package com.kennett.simplequiz

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

const val SHARED_PREFS_FILE = "com.kennett.simplequiz.PREFS"
const val SCORE_KEY = "com.kennett.simplequiz.SCORE"
const val NEXT_QUESTION_KEY = "com.kennett.simplequiz.NEXT_QUESTION"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startQuiz(view: View) {
        // The score and next question need to be initialized to 0
        // and stored in Shared Preferences before the quiz begins
        val sharedPreferences = getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE)

        val editor = sharedPreferences.edit()
        editor.putInt(SCORE_KEY, 0)
        editor.putInt(NEXT_QUESTION_KEY, 0)
        editor.apply()

        // Start the QuizQuestionActivity after the quiz is initialized
        val intent = Intent(this, QuizQuestionActivity::class.java).apply {
            startActivity(intent)
        }
    }
}
