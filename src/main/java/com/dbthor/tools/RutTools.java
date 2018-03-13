package com.dbthor.tools;

/**
 * Libreria de metodos para el manejo del rut
 *
 * Created by CSATTLER on 09-03-2017.
 */
@SuppressWarnings("unused")
public class RutTools {
    /**
     * Obtiene el digito verificador de un RUT
     *
     * @param vrut  Valor del Rut
     * @return      digito verificador
     */
    public static String getDvFromRut(String vrut) {
        try {
            String rut = vrut.trim();

            int cantidad = rut.length();
            int factor = 2;
            int suma = 0;

            for (int i = cantidad; i > 0; i--) {
                if (factor > 7) {
                    factor = 2;
                }
                suma += (Integer.parseInt(rut.substring((i - 1), i))) * factor;
                factor++;

            }
            String verificador = String.valueOf(11 - suma % 11);

            switch (verificador) {
                case "10":
                    return "k";
                case "11":
                    return "0";
                default:
                    return verificador;
            }

        } catch (Exception ex) {
            return "";
        }

    }

    public static boolean validarRut(String rut) {

        boolean validacion = false;
        try {
            rut =  rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }

        } catch (Exception e) {
            return false;
        }
        return validacion;
    }

    /**
     * Una forma simple de formatear el RUT dejándolos en formato XX.XXX.XXX-X
     * @param rut   String del RUT
     * @return      String Rut con fomato
     */
    public static String FormatearRut(String rut) {

        int cont = 0;
        StringBuilder format;
        rut = rut.replace(".", "");
        rut = rut.replace("-", "");
        format = new StringBuilder("-" + rut.substring(rut.length() - 1));
        for (int i = rut.length() - 2; i >= 0; i--) {
            format.insert(0, rut.substring(i, i + 1));
            cont++;
            if (cont == 3 && i != 0) {
                format.insert(0, ".");
                cont = 0;
            }
        }
        return format.toString();
    }


    public static Long getRutNum(String rut) {
        String valorStr;
        rut = rut.replace(".", "");
        if (rut.indexOf("-")>0)
            valorStr =  rut.substring(0,rut.indexOf("-"));
        else
            valorStr =  rut;

        try {
            return Long.parseLong(valorStr);
        } catch (Exception ex) {
            return null;
        }
    }
}
