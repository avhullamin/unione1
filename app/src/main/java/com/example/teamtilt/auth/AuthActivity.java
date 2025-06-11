package com.example.teamtilt.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    private static final String TAG = "AuthActivity";
    private ActivityAuthBinding binding;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private boolean isLoginMode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize Firebase instances
        try {
            auth = FirebaseAuth.getInstance();
            db = FirebaseFirestore.getInstance();
            Log.d(TAG, "Firebase instances initialized successfully");
        } catch (Exception e) {
            Log.e(TAG, "Error initializing Firebase: " + e.getMessage());
            Toast.makeText(this, "Error initializing app. Please try again later.", Toast.LENGTH_LONG).show();
            return;
        }

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
            String email = binding.emailInput.getText().toString().trim();
            String password = binding.passwordInput.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (isLoginMode) {
                loginUser(email, password);
            } else {
                String universityId = binding.universityIdInput.getText().toString().trim();
                String name = binding.nameInput.getText().toString().trim();
                
                if (universityId.isEmpty() || name.isEmpty()) {
                    Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                registerUser(email, password, universityId, name);
            }
        });
    }

    private void loginUser(String email, String password) {
        Log.d(TAG, "Attempting login for email: " + email);
        binding.submitButton.setEnabled(false);
        binding.progressBar.setVisibility(View.VISIBLE);

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener(authResult -> {
                Log.d(TAG, "Login successful for user: " + authResult.getUser().getUid());
                startActivity(new Intent(AuthActivity.this, MainActivity.class));
                finish();
            })
            .addOnFailureListener(e -> {
                Log.e(TAG, "Login failed: " + e.getMessage(), e);
                binding.submitButton.setEnabled(true);
                binding.progressBar.setVisibility(View.GONE);
                
                String errorMessage;
                if (e.getMessage().contains("no user record")) {
                    errorMessage = "No account found with this email. Please register first.";
                } else if (e.getMessage().contains("password is invalid")) {
                    errorMessage = "Incorrect password. Please try again.";
                } else if (e.getMessage().contains("badly formatted")) {
                    errorMessage = "Invalid email format. Please check your email.";
                } else {
                    errorMessage = "Login failed: " + e.getMessage();
                }
                
                Toast.makeText(AuthActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            });
    }

    private void registerUser(String email, String password, String universityId, String name) {
        Log.d(TAG, "Attempting registration for email: " + email);
        binding.submitButton.setEnabled(false);
        binding.progressBar.setVisibility(View.VISIBLE);

        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener(authResult -> {
                Log.d(TAG, "Firebase Auth successful, creating user document");
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
                        Log.d(TAG, "User document created successfully");
                        startActivity(new Intent(AuthActivity.this, MainActivity.class));
                        finish();
                    })
                    .addOnFailureListener(e -> {
                        Log.e(TAG, "Error creating user document: " + e.getMessage(), e);
                        binding.submitButton.setEnabled(true);
                        binding.progressBar.setVisibility(View.GONE);
                        
                        String errorMessage;
                        if (e.getMessage().contains("already in use")) {
                            errorMessage = "This email is already registered. Please login instead.";
                        } else if (e.getMessage().contains("badly formatted")) {
                            errorMessage = "Invalid email format. Please check your email.";
                        } else if (e.getMessage().contains("password")) {
                            errorMessage = "Password should be at least 6 characters long.";
                        } else {
                            errorMessage = "Registration failed: " + e.getMessage();
                        }
                        
                        Toast.makeText(AuthActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                    });
            })
            .addOnFailureListener(e -> {
                Log.e(TAG, "Firebase Auth failed: " + e.getMessage(), e);
                binding.submitButton.setEnabled(true);
                binding.progressBar.setVisibility(View.GONE);
                
                String errorMessage;
                if (e.getMessage().contains("already in use")) {
                    errorMessage = "This email is already registered. Please login instead.";
                } else if (e.getMessage().contains("badly formatted")) {
                    errorMessage = "Invalid email format. Please check your email.";
                } else if (e.getMessage().contains("password")) {
                    errorMessage = "Password should be at least 6 characters long.";
                } else {
                    errorMessage = "Registration failed: " + e.getMessage();
                }
                
                Toast.makeText(AuthActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            });
    }
} 