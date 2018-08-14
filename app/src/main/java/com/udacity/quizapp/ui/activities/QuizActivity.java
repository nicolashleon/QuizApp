package com.udacity.quizapp.ui.activities;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.udacity.quizapp.QuizGrader;
import com.udacity.quizapp.R;
import com.udacity.quizapp.ui.adapters.QuestionsAdapter;
import com.udacity.quizapp.ui.models.Question;
import com.udacity.quizapp.ui.models.Quiz;

import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private final static String QUESTIONS_KEY = "QUESTIONS_KEY";

    private RecyclerView recyclerView;
    private QuestionsAdapter adapter;
    private Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_activity_quiz);
        setSupportActionBar(toolbar);

        adapter = new QuestionsAdapter();
        recyclerView = findViewById(R.id.recycler_view_questions);
        submitButton = findViewById(R.id.btn_submit);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final Quiz quiz = ViewModelProviders.of(this).get(Quiz.class);

        MutableLiveData<List<Question>> data = null;

        if (savedInstanceState != null && savedInstanceState.containsKey(QUESTIONS_KEY)) {
            data = quiz.setQuizQuestions((List<Question>) savedInstanceState.getSerializable(QUESTIONS_KEY));
        } else {
            data = quiz.getCyclistQuizQuestions(this);
        }

        data.observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(@Nullable List<Question> questions) {
                adapter.addAllItemsAndNotify(questions);
            }
        });

        if (quiz.isGraded()) {
            submitButton.setText(R.string.txt_finish_quiz);
        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quiz.isGraded()) {
                    QuizActivity.this.finish();
                } else {
                    QuizGrader quizGrader = new QuizGrader();
                    Pair<Integer, Integer> grade = quizGrader.gradeQuiz(adapter.getQuestions());
                    Toast.makeText(QuizActivity.this, getString(R.string.txt_grade_quiz, grade.first, grade.second), Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                    submitButton.setText(R.string.txt_finish_quiz);
                    quiz.setGraded(true);
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(QUESTIONS_KEY, adapter.getQuestions());
        super.onSaveInstanceState(outState);
    }
}
