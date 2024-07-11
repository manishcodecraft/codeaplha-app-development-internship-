package com.example.flashcardquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;
    private TextView scoreTextView;
    private RadioGroup optionsRadioGroup;
    private RadioButton option1, option2, option3, option4;
    private Button showAnswerButton, addFlashcardButton, nextButton;

    private List<Questions> questionBank;
    private int currentIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.questions);
        scoreTextView = findViewById(R.id.score);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        showAnswerButton = findViewById(R.id.b1);
        addFlashcardButton = findViewById(R.id.b2);
        nextButton = findViewById(R.id.b3);

        questionBank = QuestionBank.getInstance().getQuestions();

        showAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
            }
        });

        addFlashcardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddFlashcardActivity();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex + 1) % questionBank.size();
                updateQuestion();
            }
        });

        updateQuestion();
    }

    private void updateQuestion() {
        if (questionBank.size() > 0) {
            Questions currentQuestion = questionBank.get(currentIndex);
            questionTextView.setText(currentQuestion.getQuestion());
            option1.setText(currentQuestion.getOption1());
            option2.setText(currentQuestion.getOption2());
            option3.setText(currentQuestion.getOption3());
            option4.setText(currentQuestion.getOption4());
            resetRadioButtons();
        } else {
            questionTextView.setText("No questions available. Please add some questions.");
            optionsRadioGroup.clearCheck();
            option1.setText("");
            option2.setText("");
            option3.setText("");
            option4.setText("");
        }
    }

    private void resetRadioButtons() {
        optionsRadioGroup.clearCheck();
    }

    private void checkAnswer() {
        int selectedId = optionsRadioGroup.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedId);
            String selectedOption = selectedRadioButton.getText().toString();
            String correctAnswer = questionBank.get(currentIndex).getCorrectAnswer();

            if (selectedOption.equals(correctAnswer)) {
                score++;
                Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Incorrect!", Toast.LENGTH_SHORT).show();
            }

            scoreTextView.setText("Score: " + score);
        } else {
            Toast.makeText(MainActivity.this, "Please select an option.", Toast.LENGTH_SHORT).show();
        }
    }

    private void openAddFlashcardActivity() {
        Intent intent = new Intent(MainActivity.this, AddFlashcardactivity.class);
        startActivity(intent);
    }
}
