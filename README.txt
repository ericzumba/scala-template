# Troubleshooting

## Fixing Metals / Bloop after changing Scala or sbt version

1. Remove build state

rm -rf .bloop .metals target project/target

2. Rebuild containers and reopen the devcontainer

3. In VS Code run:
Metals: Import Build