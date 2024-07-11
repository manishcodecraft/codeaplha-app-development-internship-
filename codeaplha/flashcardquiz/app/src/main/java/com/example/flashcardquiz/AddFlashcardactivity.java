package com.example.flashcardquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddFlashcardactivity extends AppCompatActivity {

    private EditText questionEditText, option1EditText, option2EditText, option3EditText, option4EditText, correctAnswerEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_questions_dialog);

        questionEditText = findViewById(R.id.editText_question);
        option1EditText = findViewById(R.id.editText_option1);
        option2EditText = findViewById(R.id.editText_option2);
        option3EditText = findViewById(R.id.editText_option3);
        option4EditText = findViewById(R.id.editText_option4);
        correctAnswerEditText = findViewById(R.id.editText_correctAnswer);
        saveButton = findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveQuestion();
            }
        });
    }

    private void saveQuestion() {
        String question = questionEditText.getText().toString();
        String option1 = option1EditText.getText().toString();
        String option2 = option2EditText.getText().toString();
        String option3 = option3EditText.getText().toString();
        String option4 = option4EditText.getText().toString();
        String correctAnswer = correctAnswerEditText.getText().toString();

        // Create a Questions object and add it to the QuestionBank
        Questions newQuestion = new Questions(question, option1, option2, option3, option4, correctAnswer);
        QuestionBank.getInstance().addQuestion(newQuestion);

        Toast.makeText(this, "Question added successfully", Toast.LENGTH_SHORT).show();
        finish(); // Go back to MainActivity
    }
}
