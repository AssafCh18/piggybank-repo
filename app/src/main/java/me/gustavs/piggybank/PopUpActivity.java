package me.gustavs.piggybank;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Date;
import java.util.Locale;

import me.gustavs.piggybank.database.App;
import me.gustavs.piggybank.entities.Operation;
import me.gustavs.piggybank.entities.OperationDao;

public class PopUpActivity extends AppCompatActivity {

    // UI elements
    RadioGroup rgAction;
    EditText etAmount, etReason;
    Settings settings;
    int aSubmit;

    Button sendloan;
    Button popupButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);

        setContentView(R.layout.popup);

                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                int Width=dm.widthPixels;
                int height = dm.heightPixels;

                getWindow().setLayout((int)(Width*.8),(int)(height*.5));

        // Assign UI elements to variables
        rgAction = findViewById(R.id.rgAction);
        etAmount = findViewById(R.id.etAmount);
        etReason = findViewById(R.id.etReason);

        sendloan = findViewById(R.id.sendloan);
        popupButton = (Button) findViewById(R.id.popupButton);

        popupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }});


        aSubmit = R.id.submit;

        settings = new Settings(getBaseContext());

        // Hide keyboard when opening activity
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        popupActionButtonListeners();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_operations, menu);

        // Set icon color
        Drawable drawable = menu.findItem(aSubmit).getIcon();
        drawable.setColorFilter(
                getResources().getColor(R.color.colorText),
                PorterDuff.Mode.SRC_ATOP
        );

        return true;
    }

    // Handle action button clicks
    private void popupActionButtonListeners() {
        popupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);}

//
//                                            Button popupButton = (Button) findViewById(R.id.popupButton);
//
//                                            popupButton.setOnClickListener(new View.OnClickListener())
//                                            @Override
//                                            public void onClick (View v){
//                                                startActivity(new Intent(LoansActivity.this, PopUpActivity.class));
        });



    }
}
