package com.example.instantservice.category;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.instantservice.R;
import com.example.instantservice.notification.MySingleton;
import com.example.instantservice.notification.notification;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class other extends AppCompatActivity {
    EditText edtTitle;
    EditText edtMessage;

    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String serverKey = "key=" + "AAAA-DGkzIY:APA91bHSYdNqfNTj9m91SXc3Yz77sTgk-7rKlvHI-mKH1GNAjEHgnZ6BUsm5YaYFyWWfU35B8_w9Lzqkk_5UeW-tFpLBRm9HSIV0_aKqDwhxGqIGjYM6Xyu-N0lSf6bKalNac70cmO6o";
    final private String contentType = "application/json";
    final String TAG = "NOTIFICATION TAG";
    String NOTIFICATION_TITLE;
    String NOTIFICATION_MESSAGE;
    String TOPIC;

    private Button btn;
    private Button btn2;
    TextInputLayout e1,e2,e3;

    private CheckBox c1,c2;
    private void textclear() {
        e1.getEditText().setText(null);
        e2.getEditText().setText(null);
        e3.getEditText().setText(null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        btn2=(Button)findViewById(R.id.btnSend);

        e1=findViewById(R.id.editText);
        e2=findViewById(R.id.editText2);
        e3=findViewById(R.id.editText3);
        c1=(CheckBox)findViewById(R.id.Rent);
        c2=(CheckBox)findViewById(R.id.Permanent);





        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = e1.getEditText().getText().toString();
                String username = e2.getEditText().getText().toString();
                String phoneNo = e3.getEditText().getText().toString();

                Intent intent2 = new Intent(other.this, notification.class);
                intent2.putExtra("name", name);
                intent2.putExtra("cost", username);
                intent2.putExtra("phoneNo", phoneNo);
                startActivity(intent2);


                if(c1.isChecked()) {

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef1 = database.getReference("Rent");
                    ElectricData dclass = new ElectricData(name, username, phoneNo);
                    myRef1.child(phoneNo).setValue(dclass);

                    Toast.makeText(other.this, "C1 posted successfuly", Toast.LENGTH_SHORT).show();
                    textclear();
                }
                if(c2.isChecked()) {

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef1 = database.getReference("Permanent");
                    ElectricData dclass = new ElectricData(name, username, phoneNo);
                    myRef1.child(phoneNo).setValue(dclass);
                    Toast.makeText(other.this, "C2 posted successfuly", Toast.LENGTH_SHORT).show();
                    textclear();
                }
                else{

                }
                TOPIC = "/topics/userABC"; //topic has to match what the receiver subscribed to
                //NOTIFICATION_TITLE = edtTitle.getText().toString();
                //NOTIFICATION_MESSAGE = edtMessage.getText().toString();

                JSONObject notification = new JSONObject();
                JSONObject notifcationBody = new JSONObject();
                try {
                    notifcationBody.put("title", "Instant Service");
                    notifcationBody.put("message", "New Item Available");

                    notification.put("to", TOPIC);
                    notification.put("data", notifcationBody);
                } catch (JSONException e) {
                    Log.e(TAG, "onCreate: " + e.getMessage() );
                }

                sendNotification(notification);

            }
        });



    }
    private void sendNotification(JSONObject notification) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(FCM_API, notification,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, "onResponse: " + response.toString());
                        edtTitle.setText("");
                        edtMessage.setText("");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(other.this, "Request error", Toast.LENGTH_LONG).show();
                        Log.i(TAG, "onErrorResponse: Didn't work");
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", serverKey);
                params.put("Content-Type", contentType);
                return params;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }
}
