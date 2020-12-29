package br.com.mercator.api.arquitetura;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UtilitarioReflexao {

    private Object object;

    public UtilitarioReflexao(Object object) {
        super();
        this.object = object;
    }

    public Object acessaValorCampo(String fieldName) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Field declaredField = acessaCampo(fieldName, object.getClass());
        declaredField.setAccessible(true);
        Object fieldValue = declaredField.get(object);
        declaredField.setAccessible(false);
        return fieldValue;
    }

    public void defineValorCampo(String nomeCampo, Object valorCampo)
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

        Field declaredField = acessaCampo(nomeCampo, object.getClass());
        declaredField.setAccessible(true);
        declaredField.set(object, valorCampo);
        declaredField.setAccessible(false);
    }

    public List<String> listaCamposRepresentaveisPor(Class<?> clazz) {
        Field[] declaredFields = object.getClass().getDeclaredFields();
        List<String> listFieldName = new ArrayList<>();
        for (Field field : declaredFields) {
            if (clazz.isAssignableFrom(field.getType())) {
                listFieldName.add(field.getName());
            }
        }
        return listFieldName;
    }

    public <T> T transfereValoresCamposPara(T objetoReceptor, boolean ignoraValoresNulos) throws IllegalAccessException {
        List<Field> camposObjetoOriginal = listaCamposRecursivamente();
        UtilitarioReflexao reflexaoObjetoReceptor = new UtilitarioReflexao(objetoReceptor);
        for (Field campo : camposObjetoOriginal) {
            try {
                Object o = acessaValorCampo(campo.getName());

                if (ignoraValoresNulos && o == null) {
                    continue;
                }
                boolean verificaSeCampoComplexo = !(verificaSeCampoString(campo) || verificaSeCampoNumero(campo));
                if (verificaSeCampoComplexo) {
                    Field tipoCampoComplexo = reflexaoObjetoReceptor.acessaCampo(campo.getName());
                    Object objetoCampoComplexo = reflexaoObjetoReceptor.acessaValorCampo(campo.getName());
                    if (objetoCampoComplexo == null) {
                        objetoCampoComplexo = tipoCampoComplexo.getType().getConstructor().newInstance();
                    }
                    UtilitarioReflexao utilitarioReflexaoCampo = new UtilitarioReflexao(o);
                    utilitarioReflexaoCampo.transfereValoresCamposPara(objetoCampoComplexo, ignoraValoresNulos);
                    reflexaoObjetoReceptor.defineValorCampo(campo.getName(), objetoCampoComplexo);
                } else {
                    reflexaoObjetoReceptor.defineValorCampo(campo.getName(), o);
                }
            } catch (NoSuchFieldException e) {
                continue;
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return objetoReceptor;
    }

    private boolean verificaSeCampoString(Field campo) {
        return campo.getType().isAssignableFrom(String.class);
    }

    private boolean verificaSeCampoNumero(Field campo) {
        return campo.getType().isAssignableFrom(Number.class);
    }

    private List<Field> listaCamposRecursivamente() {
        return listaCamposRecursivamente(object.getClass(), null);
    }

    private List<Field> listaCamposRecursivamente(Class clazz, List<Field> camposDeclarados) {

        if (Object.class.equals(clazz)) {
            return camposDeclarados;
        }

        if (camposDeclarados == null) {
            camposDeclarados = new ArrayList<>();
        }

        camposDeclarados.addAll(Arrays.asList(clazz.getDeclaredFields()));
        return listaCamposRecursivamente(clazz.getSuperclass(), camposDeclarados);
    }

    private Field acessaCampo(String fieldName) throws NoSuchFieldException {
        return acessaCampo(fieldName, object.getClass());
    }

    private Field acessaCampo(String fieldName, Class clazz) throws NoSuchFieldException {
        try {
            Field declaredField = clazz.getDeclaredField(fieldName);
            return declaredField;
        } catch (NoSuchFieldException e) {
            if (clazz.equals(Object.class)) {
                throw e;
            }
            return acessaCampo(fieldName, clazz.getSuperclass());
        }
    }
}
