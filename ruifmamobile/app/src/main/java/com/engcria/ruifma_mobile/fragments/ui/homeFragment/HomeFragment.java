package com.engcria.ruifma_mobile.fragments.ui.homeFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import com.engcria.ruifma_mobile.R;

public class HomeFragment extends Fragment {
    Toolbar toolbar;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Dashboard do Usuário");


        return root;
    }
}