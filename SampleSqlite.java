package aebhi.addictech.samplesqlite;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import aebhi.addictech.samplesqlite.Adapters.CrudBase;
import aebhi.addictech.samplesqlite.Models.StudData;

public class SampleSqlite extends AppCompatActivity {
    EditText ed1;
    Button b1;
    TextView t1;
    Spinner s1;
    CrudBase cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen1);

        cb = new CrudBase(this);

        ed1 = (EditText) findViewById(R.id.et1);
        b1 = (Button) findViewById(R.id.AddS);
        t1= (TextView) findViewById(R.id.ViewS);
        s1= (Spinner) findViewById(R.id.spinnerD);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ed1.getText().toString();
                String dept = s1.getSelectedItem().toString();
                cb.addStud(new StudData(name,dept));


                /*SharedPreferences pref = getApplicationContext().getSharedPreferences("SamplePref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();

                editor.putString("name", "string value"); // Storing string
                editor.putInt("id",1 ); // Storing integer
                editor.clear();
                editor.commit();*/



                ArrayList<StudData> d = new ArrayList<StudData>();
                d= cb.getAllData();
                for (StudData d1:d){
                    Log.d("name",d1.getName()+"");
                    Log.d("dept",d1.getDept()+"");
                    Log.d("id",d1.getId());
                }
            }
        });







    }
}
