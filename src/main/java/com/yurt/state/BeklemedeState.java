package com.yurt.state;

import com.yurt.domain.IzinDurumu;
import com.yurt.domain.IzinTalebi;

public class BeklemedeState implements IzinState {

    @Override
    public void onayla(IzinTalebi izin) {
        izin.setDurum(IzinDurumu.ONAYLANDI);
    }

    @Override
    public void reddet(IzinTalebi izin) {
        izin.setDurum(IzinDurumu.REDDEDILDI);
    }
}
