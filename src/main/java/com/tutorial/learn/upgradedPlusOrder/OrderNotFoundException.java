package com.tutorial.learn.upgradedPlusOrder;

class OrderNotFoundException extends RuntimeException{
    OrderNotFoundException(Long id) {
        super("Could not find order " + id);
    }
}
