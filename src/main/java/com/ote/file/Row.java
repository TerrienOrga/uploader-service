package com.ote.file;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Row {

    private String nodeLeft;
    private String nodeRight;
    private int distance;
}
