package br.com.mercator.api.arquitetura.controle;

import br.com.mercator.api.arquitetura.UtilitarioReflexao;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class To {

    private String codigo;

    public <T> T transfereValoresCamposPara(T object) {
        return transfereValoresCamposPara(object, false);
    }

    public <T> T transfereValoresCamposPara(T object, boolean ignoraNulos) {
        try {
            UtilitarioReflexao utilitario = new UtilitarioReflexao(this);
            return utilitario.transfereValoresCamposPara(object, ignoraNulos);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void recebeValoresCamposDe(Object object) {
        recebeValoresCamposDe(object, false);
    }

    public void recebeValoresCamposDe(Object object, boolean ignoraNulos) {
        try {
            UtilitarioReflexao utilitario = new UtilitarioReflexao(object);
            utilitario.transfereValoresCamposPara(this, ignoraNulos);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
