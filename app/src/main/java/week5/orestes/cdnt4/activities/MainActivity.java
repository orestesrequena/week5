package week5.orestes.cdnt4.activities;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import week5.orestes.cdnt4.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getName();
    private static final int REQUEST_PERMISSIONS = 123;

    private EditText myEditText, mPhoneNumber;
    private Button myButton, mCall,mSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //reconocemos los objetos que hay en el xml
        myEditText = findViewById(R.id.monEditText);
        myButton = findViewById(R.id.monButton);
        mPhoneNumber = findViewById(R.id.main_phone);
        mCall = findViewById(R.id.main_call);
        mSms = findViewById(R.id.main_sms);

        //llamadas a la funcion para que ejecute la funcion que corresponde
        myButton.setOnClickListener(this);
        mCall.setOnClickListener(this);
        mSms.setOnClickListener(this);

        //para guardar la variable de myEditText en la memoria
        SharedPreferences sp = getSharedPreferences("PREFS", MODE_PRIVATE);
        myEditText.setText(sp.getString("firstName", "---"));
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == myButton.getId()) {
            String content = myEditText.getText().toString();
            if (content.trim().length() == 0) {
                Toast.makeText(getApplicationContext(), "Ton texte ne peut pas etre vide", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), content, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("firstName", content);
                startActivity(intent);
            }
        } else if (v.getId() == mCall.getId()) {
            //con esto lo que hacemos es componer el numero para la aplicacion del telefono. no llamamos directamente
            String phoneNumber = mPhoneNumber.getText().toString();
            if (phoneNumber.length() > 0) {
                //esto es para marcar el numero
                // Intent intentPhone = new Intent(Intent.ACTION_DIAL);
                //este es para llamar, pero necesita permisos especiales
                Intent intentPhone = new Intent(Intent.ACTION_CALL);
                intentPhone.setData(Uri.parse("tel:" + phoneNumber));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    String[] permissions = {Manifest.permission.CALL_PHONE};
                    ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSIONS);
                    return;
                }
                startActivity(intentPhone);
            }
        }else if (v.getId() == mSms.getId()) {
            //con esto lo que hacemos es componer el numero para la aplicacion del telefono. no llamamos directamente
            String phoneNumber = mPhoneNumber.getText().toString();
            if (phoneNumber.length() > 0) {

                //este es para enviar mensaje, pero necesita permisos especiales
                Intent intentSms = new Intent(Intent.ACTION_SENDTO);
                String message = "patata 22";
                intentSms.putExtra("sms_body", message);
                intentSms.setData(Uri.parse("smsto:" + phoneNumber));

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
//                    String[] permissions = {Manifest.permission.SEND_SMS};
//                    ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSIONS);
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNumber, null, message, null, null);
                    return;
                }
                startActivity(intentSms);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            mCall.callOnClick();
        } else if (requestCode == REQUEST_PERMISSIONS && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            mSms.setEnabled(true);


        }
    }
}
