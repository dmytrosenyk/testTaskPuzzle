#!/bin/bash
Xvfb :99 &
export DISPLAY=:99
x11vnc -forever -usepw -create &
/bin/bash
