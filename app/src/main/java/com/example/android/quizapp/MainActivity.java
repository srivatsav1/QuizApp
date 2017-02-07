package com.example.android.quizapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();}
//
    public void submitOrder(View view) {
        CheckBox DTCheckbox,HCCheckbox,BOCheckbox;
        EditText Answer1 = (EditText) findViewById(R.id.answer1);
        String ans = Answer1.getText().toString();
        Log.v("MainActivity", "City name :" + ans);
        String answer= String.valueOf(Answer1);

//    public void onCheckboxClicked(View view){

        DTCheckbox = (CheckBox) findViewById(R.id.donald_trump);
        HCCheckbox = (CheckBox) findViewById(R.id.hillary_clinton);
        BOCheckbox = (CheckBox) findViewById(R.id.barack_0bama);
        boolean isDT = DTCheckbox.isChecked();
        boolean isHC = HCCheckbox.isChecked();
        boolean isBO = BOCheckbox.isChecked();

//        String answer = question2(isDT,isHC,isBO,ans);
        String Message = YourAnswers(ans, isDT, isHC, isBO,answer);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Answers provided by you:-");
        intent.putExtra(Intent.EXTRA_TEXT, "" + Message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        //displayMessage(Message);
    }

    public void onCheckBoxClick(View view){
        CheckBox DTCheckbox,HCCheckbox,BOCheckbox;
        DTCheckbox = (CheckBox) findViewById(R.id.donald_trump);
        HCCheckbox = (CheckBox) findViewById(R.id.hillary_clinton);
        BOCheckbox = (CheckBox) findViewById(R.id.barack_0bama);

        switch(view.getId()){
            case R.id.donald_trump:
                HCCheckbox.setChecked(false);
                BOCheckbox.setChecked(false);
                break;
            case R.id.hillary_clinton:
                DTCheckbox.setChecked(false);
                BOCheckbox.setChecked(false);
                break;
            case R.id.barack_0bama:
                DTCheckbox.setChecked(false);
                HCCheckbox.setChecked(false);
                break;
        }
    }
    public String YourAnswers(String ans, boolean isDT, boolean isHC, boolean isBO,String answer) {

        String Message = "1.:You answered  \n"+ans+  "\n"    +ques1(ans);
        Message = Message + "  \n 2.:  \n" +question2(isDT,isHC,isBO) ;
        return Message;
    }
    public String ques1(String answer){

        if (answer.equals("Jefferson City")){//||ans=="Jeff City"||ans=="Jeffcity"||ans=="Jeffersoncity"){
            return "correct";

        }
        else {
            if (answer.equals("Jeff City")) {
                return "correct.";
            } else {
                if (answer.equals("Jeffcity")) {
                    return "correct..";
                } else {
                    if (answer.equals("Jeffersoncity")) {
                        return "correct.....";
                    } else {
                        return "Sorry,but the correct ans is Jefferson City";
                    }
                }
            }
        }

    }


    public String question2(boolean isDT, boolean isHC, boolean isBO) {

        if (isDT) {

            return "Donald Trump.Correct Answer";
        }
        else if (isHC ) {
            return "You answered Hillary Clinton.But the president is Donald Trump";
        }
        else if(isBO){
            return " You answered Barack Obama.But the president is Donald Trump";
        }
        return "The correct answer is Mr.Donald Trump";
    }


//    public void displayMessage(String message) {
//        TextView submitOrderTextView = (TextView) findViewById(R.id.answer1);
//        submitOrderTextView.setText(message);
//    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.android.quizapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.android.quizapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
