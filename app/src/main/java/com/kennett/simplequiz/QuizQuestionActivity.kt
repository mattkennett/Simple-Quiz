package com.kennett.simplequiz

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class QuizQuestionActivity : AppCompatActivity() {
    // The questions for the quiz are stored as private member variables
    private val questionOne = QuizQuestion("Who is Charlie in love with?",
            arrayOf("Maureen Ponderosa",
                    "The Waitress",
                    "Doyle McPoyle",
                    "Artemis"),
            1
    )

    private val questionTwo = QuizQuestion("What Philly Celebrity would you most want to have a drink with?",
            arrayOf("Chase Utley",
                    "Questlove",
                    "Bill Cosby",
                    "Kevin Hart"),
            2
    )

    private val questionThree = QuizQuestion("What is the greatest band in the world?",
            arrayOf("Rick Astley",
                    "Led Zeppelin",
                    "Puddle of Mudd",
                    "Chumbawamba"),
            3
    )

    private val quiz = arrayOf(questionOne, questionTwo, questionThree)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        // The current score and index of the next question need to be
        // read from Shared Preferences
        val sharedPreferences = getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE)
        val currentScore = sharedPreferences.getInt(SCORE_KEY, 0)
        val nextQuestion = sharedPreferences.getInt(NEXT_QUESTION_KEY, 0)

        // If there are no more questions, the QuizCompleteActivity needs to be
        if(nextQuestion == quiz.size) {
            val intent = Intent(this, QuizCompleteActivity::class.java).apply {
                startActivity(intent)
            }
        }
        else {
            // Show the score at the top of the View
            val scoreBox = findViewById<TextView>(R.id.scoreBox).apply {
                text = currentScore.toString()
            }

            // Create handles to the incorrect and correct activities
            val intentCorrect = Intent(this, CorrectAnswerActivity::class.java)
            val intentIncorrect = Intent(this, IncorrectAnswerActivity::class.java)

            val questionText = findViewById<TextView>(R.id.questionText).apply {
                text = quiz[nextQuestion].question
            }

            // Collect the identifiers of all Buttons in an array
            val idButton0 = R.id.buttonOption1
            val idButton1 = R.id.buttonOption2
            val idButton2 = R.id.buttonOption3
            val idButton3 = R.id.buttonOption4
            val buttons = arrayOf(idButton0, idButton1, idButton2, idButton3)

            for(i in 0..3) {
                // Get a handle to the current button and set its text
                var currentButton = findViewById<Button>(buttons[i]).apply {
                    text = quiz[nextQuestion].options[i]
                    // If the current answer is the correct one, set the
                    // onClickListener of the button to the CorrectAnswerActivity
                    // Otherwise, set it to IncorrectAnswerActivity
                    if (i == quiz[nextQuestion].correct) {
                        setOnClickListener {
                            startActivity(intentCorrect)
                        }
                    }
                    else {
                        setOnClickListener {
                            startActivity(intentIncorrect)
                        }
                    }
                }
            }

            // Increment the index of the next question and store it in Shared Preferences
            val editor = sharedPreferences.edit()
            editor.putInt(NEXT_QUESTION_KEY, nextQuestion + 1)
            editor.apply()
        }
    }
}
