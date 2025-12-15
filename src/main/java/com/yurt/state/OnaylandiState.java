package com.yurt.state;

import com.yurt.domain.IzinTalebi;

public class OnaylandiState implements IzinState {

    @Override
    public void onayla(IzinTalebi izin) {
        throw new IllegalStateException("İzin zaten onaylanmış.");
    }

    @Override
    public void reddet(IzinTalebi izin) {
        throw new IllegalStateException("Onaylanan izin reddedilemez.");
    }
}
