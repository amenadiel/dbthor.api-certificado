package com.dbthor.tools;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
//import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Libreria de metodos para el manejo del IO
 *
 * Created by CSATTLER on 30-01-2017.
 */
@SuppressWarnings("unused")
public class IOTools {

    /*----------------------------------------------------------------------------------------------------------------*/
    /**
     * Funcion de Encode64 para un archivo
     *
     * @param fileName      Nombre del Archivo incluido su path
     * @return              String Encode64
     * @throws IOException  IOException
     */
    public static String encodeBase64File(String fileName) throws IOException {
        File file = new File(fileName);
        byte[] encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));

        return new String(encoded, StandardCharsets.UTF_8);
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /**
     * Funcion de Encode64 para un archivo
     *
     * @param fileName      Nombre del Archivo incluido su path
     * @return              String Encode64
     * @throws IOException  IOException
     */
    public static String decodeBase64File(String fileName) throws IOException {
        File file = new File(fileName);
        byte[] encoded = Base64.decodeBase64(FileUtils.readFileToByteArray(file));
        return new String(encoded, StandardCharsets.UTF_8);
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /**
     * Funcion de Encode64 para un string
     *
     * @param data      Nombre del Archivo incluido su path
     * @return              String Encode64
     */
    public static String encodeBase64String(String data) {
        byte[] encoded = Base64.encodeBase64(data.getBytes());

        return new String(encoded, StandardCharsets.ISO_8859_1);
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /**
     * Funcion de Encode64 para un string
     *
     * @param data      Nombre del Archivo incluido su path
     * @return              String Encode64
     */
    public static String decodeBase64String(String data) {
        byte[] encoded = Base64.decodeBase64(data.getBytes());
        return new String(encoded, StandardCharsets.UTF_8);
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /**
     * Funcion de Encode64 para un string
     *
     * @param data      Nombre del Archivo incluido su path
     * @return              String Encode64
     */
    public static byte[] decodeBase64Byte(String data) {
        //byte[] encoded = Base64.decodeBase64(data.getBytes());
        //return new String(encoded, StandardCharsets.UTF_8);
        return Base64.decodeBase64(data.getBytes());
    }


    /*----------------------------------------------------------------------------------------------------------------*/
    /**
     * Convierte un InputStream a String
     *
     * @param is    InputStream
     * @return      String convertido
     */
    public static String getStringFromInputStream(InputStream is) throws IOException {
        return IOUtils.toString(is);
    }


    /*----------------------------------------------------------------------------------------------------------------*/
    /**
     * Convierte un String en InputStream
     *
     * @param data    String
     * @return      InputStream convertido
     */
    public static InputStream getInputStreamFromString(String data) {
        return new ByteArrayInputStream(data.getBytes());
    }

    /*----------------------------------------------------------------------------------------------------------------
    public static String toPdfEncodeB64(String html) throws Exception {
        try {
            final ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();

            byte[] toPdf;
            try (ByteArrayOutputStream fos = new ByteArrayOutputStream(html.length())) {
                renderer.createPDF(fos);
                toPdf = fos.toByteArray();
                byte[] encoded = Base64.encodeBase64(toPdf);
                return new String(encoded, StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            throw e;
        }
    }
*/

    /*----------------------------------------------------------------------------------------------------------------*/
    public static String extractRegExp(String line, String pattern, Integer group) {
        String out="";

        // Create a Pattern object
        Pattern r = Pattern.compile("(?si)"+pattern);

        // Now create matcher object.
        Matcher m = r.matcher(line);
        if (m.find( )) {
            out= m.group(group);
        }
        return out;
    }

    public static String getClassName(String data) {
        return data.substring(data.lastIndexOf("."),data.indexOf("@"));
    }
}
