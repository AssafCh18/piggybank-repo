package me.gustavs.piggybank;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.util.DisplayMetrics;

import android.os.Handler;

import java.util.Date;
import java.util.Locale;

import me.gustavs.piggybank.database.App;
import me.gustavs.piggybank.entities.Operation;
import me.gustavs.piggybank.entities.OperationDao;

public class LoansActivity extends AppCompatActivity {

    // UI elements
    RadioGroup rgAction;
    EditText amountfescrip, loanreasondescription;
    Settings settings;
    int aSubmit;

    Button sendloan;
    Button popupButton;

    TextView loandetails;
    TextView loanamount;
    TextView loanreasondesc;
    TextView whatiamwillingtodo;
    EditText editText1;
    EditText editText2;
    ImageView emptychest;
    ImageView coinsloans;
    ConstraintLayout waitinglayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loans);

        // Assign UI elements to variables
        rgAction = findViewById(R.id.rgAction);
        amountfescrip = findViewById(R.id.amountfescrip);
        loanreasondescription = findViewById(R.id.loanreasondescription);

        loandetails = findViewById(R.id.loandetails);
        loanamount = findViewById(R.id.loanamount);
        loanreasondesc = findViewById(R.id.loanreasondesc);
        whatiamwillingtodo = findViewById(R.id.whatiamwillingtodo);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        emptychest = findViewById(R.id.emptychest);
        coinsloans = findViewById(R.id.coinsloans);
        waitinglayout = findViewById(R.id.waitinglayout);

        sendloan = findViewById(R.id.sendloan);

        aSubmit = R.id.submit;

        settings = new Settings(getBaseContext());

        // Hide keyboard when opening activity
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        setLoansActionButtonListeners();


    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_operations, menu);
//
//        // Set icon color
//        Drawable drawable = menu.findItem(aSubmit).getIcon();
//        drawable.setColorFilter(
//                getResources().getColor(R.color.colorText),
//                PorterDuff.Mode.SRC_ATOP
//        );
//
//        return true;
//    }

    // Handle action button clicks
    private void setLoansActionButtonListeners() {
        sendloan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String reason = loanreasondescription.getText().toString();

                // Check the empty fields
                if (amountfescrip.getText().toString().trim().length() == 0 || reason.trim().length() == 0) {
//                    Toast.makeText(this, R.string.fill_all, Toast.LENGTH_SHORT).show();
                }

                // Format the amount with 2 decimal places
                // @TODO: Find a better approach
                float amount = Float.parseFloat(
                        String.format(
                                Locale.US,
                                "%.2f",
                                Float.parseFloat(amountfescrip.getText().toString()))
                );

                // Check whether the amount is not 0F
                if (amount == 0f) {
//                    Toast.makeText(this, R.string.please_specify_amount, Toast.LENGTH_SHORT).show();
                }

                // Create new Operation instance
                Operation operation = new Operation();
                operation.setAmount(amount);
                operation.setDescription(reason);
                operation.setCreatedAt(new Date().getTime());

                // Save the new operation to database
                OperationDao dao = ((App) getApplication()).getDaoSession().getOperationDao();
                dao.insert(operation);

                loandetails.setVisibility(View.INVISIBLE);
                loanamount.setVisibility(View.INVISIBLE);
                amountfescrip.setVisibility(View.INVISIBLE);
                loanreasondesc.setVisibility(View.INVISIBLE);
                loanreasondescription.setVisibility(View.INVISIBLE);
                whatiamwillingtodo.setVisibility(View.INVISIBLE);
                editText1.setVisibility(View.INVISIBLE);
                editText2.setVisibility(View.INVISIBLE);
                emptychest.setVisibility(View.INVISIBLE);
                coinsloans.setVisibility(View.INVISIBLE);
                sendloan.setVisibility(View.INVISIBLE);

                waitinglayout.setVisibility(View.VISIBLE);


                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 100ms
                        Intent i = new Intent(getApplicationContext(), PopUpActivity.class);
                        startActivity(i);
                    }
                }, 20000);


                // Calculate the new balance
                float balance = settings.getFloat("balance", 0f);
                settings.setFloat("balance", balance + amount);
            }


        });
    }
}