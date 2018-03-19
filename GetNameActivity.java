package sheridan.vladvoytenko.multiactivities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by voytenko on 2018-03-05.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class GetNameActivity extends AppCompatActivity{

    public static final int NAME_REQUEST = 1;
    public static final String CALLING_ACTIVITY = "callingActivity";
    public static final String NAME = "Name";

    private EditText mNameEdit;
    private TextView mCallingActivityView;



//    SharedPreferences mPref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
//    SharedPreferences.Editor mEditor = mPref.edit();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout for the layout we created
        setContentView(R.layout.activity_get_name);

        // find the views
        mCallingActivityView = findViewById(R.id.calling_activity_view);
        mNameEdit = findViewById(R.id.name_edit);

        // Get the Intent that called for this Activity to open
        Intent intent = getIntent();

        // Get the data that was sent
        String callingActivity = intent.getExtras().getString(CALLING_ACTIVITY);
        mCallingActivityView.append(" " + callingActivity);
    }

    // Called when button "Send" clicked
    public void onSend(View view) {

        // Get the name typed into the EditText
        String name = mNameEdit.getText().toString();

        //Prevent saving empty string
        if(name.equals("")){
            mNameEdit.setError("Enter Value");
            mNameEdit.requestFocus();
        } else {

            // Define our intention to go back to ActivityMain
            Intent intent = new Intent();

            // Define the String name and the value to assign to it
            intent.putExtra(NAME, name);

            //Save name to file
            //mEditor.putString("Name", "string value");
            //mEditor.commit();

            // Sends data back to the parent and can use RESULT_CANCELED, RESULT_OK, or any
            // custom values starting at RESULT_FIRST_USER. RESULT_CANCELED is sent if
            // this Activity crashes
            setResult(RESULT_OK, intent);

            // Close this Activity
            finish();
        }
    }

    // Called when button "Cancel" clicked
    public void onCancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}