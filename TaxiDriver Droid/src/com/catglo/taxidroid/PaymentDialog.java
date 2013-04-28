package com.catglo.taxidroid;

import com.catglo.deliveryDatabase.DataBase;
import com.catglo.deliveryDatabase.DropOff;
import com.catglo.deliveryDatabase.Order;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Button;
import android.widget.TextView;


public class PaymentDialog extends PaymentDialogBase {
	 
	private int primaryKey;
	protected EditText meterAmount;
	
	public PaymentDialog(Context context, int id, DropOff dropOff, Order order, Runnable runOnDialogSave) {
		super(context,dropOff,order, runOnDialogSave,R.layout.payment);
		primaryKey = id;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		
		meterAmount = (EditText)findViewById(R.id.editText1);
		setTitle(R.string.Payment);
				
		((Button) findViewById(R.id.button1)).setOnClickListener(new Button.OnClickListener(){public void onClick(View v) {
			formToDropOff();
			/*dataBase.updateDropOff(primaryKey,
						           paymentType,
						           meterAmount.getEditableText().toString(),
						           paymentAmount.getEditableText().toString(),
						           extraField.getEditableText().toString());*/
			dismiss();
			if (runOnDialogSave != null){
				runOnDialogSave.run();
			}
		}});
		
		
	}
	
	@Override
	void formToDropOff(){
		super.formToDropOff();
		 try {
			 dropOff.meterAmount = new Float(meterAmount.getEditableText().toString());
		 } catch (NumberFormatException e){
			 dropOff.meterAmount = 0f;
		 }  
	}
}
