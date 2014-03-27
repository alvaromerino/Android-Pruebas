package com.alvaro93mg.android.parcelable.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.alvaro93mg.android.parcelable.data.Persona;

import java.util.ArrayList;

/**
 * Created by Alvaro on 25/03/14.
 */
public class PersonaArrayAdapter extends ArrayAdapter<Persona> {

    private ArrayList<Persona> personas;
    private LayoutInflater inflater;

    public PersonaArrayAdapter(Context context, ArrayList<Persona> personas) {
        super(context, android.R.layout.simple_list_item_1, personas);
        this.personas = personas;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        Persona persona = personas.get(position);
        textView.setText(String.format("%s - %s - %s", persona.getDni(), persona.getNombre(), persona.getApellido1()));
        return view;
    }
}
