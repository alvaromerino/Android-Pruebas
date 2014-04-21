package com.alvaromerino.android.sqlite.adapters;

import java.util.List;

import com.alvaromerino.android.sqlite.R;
import com.alvaromerino.android.sqlite.data.Usuario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class UsuarioAdapter extends ArrayAdapter<Usuario> {

	private List<Usuario> usuarios;
	private LayoutInflater inflater;
	
	public UsuarioAdapter(Context context, List<Usuario> usuarios) {
		super(context, R.layout.item_listview_usuario, usuarios);
		this.usuarios = usuarios;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = inflater.inflate(R.layout.item_listview_usuario, parent, false);
		TextView textview = (TextView) view.findViewById(R.id.textviewItemUsuario);
		Usuario usuario = usuarios.get(position);
		textview.setText(String.format("%s", usuario.getUser()));

		return view;
	}
	
	
}
