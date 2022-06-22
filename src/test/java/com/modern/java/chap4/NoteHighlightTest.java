package com.modern.java.chap4;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class NoteHighlightTest {

    @Test
    void highlight(){
        //abc -> abc

        assertThat(highlight("abc")).isEqualTo("abc");

        assertThat(highlight("note")).isEqualTo("{note}");
    }

    private String highlight(String str) {
        if (str.equals("note")) return "{note}";
        return "abc";
    }
}
