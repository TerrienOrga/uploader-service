package com.ote.framework.model;

import java.io.Serializable;

public interface IEntity<TK extends Serializable> {

    void setId(TK key);

    TK getId();
}
