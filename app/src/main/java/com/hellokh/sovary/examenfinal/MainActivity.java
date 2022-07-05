package com.hellokh.sovary.examenfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RVAdapter adapter;
    DAOMesa dao;
    boolean isLoading = false;
    String key = null;

    private FirebaseStorage storage;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new RVAdapter(this);
        recyclerView.setAdapter(adapter);
        dao = new DAOMesa();
        loadData();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Mesa");

        Button btnMesa1 = findViewById(R.id.btn_mesa1);
        Button btnMesa2 = findViewById(R.id.btn_mesa2);
        Button btnMesa3 = findViewById(R.id.btn_mesa3);
        Button btnMesa4 = findViewById(R.id.btn_mesa4);
        Button btnMesa5 = findViewById(R.id.btn_mesa5);
        Button btnMesa6 = findViewById(R.id.btn_mesa6);
        Button btnMesa7 = findViewById(R.id.btn_mesa7);
        Button btnMesa8 = findViewById(R.id.btn_mesa8);

        btnMesa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mesa m = new Mesa("Mesa 1", true);
                String dhId = mDatabaseRef.push().getKey();
                mDatabaseRef.child(dhId).setValue(m);
                loadData();
                btnMesa1.setEnabled(false);
            }
        });

        btnMesa2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mesa m = new Mesa("Mesa 2", true);
                String dhId = mDatabaseRef.push().getKey();
                mDatabaseRef.child(dhId).setValue(m);
                loadData();
                btnMesa2.setEnabled(false);
            }
        });

        btnMesa3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mesa m = new Mesa("Mesa 3", true);
                String dhId = mDatabaseRef.push().getKey();
                mDatabaseRef.child(dhId).setValue(m);
                loadData();
                btnMesa3.setEnabled(false);
            }
        });

        btnMesa4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mesa m = new Mesa("Mesa 4", true);
                String dhId = mDatabaseRef.push().getKey();
                mDatabaseRef.child(dhId).setValue(m);
                loadData();
                btnMesa4.setEnabled(false);
            }
        });

        btnMesa5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mesa m = new Mesa("Mesa 5", true);
                String dhId = mDatabaseRef.push().getKey();
                mDatabaseRef.child(dhId).setValue(m);
                loadData();
                btnMesa5.setEnabled(false);
            }
        });

        btnMesa6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mesa m = new Mesa("Mesa 6", true);
                String dhId = mDatabaseRef.push().getKey();
                mDatabaseRef.child(dhId).setValue(m);
                loadData();
                btnMesa6.setEnabled(false);
            }
        });

        btnMesa7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mesa m = new Mesa("Mesa 7", true);
                String dhId = mDatabaseRef.push().getKey();
                mDatabaseRef.child(dhId).setValue(m);
                loadData();
                btnMesa7.setEnabled(false);
            }
        });

        btnMesa8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mesa m = new Mesa("Mesa 8", true);
                String dhId = mDatabaseRef.push().getKey();
                mDatabaseRef.child(dhId).setValue(m);
                loadData();
                btnMesa8.setEnabled(false);
            }
        });
    }

    private void loadData() {
        dao.get(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Mesa> ms = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Mesa m = data.getValue(Mesa.class);
                    m.setKey(data.getKey());
                    ms.add(m);
                    key = data.getKey();
                }
                //Este es el método para ordenar los registros
                Collections.sort(ms);
                //Collections.reverse(ms); //Con este apenas se abre la app los carga al revés, pero a la hora
                                            // de insertar se insertan normal, entonces hay que buscar otra forma
                adapter.setItems(ms);
                adapter.notifyDataSetChanged();
                isLoading = false;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}