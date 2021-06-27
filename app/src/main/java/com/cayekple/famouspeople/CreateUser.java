package com.cayekple.famouspeople;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class CreateUser extends AppCompatActivity {

    private  static final String TAG = "Create User";

    EditText firstName, lastName, email;
    Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user);

        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        button = findViewById(R.id.button);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production")
                .allowMainThreadQueries()
                .build();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Save to the database
                Log.d(TAG, "onClick: firstname: "+ firstName.getText().toString());
                for (int i = 0; i < 10; i++) {
                    User user = new User(firstName.getText().toString(), lastName.getText().toString(), email.getText().toString());
                    db.mUserDao().insertAll(user);
                }
                startActivity(new Intent(CreateUser.this, MainActivity.class));
            }
        });
    }
}
