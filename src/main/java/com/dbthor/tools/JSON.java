package com.dbthor.tools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSON {

    public static String getString(JSONObject json, String el) {
      /*  System.out.println("JSON " + json.toString());
        System.out.println("EL " + el);
        System.out.println("EL FIN");*/
        try {
            return json.getString(el).trim();
        } catch (JSONException ex) {
            try {
                return "" + json.getInt(el);
            } catch (JSONException ex2) {

                try {
                    return "" + json.getDouble(el);
                } catch (JSONException ex3) {

                    try {
                        return "" + json.getLong(el);
                    } catch (JSONException ex4) {

                        try {
                            return "" + json.get(el);
                        } catch (JSONException ex5) {

                            return null;
                        }
                    }
                }
            }
        }
    }

    /**
     * Metodo que obtiene un boolean de un json. si este es true o 1 debuelve 1
     * en caso contrario retorna 0
     *
     * @param json JSONOObject
     * @param el   String
     * @return 1 en caso de true y 0 en caso de false
     */
    public static int getBoolean(JSONObject json, String el) {
        try {
            return json.getBoolean(el) ? 1 : 0;
        } catch (JSONException ex) {
            try {

                return json.getString(el).equalsIgnoreCase("true") ? 1 : 0;
            } catch (JSONException ex1) {
                try {

                    return json.getInt(el) == 1 ? 1 : 0;
                } catch (JSONException ex2) {
                    return 0;
                }
            }
        }
    }

    public static int getInt(JSONObject json, String el) throws JSONException {
        try {
            return json.getInt(el);
        } catch (Exception e) {
            return Integer.parseInt(JSON.getString(json, el));
        }

    }

    public static double getDouble(JSONObject json, String el) throws JSONException {
        try {
            return json.getDouble(el);
        } catch (Exception e) {
            return Double.parseDouble(getString(json, el));
        }
    }

    public static float getFloat(JSONObject json, String el) throws JSONException {
        try {
            return json.getLong(el);
        } catch (Exception e) {
            return Float.parseFloat(JSON.getString(json, el));
        }

    }

    public static Object get(JSONObject json, String el) {
        try {
            return json.get(el);
        } catch (JSONException ex) {
           // DEF.log().log(Level.SEVERE, "No se encontro el objeto en el json " + el, ex);
            System.out.println("No se encontro el objeto en el json " + el);
            return null;
        }
    }

    public static JSONArray getArray(JSONObject json, String el) {
        try {
            return json.getJSONArray(el);
        } catch (JSONException ex) {

            return null;
        }
    }


}
