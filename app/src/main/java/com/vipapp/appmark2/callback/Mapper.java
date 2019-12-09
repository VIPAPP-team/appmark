package com.vipapp.appmark2.callback;

public interface Mapper<FirstType, SecondType> {
    SecondType map(FirstType type);
}
