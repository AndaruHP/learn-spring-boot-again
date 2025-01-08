package com.tutorial.learn;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OneJsonTest {
    @Test
    void myFirstTest() {
        // true
        assertThat(42).isEqualTo(42);

        // false
//        assertThat(1).isEqualTo(42);
    }
}
