package com.dbthor.domain.certificado.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.UUID;

import static com.dbthor.tools.IOTools.decodeBase64Byte;

/**
 * Contenedor de certificado digital
 *
 * Created by CSATTLER on 07-02-2017.
 */
@Log4j2
public class CertificadoDigital {
    private static final Logger logger = LogManager.getLogger(CertificadoDigital.class.getName());
    private KeyStore ks;            //Certificado contenedor
    private String dataEncodeB64;   //Certificado en formato Encode Base 64
    @Getter private String password;        //Clave del certificado
    @Getter private String alias;
    @Getter private Date fechaCreacion;
    @Getter private Date fechaExpiracion;
    @Getter private String mail;
    @Getter private String subject;
    @Getter private String issuer;
    @Getter @Setter private UUID id;


    //toDo: agregar atributos del certificado que seran almacenado.

    /**
     * Carga el certificado PFX/P12 y lo valida con contra sus password
     *
     * @param certificadoEncodeB64      certificado en formato encode B64
     * @param password                  password del archivo
     * @throws KeyStoreException        KeyStore Exception
     * @throws IOException              IO Expception
     * @throws CertificateException     Certificate Exception
     * @throws NoSuchAlgorithmException NoSuchAlgorithm Exception
     */
    public void loadCertificado(String certificadoEncodeB64, String mail, String password, String trxId)
            throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        log.debug("{} START loadCertificado()", trxId );
        log.debug("{}   PARAM certData  : {}", trxId,
                (certificadoEncodeB64!=null && certificadoEncodeB64.length()>30?certificadoEncodeB64.substring(0,30):certificadoEncodeB64) );
        log.debug("{}   PARAM mail      : {}", trxId, mail );
        log.debug("{}   PARAM password  : {}", trxId, password );

        this.dataEncodeB64 =  certificadoEncodeB64;
        this.password =  password;
        this.mail =  mail;

        byte[] data= decodeBase64Byte(certificadoEncodeB64);

        InputStream stream = new ByteArrayInputStream(data);

        ks = KeyStore.getInstance("PKCS12");
        ks.load(stream, password.toCharArray());

        alias = ks.aliases().nextElement();
        X509Certificate cert = this.getX509Certificate(trxId);

        fechaCreacion = cert.getNotBefore();
        fechaExpiracion = cert.getNotAfter();
        subject = cert.getSubjectDN().getName();
        issuer = cert.getIssuerDN().getName();

        log.debug("{} Datos del Certificado:", trxId );
        log.debug("{}  - fechaCreacion  :{}", trxId,fechaCreacion );
        log.debug("{}  - fechaExpiracion:{}", trxId,fechaExpiracion );
        log.debug("{}  - subject        :{}", trxId,subject );
        log.debug("{}  - issuer         :{}", trxId,issuer );

        log.debug("{} END loadCertificado()", trxId );
    }

    /**
     * Obtiene la llave privada del certificado
     *
     * @return Privatekey: objecto llave del certificado publica
     *
     * @throws KeyStoreException            KeyStore Exception
     * @throws IOException                  IO Exception
     * @throws NoSuchAlgorithmException     No Such Algorithm Exception
     * @throws CertificateException         Certificate Exception
     * @throws UnrecoverableKeyException    Unrecoverable Key Exception
     */
    public PrivateKey getPrivateKey() throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {


        log.debug("getPrivateKey() - Usando certificado {} del archivo PKCS12", alias);
        return (PrivateKey) ks.getKey(alias, password.toCharArray());
    }

    /**
     * Obtiene certificado X509 del certificado
     *
     * @return X509Certificate              certificado X509
     * @throws KeyStoreException            KeyStore Exception
     * @throws IOException                  IO Exception
     * @throws NoSuchAlgorithmException     No Such Algorithm Exception
     * @throws CertificateException         Certificate Exception
     */
    public X509Certificate getX509Certificate() throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
        String alias = ks.aliases().nextElement();
        log.debug("getX509Certificate() - Usando certificado {} del archivo PKCS12", alias);

        return (X509Certificate) ks.getCertificate(alias);
    }

    public X509Certificate getX509Certificate(String trxId) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
        String alias = ks.aliases().nextElement();
        log.debug("{} getX509Certificate() - Usando certificado {} del archivo PKCS12", trxId, alias);

        return (X509Certificate) ks.getCertificate(alias);
    }

    /**
     * Obtiene los datos del certificado en formato Encode Base 64
     *
     * @return String base 64
     */
    public String getCertificadoData() {
        return dataEncodeB64;
    }


}
