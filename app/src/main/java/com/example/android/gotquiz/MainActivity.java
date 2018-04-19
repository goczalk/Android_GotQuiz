package com.example.android.gotquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static int FINAL_SCORE = 0;

    private int[] checkBoxIdsQuestionTwo = {R.id.question_two_answer_one,
                                            R.id.question_two_answer_two,
                                            R.id.question_two_answer_three};
    private int[] checkBoxIdsQuestionFive = {R.id.question_five_answer_one,
                                             R.id.question_five_answer_two,
                                             R.id.question_five_answer_three};

    private List<CheckBox> checkBoxesQuestionTwo = new ArrayList<>();
    private List<CheckBox> checkBoxesQuestionFive = new ArrayList<>();
    private List<RadioGroup> radioGroups = new ArrayList<>();
    private List<EditText> editTexts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createCheckBoxesList();
        createRadioGroupesList();
        createEditTextesList();
    }

//    zmienic kolor z różowego w buttonach
//    dla nizszych api zeby button nie byl czarny

    public void submit(View v){
        FINAL_SCORE = 0;
        checkQuestionOne();
        checkQuestionTwo();
        checkQuestionThree();
        checkQuestionFour();
        checkQuestionFive();
        checkQuestionSix();
        displayToast();
        clearButtonsAndInputs();
    }

    private void createCheckBoxesList() {
        for(int i = 0; i < checkBoxIdsQuestionTwo.length; i++) {
            checkBoxesQuestionTwo.add((CheckBox) findViewById(checkBoxIdsQuestionTwo[i]));
        }

        for(int i = 0; i < checkBoxIdsQuestionFive.length; i++) {
            checkBoxesQuestionFive.add((CheckBox) findViewById(checkBoxIdsQuestionFive[i]));
        }
    }

    private void createRadioGroupesList() {
        radioGroups.add((RadioGroup) findViewById(R.id.question_one));
        radioGroups.add((RadioGroup) findViewById(R.id.question_six));
    }

    private void createEditTextesList() {
        editTexts.add((EditText) findViewById(R.id.question_three_input));
        editTexts.add((EditText) findViewById(R.id.question_four_input));
    }

    private void clearButtonsAndInputs() {
        for(RadioGroup radioGroup : radioGroups) {
            radioGroup.clearCheck();
        }

        for(CheckBox checkBox : checkBoxesQuestionTwo) {
            checkBox.setChecked(false);
        }

        for(CheckBox checkBox : checkBoxesQuestionFive) {
            checkBox.setChecked(false);
        }

        for(EditText editText : editTexts) {
            editText.setText(null);
        }

    }

    private void checkQuestionOne(){
        int selectedId = radioGroups.get(0).getCheckedRadioButtonId();
        if(selectedId == R.id.question_one_answer_three) {
            FINAL_SCORE++;
        }
    }

    private void checkQuestionTwo(){
        int correctAnswers = 0;
        boolean anyWrong = false;
        for (CheckBox checkBox : checkBoxesQuestionTwo) {
            if(checkBox.isChecked()) {
                if(checkBox.getId() == R.id.question_two_answer_three ||
                        checkBox.getId() == R.id.question_two_answer_two){
                    correctAnswers++;
                }
                else {
                    anyWrong = true;
                }
            }
        }
        if (!anyWrong && correctAnswers == 2) {
            FINAL_SCORE++;
        }
    }

    private void checkQuestionThree() {
        if(editTexts.get(0).getText().toString().trim().equalsIgnoreCase(
                                            getString(R.string.question_three_answer))) {
            FINAL_SCORE++;
        }
    }

    private void checkQuestionFour() {
        if(editTexts.get(1).getText().toString().trim().equalsIgnoreCase(
                                            getString(R.string.question_four_answer))) {
            FINAL_SCORE++;
        }
    }

    private void checkQuestionFive(){
        int correctAnswers = 0;
        boolean anyWrong = false;
        for (CheckBox checkBox : checkBoxesQuestionFive) {
            if(checkBox.isChecked()) {
                if(checkBox.getId() == R.id.question_five_answer_three ||
                        checkBox.getId() == R.id.question_five_answer_two){
                    correctAnswers++;
                }
                else {
                    anyWrong = true;
                }
            }
        }
        if (!anyWrong && correctAnswers == 2) {
            FINAL_SCORE++;
        }
    }

    private void checkQuestionSix(){
        int selectedId = radioGroups.get(1).getCheckedRadioButtonId();
        if(selectedId == R.id.question_six_answer_two) {
            FINAL_SCORE++;
        }
    }

    private void displayToast(){
        Toast.makeText(this, "You got: " +  FINAL_SCORE + " point(s).", Toast.LENGTH_SHORT).show();
    }
}
