package br.com.mercator.api.arquitetura.modelo;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@SuppressWarnings("serial")
public abstract class ModeloGenericoComMetadados implements Modelo {

    @Column(nullable = false, name = "ultima_atualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date ultimaAtualizacao;

    @Version
    protected Integer versao;

    @Column(nullable = false, unique = true, updatable = false, length = 30)
    protected String codigo;


    @Override
    public String getCodigo() {
        return codigo;
    }

    @Override
    public Date getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    @Override
    public void setUltimaAtualizacao(Date ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    @Override
    public Integer getVersao() {
        return versao;
    }

    @Override
    public void setVersao(Integer versao) {
        this.versao = versao;
    }

    @Override
    public void aplicaCodigo() {
        if (this.codigo == null)
            this.codigo = geraCodigo();
    }

    @Override
    public String toString() {
        String entityString = geraStringComMetaDados();
        if (entityString == null)
            return super.toString();
        else
            return entityString;
    }

    @Override
    public int hashCode() {
        return (getClass().hashCode() / 2 + ((int) (Math.random() * 299)));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Modelo other = (Modelo) obj;
        if (codigo == null) {
            if (other.getCodigo() != null)
                return false;
        } else if (!codigo.equals(other.getCodigo()))
            return false;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        if (versao == null) {
            if (other.getVersao() != null)
                return false;
        } else if (!versao.equals(other.getVersao()))
            return false;
        return true;
    }
}