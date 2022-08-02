package com.example.cupcake;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.cupcake.databinding.FragmentFlavorBinding;
import com.example.cupcake.model.OrderViewModel;

public class FlavorFragment extends Fragment {
    private FragmentFlavorBinding binding;
    private OrderViewModel sharedViewModel;
    public FlavorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentFlavorBinding.inflate(getLayoutInflater());
        sharedViewModel = new ViewModelProvider(getActivity()).get(OrderViewModel.class);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.setSharedViewModel(sharedViewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setFlavorFragment(this);
        if(sharedViewModel.hasNoFlavorSet()){
            sharedViewModel.setFlavor("Vanilla");
            binding.vanilla.setChecked(true);
            Log.d("SHARED_VIEW_MODEL_TRUE","TOILET");
        }
        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_flavorFragment_to_pickupFragment);
            }
        });

    }
    public void goToNextScreen(){
        Navigation.findNavController(getView()).navigate(R.id.action_flavorFragment_to_pickupFragment);
    }
    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }
}