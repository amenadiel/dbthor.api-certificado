package com.dbthor.domain.certificado.cliente.mail.entity;

import com.dbthor.tools.JSON;
import org.json.JSONObject;

public class toolsMail {

    /**
     * Metodo para reemplazar las variables enviadas en el texto de correos
     *
     * @param docBase
     * @param valReemplazo
     * @return
     */
    public static TextoMail generaTextoNotificacionJson(TextoMail docBase, JSONObject valReemplazo) {

        for (String val : docBase.getListVar()) {
            String valNuevo = JSON.getString(valReemplazo, val.replace("--", "")) != null ? JSON.getString(valReemplazo, val.replace("--", "")) : " ";
            valNuevo = (valNuevo == null ? "" : valNuevo);
            docBase.setTexto(docBase.getTexto().replaceAll(val, valNuevo));
            docBase.setTitulo(docBase.getTitulo().replaceAll(val, valNuevo));

        }

        return docBase;
    }
}
