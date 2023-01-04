#!/bin/bash
sed -e'/^$/d' -e's/^/signals.add(/' -e's/\[/listOf(/g' -e's/\]/)/g' -e's/$/\)/g' -e's/()/<Any>()/g'