package me.gustavs.piggybank;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Button;

import java.util.Locale;

//test
//test2


public class MainActivity extends AppCompatActivity {

    // UI elements
    Toolbar toolbar;
    FloatingActionButton fab;
    Button goalsbutton;
    Button loansbutton;
    Button treasurebutton;
    Button mapbutton;
    TextView tvAmount;

    Settings settings;

    int aHistory; // History action button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign UI elements to variables
        toolbar = findViewById(R.id.toolbar);
        //fab = findViewById(R.id.fab);
        //goalsbutton = findViewById(R.id.goalsbutton);
        mapbutton = findViewById(R.id.mapbutton);
        //loansbutton = findViewById(R.id.loansbutton);
        //treasurebutton = findViewById(R.id.treasurebutton);
        tvAmount = findViewById(R.id.tvamount);

        settings = new Settings(getBaseContext());

        //aHistory = R.id.history;

        // Initialize action bar
        setSupportActionBar(toolbar);

        // Set listeners
        //setFloatingActionButtonListeners();

        //setGoalsActionButtonListeners();

        //setLoansActionButtonListeners();

        //setTreasureActionButtonListeners();

        setMapActionButtonListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();

        tvAmount.setText(
                String.format(
                        Locale.US,
                        "%.2f",
                        settings.getFloat("balance", 0f)
                ) + "$"
        );
    }

    //@Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//
//        // Set icon color
//        Drawable drawable = menu.findItem(aHistory).getIcon();
//        drawable.setColorFilter(
//                getResources().getColor(R.color.colorText),
//                PorterDuff.Mode.SRC_ATOP
//        );
//
//        return true;
//    }

    // Handle action button clicks
    //@Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int menu = item.getItemId(); // Get selected item
//
//        if (menu == aHistory) {
//            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
//            startActivity(intent);
//
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    private void setFloatingActionButtonListeners() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OperationsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setGoalsActionButtonListeners() {
        goalsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GoalsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setLoansActionButtonListeners() {
        loansbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoansActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setTreasureActionButtonListeners() {
        treasurebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TreasureActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setMapActionButtonListeners() {
        mapbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
    }

}
