package me.gustavs.piggybank;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import me.gustavs.piggybank.entities.Operation;

public class HistoryAdapter extends ArrayAdapter<Operation> {


    public HistoryAdapter(Context context, int resource, List<Operation> items) {
        super(context, resource, items);
    }

    @NonNull
    @Override
    public View getView(final int position, View view, @NonNull ViewGroup parent) {

        final Operation operation = getItem(position);

        if (view == null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            view = inflater.inflate(R.layout.activity_history_list, parent, false);
        }

        TextView description = view.findViewById(R.id.description);
        TextView date = view.findViewById(R.id.date);
        TextView amount = view.findViewById(R.id.amount);

        description.setText(operation.getDescription());

        SimpleDateFormat dt = new SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.US);
        date.setText(dt.format(new Date(operation.getCreatedAt())));

        String setAmount = String.format(
                Locale.US,
                "%.2f",
                operation.getAmount()
        );

        if (operation.getAmount() > 0f) {
            amount.setText("+" + setAmount + "€");
            amount.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPositive));
        } else {
            amount.setText(setAmount + "€");
            amount.setTextColor(ContextCompat.getColor(getContext(), R.color.colorNegative));
        }

        return view;
    }

}