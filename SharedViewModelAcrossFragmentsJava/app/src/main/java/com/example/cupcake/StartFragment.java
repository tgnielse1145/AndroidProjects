package com.example.cupcake;

import android.os.Bundle;

import androidx.activity.ActivityViewModelLazyKt;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.cupcake.databinding.FragmentStartBinding;
import com.example.cupcake.model.OrderViewModel;


public class StartFragment extends Fragment {
    private FragmentStartBinding binding;
    private OrderViewModel sharedViewModel;
    private View startFragmentView;
    public StartFragment() {
        // Required empty public constructor
       // sharedViewModel = new ViewModelProvider(this).get(OrderViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // binding=FragmentStartBinding.inflate(getLayoutInflater());
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_start,container,false);
        sharedViewModel = new ViewModelProvider(getActivity()).get(OrderViewModel.class);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        Log.d("WORK","WHY WONT IT WORK?");
        super.onViewCreated(view,savedInstanceState);

        binding.setStartFragment(this);
        binding.setLifecycleOwner(getViewLifecycleOwner());


        // sharedViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        binding.orderOneCupcake.setOnClickListener(v -> {orderCupcake(1); });
        binding.orderSixCupcakes.setOnClickListener(v->{orderCupcake(6);});
        binding.orderTwelveCupcakes.setOnClickListener(v->{orderCupcake(12);});
    }


    public void orderCupcake(Integer quantity){
        Log.d("WORK3",String.valueOf(quantity));
        sharedViewModel.setQuantity(quantity);
        if(sharedViewModel.hasNoFlavorSet()){
            sharedViewModel.setFlavor("Vanilla");
            Log.d("SHARED_VIEW_MODEL_TRUE","TOILET");
        }
        Log.d("SHARED_VIEW_MODEL","SHIT");
        Navigation.findNavController(getView()).navigate(R.id.action_startFragment_to_flavorFragment);

    }
    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }
}