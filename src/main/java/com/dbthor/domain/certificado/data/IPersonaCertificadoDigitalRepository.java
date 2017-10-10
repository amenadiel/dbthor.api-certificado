package com.dbthor.domain.certificado.data;

import com.dbthor.domain.certificado.data.entity.EPersonaCertificadoDigital;
import com.dbthor.domain.certificado.data.entity.EPersonaCertificadoDigitalPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by CSATTLER on 07-02-2017.
 */
public interface IPersonaCertificadoDigitalRepository extends CrudRepository<EPersonaCertificadoDigital, EPersonaCertificadoDigitalPK> {


    @Query("SELECT i FROM EPersonaCertificadoDigital i " +
            " WHERE " +
            "   i.personaId= :persId" +
            "")
    List<EPersonaCertificadoDigital> getCertificadoByPersona(
            @Param("persId") String persId
    );

    @Query("SELECT i FROM EPersonaCertificadoDigital i " +
            " WHERE " +
            "   i.personaId= :persId" +
            "   and i.tipoCertificadoUso.id = :usoId " +
            "")
    EPersonaCertificadoDigital getCertificadoByPersonaUso(
            @Param("persId") String persId,
            @Param("usoId") String usoId
    );


    @Query("SELECT i FROM EPersonaCertificadoDigital i " +
            " WHERE " +
            "   i.certificadoDigitalId= :certId" +
            "   and i.tipoCertificadoUso.id = :usoId " +
            "")
    EPersonaCertificadoDigital getCertificadoPersona(
            @Param("certId") String certId,
            @Param("usoId") String usoId
    );

    @Query("SELECT i FROM EPersonaCertificadoDigital i " +
            " WHERE " +
            "   i.certificadoDigitalId= :certId" +
            "")
    List<EPersonaCertificadoDigital> getCertificadoById(
            @Param("certId") String certId
    );

}
