# Troubleshooting

## Fixing Metals / Bloop after changing Scala or sbt version

1. Remove build state

`./scripts/utils/remove-build-state.sh`

2. Rebuild containers and reopen the devcontainer

3. In VS Code run:
Metals: Import Build

## Restarting the whole devcontainer docker compose stack

1. In VS Code run:
Dev Containers: Rebuild Container

## Memory tune

1. Run **in the host**

`./scripts/utils/memory-monitor.sh`

```
Every 1.0s: docker stats --no-stream && echo && free -h && echo && vmstat 1 2                                                                    zumba-ubuntu: Tue Mar 24 13:01:07 2026

CONTAINER ID   NAME                                CPU %     MEM USAGE / LIMIT   MEM %     NET I/O           BLOCK I/O        PIDS
586891a3c0ea   scala-template_devcontainer-dev-1   0.50%     1.924GiB / 4GiB     48.10%    29.1MB / 9.46MB   433MB / 918MB    192
9952fef94bbc   spark-worker-1                      0.09%     245.3MiB / 2GiB     11.98%    25.4MB / 18MB     919MB / 3.04GB   88
67eecfdbbdaa   spark-master                        0.08%     180.2MiB / 1GiB     17.60%    139kB / 44.2kB    2.67MB / 606kB   63

               total        used        free      shared  buff/cache   available
Mem:           7.6Gi       3.8Gi       2.5Gi       282Mi       1.9Gi       3.9Gi
Swap:          4.0Gi       231Mi       3.8Gi

procs -----------memory---------- ---swap-- -----io---- -system-- -------cpu-------
 r  b   swpd   free   buff  cache   si   so    bi    bo   in   cs us sy id wa st gu
 0  0 237204 2586608  45496 1983300   21   46   667   912 1541    1  0  0 99  0  0  0
 0  0 237204 2588024  45496 1983300    0    0     0     0  924  868  0  0 100  0  0  0
```

2. In the devcontainer terminal run the memory pressure test

`sbt "testOnly utils.SparkMemoryPressureSuite"`

3. Check the memory usage in each container, and keep an eye on `si` and `so` to detect swapping

4. Tune docker-compose memory related parameters, rebuild and start over