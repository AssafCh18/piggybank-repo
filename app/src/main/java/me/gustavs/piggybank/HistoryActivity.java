package me.gustavs.piggybank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import me.gustavs.piggybank.database.App;
import me.gustavs.piggybank.entities.DaoSession;
import me.gustavs.piggybank.entities.Operation;
import me.gustavs.piggybank.entities.OperationDao;

public class HistoryActivity extends AppCompatActivity {

    DaoSession daoSession;
    List<Operation> operations;
    HistoryAdapter adapter;
    ListView operationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        operationList = findViewById(R.id.operationList);

        daoSession = ((App) getApplication()).getDaoSession();

        operations = daoSession.queryBuilder(Operation.class)
                .orderDesc(OperationDao.Properties.Id)
                .list();

        adapter = new HistoryAdapter(this, R.layout.activity_history_list, operations);
        operationList.setAdapter(adapter);

    }

}
