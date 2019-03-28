package main

import "testing"

func TestDemo(t *testing.T) {
    if 1 != 1 {
        t.Errorf("Hi")
    }
}