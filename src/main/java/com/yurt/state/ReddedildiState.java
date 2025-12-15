package com.yurt.state;

import com.yurt.domain.IzinTalebi;

public class ReddedildiState implements IzinState {

    @Override
    public void onayla(IzinTalebi izin) {
        throw new IllegalStateException("Reddedilen izin onaylanamaz.");
    }

    @Override
    public void reddet(IzinTalebi izin) {
        throw new IllegalStateException("İzin zaten reddedilmiş.");
    }
}

