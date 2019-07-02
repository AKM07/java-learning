package com.example.heriana.javaaplikasi.model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by AKM on 7/2/2019.
 */
public class AnswerChoice extends SugarRecord implements Serializable {

    private Object A;
    private Object B;
    private Object C;
    private Object D;

    public Object getA() {
        return A;
    }

    public void setA(Object a) {
        A = a;
    }

    public Object getB() {
        return B;
    }

    public void setB(Object b) {
        B = b;
    }

    public Object getC() {
        return C;
    }

    public void setC(Object c) {
        C = c;
    }

    public Object getD() {
        return D;
    }

    public void setD(Object d) {
        D = d;
    }
}
