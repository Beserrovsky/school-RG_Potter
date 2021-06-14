package com.example.rg_potter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rg_potter.data.Global;
import com.example.rg_potter.entity.Character;
import com.example.rg_potter.entity.User;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SignUpActivity extends AppCompatActivity {

    private Date date = Global.user.Birth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Debug", "SignUp activity called");

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_sign_up);

        loadUser();
    }

    private void loadUser(){

        ((EditText) findViewById(R.id.txtName)).setText(Global.user.Name);
        ((EditText) findViewById(R.id.txtPatronus)).setText(Global.user.Patronus);


        String[] houses = { this.getString(R.string.house_Gryffindor), this.getString(R.string.house_Hufflepuff), this.getString(R.string.house_Ravenclaw), this.getString(R.string.house_Slytherin), this.getString(R.string.house_none)};

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,
                        houses); //selected item will look like a spinner set from XML

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);

        ((Spinner) findViewById(R.id.spinnerHouses)).setAdapter(spinnerArrayAdapter);
        ((Spinner) findViewById(R.id.spinnerHouses)).setSelection(Global.user.House.SpinnerIndex);

        if(Global.user.getGender_Id(this).equalsIgnoreCase("M")){
            ((RadioButton) findViewById(R.id.radioButtonF)).setChecked(false);
            ((RadioButton) findViewById(R.id.radioButtonM)).setChecked(true);
        }else{
            ((RadioButton) findViewById(R.id.radioButtonM)).setChecked(false);
            ((RadioButton) findViewById(R.id.radioButtonF)).setChecked(true);
        }
    }

    public void saveUser(View view){

        Global.user.Name = ((EditText) findViewById(R.id.txtName)).getText().toString();
        Global.user.Birth = this.date;
        Global.user.Patronus = ((EditText) findViewById(R.id.txtPatronus)).getText().toString();
        Global.user.setGender(((RadioButton) findViewById(R.id.radioButtonF)).isChecked()? User.GenderEnum.F : User.GenderEnum.M);

        String value = ((Spinner) findViewById(R.id.spinnerHouses)).getSelectedItem().toString();

        String g = this.getString(R.string.house_Gryffindor);
        String h = this.getString(R.string.house_Hufflepuff);
        String r = this.getString(R.string.house_Ravenclaw);
        String s = this.getString(R.string.house_Slytherin);
        String n = this.getString(R.string.house_none);

        if(value.equals(g)) Global.user.House = new Character.House("gryffindor", this);
        if(value.equals(h)) Global.user.House = new Character.House("hufflepuff", this);
        if(value.equals(r)) Global.user.House = new Character.House("ravenclaw", this);
        if(value.equals(s)) Global.user.House = new Character.House("slytherin", this);
        if(value.equals(n)) Global.user.House = new Character.House("", this);

        Global.user.save(this);

        Global.user = new User(0, this);

        Context context = getApplicationContext();
        CharSequence text = this.getString(R.string.saved);
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

    public void selectDate(View view){
        showDialog(DATE_DIALOG_ID);
    }

    static final int DATE_DIALOG_ID = 0;

    @Override
    protected Dialog onCreateDialog(int id) {
        Calendar c = Calendar.getInstance();

        int y = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH);
        int d = c.get(Calendar.DAY_OF_MONTH);

        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, y, m,
                        d);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int y, int m,
                                      int d) {

                    date = new GregorianCalendar(y, m, d).getTime();

                    Log.d("Debug date", date.toString());
                }
            };

}