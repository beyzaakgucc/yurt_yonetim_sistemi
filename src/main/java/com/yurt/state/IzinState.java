package com.yurt.state;

import com.yurt.domain.IzinTalebi;

public interface IzinState {
    void onayla(IzinTalebi izin);
    void reddet(IzinTalebi izin);
}

