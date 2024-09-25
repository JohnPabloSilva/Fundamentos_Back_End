package br.ufac.sgcm.dao;

import java.util.List;
//<T> se trata de um recurso de Java que se usa quando o valor retornado pode ser de vários tipos
//De maneira simples, é como se o T fosse genérico e aceitasse "Qualquer" coisa
public interface IDao<T> {
    List<T> get();

    List<T> get(String termoDeBusca);

    T get(Long id);

    int insert(T objeto);

    int update(T objeto);

    int delete(T objeto);
    
}
