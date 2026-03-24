#! /bin/bash

watch -n 1 'docker stats --no-stream && echo && free -h && echo && vmstat 1 2'