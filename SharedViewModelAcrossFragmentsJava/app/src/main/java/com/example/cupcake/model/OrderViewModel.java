package com.example.cupcake.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class OrderViewModel extends ViewModel {

    private static Double PRICE_PER_CUPCAKE = 2.00;
    private static Double PRICE_FOR_SAME_DAY_PICKUP = 3.00;

    private final MutableLiveData<Integer>quantity;
    private final MutableLiveData<String>flavor;
    private final MutableLiveData<String>date;
    public List<String>dateOptions;
    private final MutableLiveData<Double>_price;
    public LiveData<String>price;

    public OrderViewModel() {
        quantity = new MutableLiveData<Integer>(0);
        flavor = new MutableLiveData<String>("");
        date = new MutableLiveData<String>("");
        dateOptions = getPickupOptions();
        _price = new MutableLiveData<Double>(0.0);
        price = Transformations.map(_price, priced -> {
            return NumberFormat.getCurrencyInstance().format(_price.getValue());


        });
    }
    public MutableLiveData<Integer> getQuantity() {
        return quantity;
    }
    public String getQuantity2(){
        Integer num = quantity.getValue();
        return String.valueOf(num);
    }

    public void setQuantity(Integer _quantity) {
        Log.d("WORK4",String.valueOf(_quantity));
        quantity.setValue(_quantity);
        updatePrice();

    }

    public MutableLiveData<String> getFlavor() {
        return flavor;
    }

    public void setFlavor(String _flavor) {
        flavor.setValue(_flavor);
    }

    public MutableLiveData<String> getDate() {
        return date;
    }

    public void setDate(String _date) {
        date.setValue(_date); updatePrice();
    }

    public MutableLiveData<Double> getPrice() {
        return _price;
    }

    public void setPrice(Double _prices) {
        _price.setValue(_prices);
    }

    public Boolean hasNoFlavorSet(){
        if(flavor.getValue().isEmpty()){
            return true;
        }
        return false;
    }
    public void updatePrice(){
        Double calculatedPrice=0.0;
        if(quantity.getValue()!=null) {
            calculatedPrice = quantity.getValue() * PRICE_PER_CUPCAKE;
        }
        Log.d("DATE_OPTIONS",dateOptions.get(0));
        Log.d("DATE",date.getValue());
        if(dateOptions.get(0).equals(date.getValue())){
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP;
        }
        _price.setValue(calculatedPrice);
        setPrice(calculatedPrice);
    }
    private List<String> getPickupOptions(){
        List<String> options = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("E MMM d", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        for(int i=0; i<4; i++){
            options.add(formatter.format(calendar.getTime()));
            calendar.add(Calendar.DATE,1);

        }
        return options;
    }
    public void resetOrder(){
        quantity.setValue(0);
        flavor.setValue("");
        date.setValue("");
        _price.setValue(0.0);
    }
}
