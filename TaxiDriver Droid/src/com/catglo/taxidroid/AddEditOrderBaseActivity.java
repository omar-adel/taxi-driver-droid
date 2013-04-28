package com.catglo.taxidroid;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import com.catglo.deliveryDatabase.Order;

import com.catglo.widgets.AddressAutocomplete;

public class AddEditOrderBaseActivity extends TaxiDroidBaseActivity {

	private Order order;
	protected LinearLayout dropOffContainer; //!! MUST BE ASSIGNED BY CHILD VIEW	
	public class DropOffRow {
		AddressAutocomplete address;
		TextView dropoffId;
		EditText timestamp;
		
		Spinner paymentType;
		EditText account;
		EditText payment;
		TextView extraLabel;
		EditText meterAmount;
		LinearLayout all;
		public ImageButton addButton;
		
		TableLayout paymentPart;
		public CheckBox streetHire;

	}
	ArrayList<DropOffRow> dropOffRows = new ArrayList<DropOffRow>();
	
	
	
	
	public static final int NO_PAYMENT = 0;        //HArdcoded elsewhere these values can not change
	public static final int CASH_PAYMENT = 1;
	public static final int CREDIT_PAYMENT = 2;
	public static final int ACCOUNT_PAYMENT = 3;
	
    /** Called when the activity is first created. */
	int repeatPreventCounter1=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);      
    }
    
    
    
    
    protected DropOffRow addDropoffRow(final int layoutId){
    	final String[] paymentTypes = {getString(R.string.No),        //0
        		getString(R.string.Cash),      //1
                getString(R.string.Credit),    //2
                getString(R.string.account)};  //3
    	
    	DropOffRow d = new DropOffRow();
    	final LinearLayout row = (LinearLayout) getLayoutInflater().inflate(layoutId, null);
		dropOffContainer.addView(row);
    	d.address = (AddressAutocomplete) row.findViewById(R.id.addressAutocomplete1);
    	d.address.startSuggestor(dataBase);
    	d.dropoffId = (TextView) row.findViewById(R.id.idField);
    	d.addButton  = (ImageButton) row.findViewById(R.id.imageButton1);
    	if (d.addButton  != null) {
    		d.addButton.setOnClickListener(new OnClickListener(){public void onClick(View v) {
    			addDropoffRow(layoutId);
    		}});
    	}
    	if (d.address!=null){
    		d.address.setId(5000+dropOffRows.size());
    	}
    	if (d.dropoffId!=null){
    		d.dropoffId.setId(6000+dropOffRows.size());
    	}
    	d.timestamp = (EditText) row.findViewById(R.id.timeStampEditField);
    	if (d.timestamp!=null){
    		d.timestamp.setId(7000+dropOffRows.size());
    	}
    	d.paymentType = (Spinner) row.findViewById(R.id.spinner1);
    	if (d.paymentType != null){
    		d.paymentType.setId(8000+dropOffRows.size());
    		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,paymentTypes);
        	d.paymentType.setAdapter(adapter);
        	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	}
    	d.account = (EditText) row.findViewById(R.id.editText2);
    	if (d.account != null){
    		d.account.setId(9000+dropOffRows.size());
    	}
    	d.payment = (EditText) row.findViewById(R.id.editText1);
    	if (d.payment != null){
    		d.payment.setId(3000+dropOffRows.size());
    	}
    	d.extraLabel = (TextView) row.findViewById(R.id.variableLabel);
    	if (d.extraLabel != null){
    		d.extraLabel.setId(2000+dropOffRows.size());
    	}
    	d.meterAmount = (EditText) row.findViewById(R.id.editText3);
    	if (d.meterAmount != null){
    		d.meterAmount.setId(10000+dropOffRows.size());
    	}
    	d.streetHire = (CheckBox) row.findViewById(R.id.checkBox1);
    	if (d.streetHire != null){
    		d.streetHire.setId(11000+dropOffRows.size());
    	}
    	d.all = row;
    	d.paymentPart = (TableLayout) row.findViewById(R.id.TableLayout1);
    	dropOffRows.add(d);
    	
    	return d;
	}
	
}