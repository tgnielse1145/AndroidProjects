package com.example.cupcake;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cupcake.databinding.FragmentSummaryBinding;
import com.example.cupcake.model.OrderViewModel;

public class SummaryFragment extends Fragment {

    private FragmentSummaryBinding binding;
    private OrderViewModel sharedViewModel;
    public SummaryFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentSummaryBinding.inflate(getLayoutInflater());
       sharedViewModel = new ViewModelProvider(getActivity()).get(OrderViewModel.class);
       return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.setSharedViewModel(sharedViewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setSummaryFragment(this);
        Log.d("WORK5",sharedViewModel.getQuantity2());
        binding.quantity.setText(sharedViewModel.getQuantity2());
        binding.sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Send Order",Toast.LENGTH_LONG).show();
            }
        });

    }
    public void sendOrder(){
        Toast.makeText(this.getActivity(), "Send Order", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }
}