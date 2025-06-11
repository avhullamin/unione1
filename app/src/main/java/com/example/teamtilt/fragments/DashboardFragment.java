package com.example.teamtilt.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.teamtilt.R;
import com.example.teamtilt.databinding.FragmentDashboardBinding;
import com.example.teamtilt.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class DashboardFragment extends Fragment {
    private FragmentDashboardBinding binding;
    private FirebaseFirestore db;
    private FirebaseAuth auth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupUI();
        loadUserData();
    }

    private void setupUI() {
        // Setup RecyclerViews
        binding.upcomingEventsRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.quickLinksRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        
        // TODO: Set up adapters for events and quick links
    }

    private void loadUserData() {
        String userId = auth.getCurrentUser().getUid();
        db.collection("users").document(userId)
            .get()
            .addOnSuccessListener(documentSnapshot -> {
                User user = documentSnapshot.toObject(User.class);
                if (user != null) {
                    binding.welcomeText.setText(getString(R.string.welcome, user.getName()));
                }
            });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
} 