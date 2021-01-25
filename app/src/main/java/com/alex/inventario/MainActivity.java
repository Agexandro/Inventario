package com.alex.inventario;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    private SqliteHelper sqliteHelper;
    private ListView listView;
    private ListViewAdapter listViewAdapter;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqliteHelper = new SqliteHelper(this);
        listView = (ListView) findViewById(R.id.item_list);
        handler = new Handler();

        ProgressDialog progressDialog = ProgressDialog.show(this, "Cargando", "Porfavor, espere");
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                listViewAdapter = new ListViewAdapter(getApplicationContext(), sqliteHelper.getAllItems());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listView.setAdapter(listViewAdapter);
                        progressDialog.dismiss();
                    }
                });
            }
        };
        thread.run();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.inventory_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_item){
            Intent intent = new Intent(this, Registro.class);
            startActivityForResult(intent, REQUEST_CODE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE == requestCode){
            if(resultCode == RESULT_OK){
                sqliteHelper.saveItem(
                        data.getStringExtra(SqliteConstants.COL_NAME),
                        data.getStringExtra(SqliteConstants.COL_DETAILS),
                        data.getStringExtra(SqliteConstants.COL_LOTE),
                        data.getStringExtra(SqliteConstants.COL_CADUCITY),
                        data.getStringExtra(SqliteConstants.COL_QUANTITY),
                        data.getStringExtra(SqliteConstants.COL_KEY)
                );
                listViewAdapter.changeCursor(sqliteHelper.getAllItems());
            }else {
                listViewAdapter.changeCursor(sqliteHelper.getAllItems());
            }
        }
    }
}