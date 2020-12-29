package br.com.mercator.api.arquitetura.modelo;

import br.com.mercator.api.arquitetura.Encrypt;

import java.io.Serializable;
import java.util.Date;

public interface Modelo extends Serializable {

    default String geraStringComMetaDados() {
        if (getCodigo() == null || getId() == null || getVersao() == null) {
            return null;
        }
        return getClass().getSimpleName().concat(": id(")
                .concat(getId().toString()).concat(") codigo(").concat(getCodigo())
                .concat(") versao(").concat(getVersao().toString()).concat(")");
    }

    public void aplicaCodigo();

    default String geraCodigo() {
        return Encrypt.MD5digest(new Date().getTime() * Math.random());
    }

    public String getCodigo();

    public Object getId();

    public Date getUltimaAtualizacao();

    public Integer getVersao();

    public void setId(Object id);

    public void setUltimaAtualizacao(Date date);

    public void setVersao(Integer versao);
}