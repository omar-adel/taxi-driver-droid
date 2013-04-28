package com.catglo.widgets;



import com.catglo.deliveryDatabase.*;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import com.catglo.deliveryDatabase.*;

import com.catglo.deliveryDatabase.AddressSuggestiorGoogle.AddressSuggestionCommitor;



public class AddressAutocomplete extends AutoCompleteTextView {

	private TextWatcher textWatcher;
	private AddressSuggestior suggestor;
	Context context;
	
	public AddressAutocomplete(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}
	
	public void startSuggestor(DataBase dataBase){
		textWatcher = new TextWatcher(){
			public void afterTextChanged(Editable s) {}
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (count==1){
					suggestor.lookup(""+s);
				}
			}
		};
		
		suggestor = new AddressSuggestior(context,new Runnable(){public void run() {post(new Runnable(){public void run(){
				ArrayAdapter<AddressInfo> streets = new ArrayAdapter<AddressInfo>(context, android.R.layout.simple_dropdown_item_1line, suggestor.addressList);
				AddressAutocomplete.this.setAdapter(streets);
				AddressAutocomplete.this.showDropDown();
			
		}});}},dataBase);
    	addTextChangedListener(textWatcher);
	}
	
}
