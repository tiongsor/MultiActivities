package sheridan.vladvoytenko.multiactivities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  private static final String NAME_VIEW_TEXT = "NameViewText";
  private TextView mNameView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // find the TextView so I can put the users name on it
    mNameView = findViewById(R.id.users_name_message);

    // recover from a device rotation
    if(savedInstanceState != null){
      mNameView.setText(savedInstanceState.getString(NAME_VIEW_TEXT));
    }
      loadPref();
  }


  public void onGetNameClick(View view) {

    // We have to state that are intention is to open another Activity. We do so
    // by passing a Context and the Activity that we want to open

    Intent intent = new Intent(this, GetNameActivity.class);

    // To send data use putExtra with a String name followed by its value
    intent.putExtra(GetNameActivity.CALLING_ACTIVITY, this.getClass().getSimpleName());

    startActivityForResult(intent, GetNameActivity.NAME_REQUEST);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if(requestCode == GetNameActivity.NAME_REQUEST && resultCode == RESULT_OK){

      // Get the users name from the previous Activity
      String name = data.getStringExtra(GetNameActivity.NAME);

      // Add the users name to the end of the textView
      mNameView.setText(getString(R.string.display_the_name,name));

      savedPref("Name", name);
    }
  }

  // preserve the user name message during a device rotation
  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putString(NAME_VIEW_TEXT, mNameView.getText().toString());
  }

  public void savedPref(String key, String value) {
    SharedPreferences mPref = getPreferences(MODE_PRIVATE);
    SharedPreferences.Editor mEditor = mPref.edit();
    mEditor.putString(key, value);
    mEditor.commit();
  }

  public void loadPref() {
    SharedPreferences mPref = getPreferences(MODE_PRIVATE);
    mNameView = findViewById(R.id.users_name_message);
    mNameView.setText("Your name is " + mPref.getString("Name", ""));
  }
}
