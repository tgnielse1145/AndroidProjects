package com.example.cupcake;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.cupcake.databinding.FragmentPickupBinding;
import com.example.cupcake.model.OrderViewModel;

public class PickupFragment extends Fragment {
    private FragmentPickupBinding binding;
    private OrderViewModel sharedViewModel;

    public PickupFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentPickupBinding.inflate(getLayoutInflater());
       sharedViewModel = new ViewModelProvider(getActivity()).get(OrderViewModel.class);
       return binding.getRoot();

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.setSharedViewModel(sharedViewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setPickupFragment(this);
        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_pickupFragment_to_summaryFragment);
            }
        });

    }
    public void goToNextScreen(){
        Navigation.findNavController(getView()).navigate(R.id.action_pickupFragment_to_summaryFragment);
    }
    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }
}