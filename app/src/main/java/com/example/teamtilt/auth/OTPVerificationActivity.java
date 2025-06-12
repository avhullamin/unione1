package com.example.teamtilt.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teamtilt.MainActivity;
import com.example.teamtilt.R;
import com.example.teamtilt.databinding.ActivityOtpVerificationBinding;
import com.example.teamtilt.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class OTPVerificationActivity extends AppCompatActivity {
    private static final String TAG = "OTPVerificationActivity";
    private ActivityOtpVerificationBinding binding;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private String email;
    private String password;
    private String universityId;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpVerificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize Firebase instances
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Get registration data from intent
        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");
        universityId = getIntent().getStringExtra("universityId");
        name = getIntent().getStringExtra("name");

        setupUI();
        sendVerificationEmail();
    }

    private void setupUI() {
        binding.resendButton.setOnClickListener(v -> sendVerificationEmail());
        binding.verifyButton.setOnClickListener(v -> verifyEmail());
    }

    private void sendVerificationEmail() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.verifyButton.setEnabled(false);
        binding.resendButton.setEnabled(false);

        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener(authResult -> {
                FirebaseUser user = authResult.getUser();
                if (user != null) {
                    user.sendEmailVerification()
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(this, "Verification email sent. Please check your inbox.", Toast.LENGTH_LONG).show();
                            binding.progressBar.setVisibility(View.GONE);
                            binding.verifyButton.setEnabled(true);
                            binding.resendButton.setEnabled(true);
                        })
                        .addOnFailureListener(e -> {
                            Log.e(TAG, "Error sending verification email: " + e.getMessage());
                            Toast.makeText(this, "Failed to send verification email: " + e.getMessage(), Toast.LENGTH_LONG).show();
                            binding.progressBar.setVisibility(View.GONE);
                            binding.verifyButton.setEnabled(true);
                            binding.resendButton.setEnabled(true);
                        });
                }
            })
            .addOnFailureListener(e -> {
                Log.e(TAG, "Error creating user: " + e.getMessage());
                Toast.makeText(this, "Registration failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
                binding.progressBar.setVisibility(View.GONE);
                binding.verifyButton.setEnabled(true);
                binding.resendButton.setEnabled(true);
            });
    }

    private void verifyEmail() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.verifyButton.setEnabled(false);
        binding.resendButton.setEnabled(false);

        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            user.reload().addOnSuccessListener(aVoid -> {
                if (user.isEmailVerified()) {
                    // Create user document in Firestore
                    User newUser = new User(
                        user.getUid(),
                        email,
                        universityId,
                        name,
                        "STUDENT" // Default role
                    );

                    db.collection("users")
                        .document(user.getUid())
                        .set(newUser)
                        .addOnSuccessListener(aVoid1 -> {
                            Toast.makeText(this, "Email verified successfully!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(OTPVerificationActivity.this, MainActivity.class));
                            finish();
                        })
                        .addOnFailureListener(e -> {
                            Log.e(TAG, "Error creating user document: " + e.getMessage());
                            Toast.makeText(this, "Error creating user profile: " + e.getMessage(), Toast.LENGTH_LONG).show();
                            binding.progressBar.setVisibility(View.GONE);
                            binding.verifyButton.setEnabled(true);
                            binding.resendButton.setEnabled(true);
                        });
                } else {
                    Toast.makeText(this, "Please verify your email first.", Toast.LENGTH_LONG).show();
                    binding.progressBar.setVisibility(View.GONE);
                    binding.verifyButton.setEnabled(true);
                    binding.resendButton.setEnabled(true);
                }
            })
            .addOnFailureListener(e -> {
                Log.e(TAG, "Error reloading user: " + e.getMessage());
                Toast.makeText(this, "Error verifying email: " + e.getMessage(), Toast.LENGTH_LONG).show();
                binding.progressBar.setVisibility(View.GONE);
                binding.verifyButton.setEnabled(true);
                binding.resendButton.setEnabled(true);
            });
        }
    }
} 