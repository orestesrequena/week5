package week5.orestes.cdnt4.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import week5.orestes.cdnt4.R;
import week5.orestes.cdnt4.adapters.MyAdapter;
import week5.orestes.cdnt4.common.Constants;

public class SecondActivity extends Activity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, View.OnClickListener {
    MyAdapter myAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);
        TextView myTextView = findViewById(R.id.monTextView);
        ListView listView = findViewById(R.id.second_listView);
        View empty = findViewById(R.id.second_listView_empty);
        Button populate = findViewById(R.id.second_populate);

        Intent intent = getIntent();
        String fromMainActivity = intent.getStringExtra(Constants.FIRST_NAME);
        myTextView.setText(getString(R.string.hello_name, fromMainActivity));

        // we write fromMainActivity on Memory
        //SharedPreferences sp = getSharedPreferences("PREFS", MODE_PRIVATE); old way to do it
        Hawk.put(Constants.FIRST_NAME, fromMainActivity);
        // con el hwak no necesitamos el sharedPreferences, lo que haciamos en 4 lineas lo hacemos en 1
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putString("firstName", fromMainActivity);
//        editor.apply();

        //llama a la clase adapter y carga la lista de cosas

        myAdapter = new MyAdapter(this, getLayoutInflater());
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        //esto es para cargar algo en caso que la listq dentro de myadapter este vacia
        listView.setEmptyView(empty);
        //boton para cargar la lista y retirar la pagina "no hay nada"
        populate.setOnClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Position : " + position, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
        builder.setTitle("Liste Cliquée").setMessage("quelque chose : " + position).setPositiveButton("Ok", null);
        builder.create().show();
        return false;
    }

    @Override
    public void onClick(View v) {
        myAdapter.setCount(100);
        myAdapter.notifyDataSetChanged();
    }
}
