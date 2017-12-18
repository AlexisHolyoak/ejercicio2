package com.alexisholyoak.ejercicio2;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RemoveClickListner{
    Button btnadd,btnclean,btndelete;
    TextView txtnombre,txtapellido,txtedad;
    RecyclerView rvdata;
    RecyclerAdapter adapter;
    String nombre="",apellidos="",edad="";
    ArrayList<Persona> myList;
    Persona per;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onStart() {
        //ESTO HACE QUE EL TECLADO NO DISTORSIONE CUANDO HAGA POP UP
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        rvdata =(RecyclerView)findViewById(R.id.rvdata);
        adapter=new RecyclerAdapter(myList,this);
        myList=new ArrayList<>();
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvdata.setLayoutManager(layoutManager);
        rvdata.setAdapter(adapter);
        txtnombre=(TextView)findViewById(R.id.txtnombre);
        txtapellido=(TextView)findViewById(R.id.txtapellido);
        txtedad=(TextView)findViewById(R.id.txtedad);
        adapter.setOnEntryClickListener(new RecyclerAdapter.OnEntryClickListener() {
            @Override
            public void onEntryClick(View view, int position) {

                infoDialog(myList.get(position));
            }
        });
        btnadd=(Button)findViewById(R.id.btnadd);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre=txtnombre.getText().toString();
                apellidos=txtapellido.getText().toString();
                edad=txtedad.getText().toString();
                if(nombre.matches("")){
                    Toast.makeText(v.getContext(), "No has ingresado el nombre", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(apellidos.matches("")){
                    Toast.makeText(v.getContext(), "No has ingresado el apellido", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edad.matches("")){
                    Toast.makeText(v.getContext(), "No has ingresado la edad", Toast.LENGTH_SHORT).show();
                    return;
                }
                Persona persona=new Persona();
                persona.setNombre(nombre);
                persona.setApellido(apellidos);
                persona.setEdad(edad);
                myList.add(persona);
                adapter.notifyData(myList);
                txtnombre.setText("");
                txtapellido.setText("");
                txtedad.setText("");
            }
        });

    }
    @Override
    public void OnRemoveClick(int index) {
        myList.remove(index);
        adapter.notifyData(myList);
    }
    public void infoDialog(Persona per){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Datos de la persona");
        builder.setMessage("Nombres: "+per.getNombre() +"\n" + "Apellidos: "+per.getApellido()+"\n"+ "Edad: "+ per.getEdad());
        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
