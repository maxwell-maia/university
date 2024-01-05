cmake_minimum_required(VERSION 3.26)
project(CLion C)

set(CMAKE_C_STANDARD 11)

add_executable(CLion
        tests.c
        assignment-1.c
        genericLinkedList.c
        genericLinkedList.h)
