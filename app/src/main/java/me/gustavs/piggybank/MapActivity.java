package me.gustavs.piggybank;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class MapActivity extends AppCompatActivity {

    // UI elements
    RadioGroup rgAction;
    EditText etAmount, etReason;
    Settings settings;
    int aSubmit;
    Button goalsbutton;
    Button loansbutton;
    Button treasurebutton;
    Button missionsbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);


        goalsbutton = findViewById(R.id.goalsbutton);
        missionsbutton = findViewById(R.id.missionsbutton);
        loansbutton = findViewById(R.id.loansbutton);
        treasurebutton = findViewById(R.id.treasurebutton);

        // Assign UI elements to variables
        rgAction = findViewById(R.id.rgAction);
        etAmount = findViewById(R.id.etAmount);
        etReason = findViewById(R.id.etReason);

        aSubmit = R.id.submit;

        settings = new Settings(getBaseContext());

        // Hide keyboard when opening activity
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // Set listeners
        //setFloatingActionButtonListeners();

        setGoalsActionButtonListeners();

        setLoansActionButtonListeners();

        setTreasureActionButtonListeners();

        setMissionsActionButtonListeners();
    }

//    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
////        // Inflate the menu; this adds items to the action bar if it is present.
////        getMenuInflater().inflate(R.menu.menu_operations, menu);
////
////        // Set icon color
////        Drawable drawable = menu.findItem(aSubmit).getIcon();
////        drawable.setColorFilter(
////                getResources().getColor(R.color.colorText),
////                PorterDuff.Mode.SRC_ATOP
////        );
////
////        return true;
////    }

    // Handle action button clicks
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menu = item.getItemId(); // Get selected item

        if (menu == aSubmit) {

            String reason = etReason.getText().toString();

            // Check the empty fields
            if (etAmount.getText().toString().trim().length() == 0 || reason.trim().length() == 0) {
                Toast.makeText(this, R.string.fill_all, Toast.LENGTH_SHORT).show();
                return true;
            }

            // Format the amount with 2 decimal places
            // @TODO: Find a better approach
            float amount = Float.parseFloat(
                    String.format(
                            Locale.US,
                            "%.2f",
                            Float.parseFloat(etAmount.getText().toString()))
            );
            // Check whether the amount is not 0F
            if (amount == 0f) {
                Toast.makeText(this, R.string.please_specify_amount, Toast.LENGTH_SHORT).show();
                return true;
            }

            // If money is being withdrawn, make the amount negative
            if (rgAction.getCheckedRadioButtonId() == R.id.rbWithdrawing) {
                amount = -amount;
            }

            // Create new Operation instance
            Operation operation = new Operation();
            operation.setAmount(amount);
            operation.setDescription(reason);
            operation.setCreatedAt(new Date().getTime());

            // Save the new operation to database
            OperationDao dao = ((App)getApplication()).getDaoSession().getOperationDao();
            dao.insert(operation);

            // Calculate the new balance
            float balance = settings.getFloat("balance", 0f);
            settings.setFloat("balance", balance + amount);

            // Exit the activity
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setGoalsActionButtonListeners() {
        goalsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapActivity.this, GoalsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setLoansActionButtonListeners() {
        loansbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapActivity.this, LoansActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setTreasureActionButtonListeners() {
        treasurebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapActivity.this, TreasureActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setMissionsActionButtonListeners() {
        missionsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapActivity.this, MissionsActivity.class);
                startActivity(intent);
            }
        });
    }


}
