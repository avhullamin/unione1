package com.example.teamtilt.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teamtilt.MainActivity;
import com.example.teamtilt.R;
import com.example.teamtilt.databinding.ActivityAuthBinding;
import com.example.teamtilt.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class AuthActivity extends AppCompatActivity {
    private ActivityAuthBinding binding;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private boolean isLoginMode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        setupUI();
        setupListeners();
    }

    private void setupUI() {
        binding.switchModeButton.setOnClickListener(v -> {
            isLoginMode = !isLoginMode;
            updateUI();
        });
    }

    private void updateUI() {
        if (isLoginMode) {
            binding.titleText.setText(R.string.login);
            binding.switchModeButton.setText(R.string.switch_to_register);
            binding.universityIdLayout.setVisibility(View.GONE);
            binding.nameLayout.setVisibility(View.GONE);
        } else {
            binding.titleText.setText(R.string.register);
            binding.switchModeButton.setText(R.string.switch_to_login);
            binding.universityIdLayout.setVisibility(View.VISIBLE);
            binding.nameLayout.setVisibility(View.VISIBLE);
        }
    }

    private void setupListeners() {
        binding.submitButton.setOnClickListener(v -> {
            String email = binding.emailInput.getText().toString();
            String password = binding.passwordInput.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (isLoginMode) {
                loginUser(email, password);
            } else {
                String universityId = binding.universityIdInput.getText().toString();
                String name = binding.nameInput.getText().toString();
                
                if (universityId.isEmpty() || name.isEmpty()) {
                    Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                registerUser(email, password, universityId, name);
            }
        });
    }

    private void loginUser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener(authResult -> {
                startActivity(new Intent(AuthActivity.this, MainActivity.class));
                finish();
            })
            .addOnFailureListener(e -> 
                Toast.makeText(AuthActivity.this, "Login failed: " + e.getMessage(), 
                    Toast.LENGTH_SHORT).show());
    }

    private void registerUser(String email, String password, String universityId, String name) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener(authResult -> {
                User user = new User(
                    authResult.getUser().getUid(),
                    email,
                    universityId,
                    name,
                    "STUDENT" // Default role
                );

                db.collection("users")
                    .document(user.getUid())
                    .set(user)
                    .addOnSuccessListener(aVoid -> {
                        startActivity(new Intent(AuthActivity.this, MainActivity.class));
                        finish();
                    })
                    .addOnFailureListener(e -> 
                        Toast.makeText(AuthActivity.this, "Registration failed: " + e.getMessage(), 
                            Toast.LENGTH_SHORT).show());
            })
            .addOnFailureListener(e -> 
                Toast.makeText(AuthActivity.this, "Registration failed: " + e.getMessage(), 
                    Toast.LENGTH_SHORT).show());
    }
} 