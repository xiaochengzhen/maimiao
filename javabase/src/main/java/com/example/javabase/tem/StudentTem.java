package com.example.javabase.tem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public abstract class StudentTem {
    @Autowired
    private Play play;

    public void student() {
        doSomething();
        play.game();
    }

    protected abstract void doSomething();
}
