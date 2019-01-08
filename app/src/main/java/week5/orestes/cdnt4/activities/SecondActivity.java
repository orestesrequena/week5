package week5.orestes.cdnt4.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.TextView;

import week5.orestes.cdnt4.R;
import week5.orestes.cdnt4.adapters.MyAdapter;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);
        TextView myTextView = findViewById(R.id.monTextView);
        ListView listView = findViewById(R.id.second_listView);

        Intent  intent = getIntent();
        String fromMainActivity = intent.getStringExtra("firstName");
        myTextView.setText("Bonjour " + fromMainActivity );

        // we write fromMainActivity on Memory
        SharedPreferences sp  = getSharedPreferences("PREFS",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("firstName", fromMainActivity);
        editor.apply();

        //llama a la clase adapter y carga la lista de cosas
        MyAdapter myAdapter = new MyAdapter(this,getLayoutInflater());
        listView.setAdapter(myAdapter);



    }
}
