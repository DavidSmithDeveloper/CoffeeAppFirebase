package com.trios.coffeeappfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    EditText loginUsername, loginPassword;
    Button loginButton;
    TextView signupRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsername = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        signupRedirectText = findViewById(R.id.signupRedirectText);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (!validateUsername() | !validatePassword())
                {
                }else

            {
                checkUser();
            }


            }
        });

        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);

            }
        });
    }
    public Boolean validateUsername(){
        String val = loginUsername.getText().toString();
        if( val.isEmpty()){
            loginUsername.setError("Username cannot be empty");
            return false;
        }else{
            loginUsername.setError(null);
            return true;
        }

    }
    public Boolean validatePassword(){
        String val = loginPassword.getText().toString();
        if( val.isEmpty()){
            loginPassword.setError("Password cannot be empty");
            return false;
        }else{
            loginPassword.setError(null);
            return true;
        }

    }
    public void checkUser() {
        String userUserName = loginUsername.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUserName);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    DataSnapshot userSnapshot = snapshot.getChildren().iterator().next(); // Get the first matching user
                    String passwordFromDB = userSnapshot.child("password").getValue(String.class);

                    if (userPassword.equals(passwordFromDB)) {
                        // Successful login
                        loginUsername.setError(null);
                        loginPassword.setError(null);

                        String personName = userSnapshot.child("name").getValue(String.class);

                        String welcomeMessage = "Welcome, " + personName + " Login Successful";

                        Toast.makeText(LoginActivity.this, welcomeMessage, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                        startActivity(intent);


                    } else {
                        // Invalid password
                        loginPassword.setError("Invalid Password");
                        loginPassword.requestFocus();
                    }
                } else {
                    // User does not exist
                    loginUsername.setError("User does not exist");
                    loginUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database query cancellation or errors
                // You can display an error message to the user here
            }
        });
    }
}