# Troubleshooting

## Fixing Metals / Bloop after changing Scala or sbt version

1. Remove build state

rm -rf .bloop .metals target project/target

2. Rebuild containers and reopen the devcontainer

3. In VS Code run:
Metals: Import Build

## Restarting the whole devcontainer docker compose stack

1. In VS Code run:
Dev Containers: Rebuild Container

## To check if the host is swapping

1. Run `vmstat 1`. Example:

```
vmstat 1
procs -----------memory---------- ---swap-- -----io---- -system-- -------cpu-------
 r  b   swpd   free   buff  cache   si   so    bi    bo   in   cs us sy id wa st gu
 0  0 185084 2508476  30904 1795108   52  168   551  1645 3680    1  1  0 99  0  0  0
 0  0 185084 2508948  30904 1795108    0    0     0     0  986  900  0  0 100  0  0  0
 0  0 185084 2511372  30904 1793120    0    0     0     0 2296 2747  1  0 98  0  0  0
 ```

2. Start your process and monitor tht `si` and `so` stay close to zero

3. Check `docker stats --no-stream`. Example:

```
docker stats --no-stream
CONTAINER ID   NAME                                CPU %     MEM USAGE / LIMIT   MEM %     NET I/O           BLOCK I/O         PIDS
31c89c609750   scala-template_devcontainer-dev-1   33.57%    1.541GiB / 2GiB     77.06%    63MB / 1.7MB      1.49GB / 4.56GB   435
ac5a1904221e   spark-worker-1                      0.08%     154.6MiB / 1.5GiB   10.06%    6.71kB / 15.9kB   1.97MB / 553kB    31
0092681c4a24   spark-master                        0.08%     135.5MiB / 512MiB   26.47%    24.8kB / 5.13kB   602kB / 586kB     30
```
