package com.kennett.simplequiz

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class IncorrectAnswerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incorrect_answer)
    }

    fun nextQuestion(view: View) {
        var intent = Intent(this, QuizQuestionActivity::class.java)
        startActivity(intent)
    }
}
