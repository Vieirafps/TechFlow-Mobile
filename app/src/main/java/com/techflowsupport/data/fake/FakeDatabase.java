package com.techflowsupport.data.fake;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.techflowsupport.data.model.Chamado;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FakeDatabase {

    private static final String PREF_NAME = "fake_db";
    private static final String KEY_CHAMADOS = "chamados";

    public static void salvarChamado(Context context, Chamado chamado) {
        List<Chamado> lista = getChamados(context);
        lista.add(chamado);
        salvarLista(context, lista);
    }

    public static List<Chamado> getChamados(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String json = sp.getString(KEY_CHAMADOS, "");

        if (json.isEmpty()) return new ArrayList<>();

        Type type = new TypeToken<List<Chamado>>() {}.getType();
        return new Gson().fromJson(json, type);
    }

    public static void salvarLista(Context context, List<Chamado> lista) {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        String json = new Gson().toJson(lista);
        editor.putString(KEY_CHAMADOS, json);
        editor.apply();
    }
}
