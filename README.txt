# Troubleshooting

## Fixing Metals docker root-test not compiling

1. Remove Metals + Bloop state

```
rm -rf .bloop .metals target project/target
```

2. Then VS Code to `Metals: Import Build`