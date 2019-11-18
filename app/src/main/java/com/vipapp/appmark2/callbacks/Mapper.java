package com.vipapp.appmark2.callbacks;

public interface Mapper<FirstType, SecondType> {
    SecondType map(FirstType type);
}
