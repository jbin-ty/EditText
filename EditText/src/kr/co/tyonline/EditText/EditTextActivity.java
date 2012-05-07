package kr.co.tyonline.EditText;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class EditTextActivity extends Activity {
	EditText edit; 
	TextView text, ID, PASSWORD;
	CheckBox checkbox;
	boolean bChkbox;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        bChkbox = false;
        edit = (EditText)findViewById(R.id.edittext);
    	text = (TextView)findViewById(R.id.textview);
    	ID = (TextView)findViewById(R.id.IDedit);
    	PASSWORD = (TextView)findViewById(R.id.PWedit);
    	
    	checkbox = (CheckBox)findViewById(R.id.save);
    	
    	SharedPreferences pref = getSharedPreferences("PrefTest", 0);
    	String id = pref.getString("id", "");
    	ID.setText(id);
    	
    	int pw = pref.getInt("pw", 1234);
    	PASSWORD.setText("");
    	
        findViewById(R.id.button).setOnClickListener(mClickListener);
        findViewById(R.id.save).setOnClickListener(mClickListener);
    }
    Button.OnClickListener mClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.button : 
				Toast.makeText(getApplicationContext(), edit.getText().toString(), Toast.LENGTH_SHORT).show();
				text.setText(edit.getText().toString());
				break;
			case R.id.save :
				if(checkbox.isChecked()){
					Log.d("where","weer");
					bChkbox = true;
				}
				else {
					Log.d("where","gto");
					bChkbox = false;
				break;
				}
			}
		}
	};
	public void onPause(){
		super.onPause();
		
		SharedPreferences pref = getSharedPreferences("PrefTest", 0);
		SharedPreferences.Editor pEdit = pref.edit();
		
		String id = ID.getText().toString();
		int pw=0;
		try{
			pw = Integer.parseInt(PASSWORD.getText().toString());
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		if(bChkbox == true){
			pEdit.putString("id", id);
			pEdit.putInt("pw", pw);
			Log.d("where", "123");
			pEdit.commit();
		}
		else if(bChkbox == false)
		{
			pEdit.putString("id", "");
			pEdit.putInt("pw", 0);
			Log.d("where", "456");
			pEdit.commit();
		}
	}
}