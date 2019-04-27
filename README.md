# ReflectASM
Java反射性能优化

### 测试感觉Java常规反射性能更好,和网上说的不太一样啊

```console
asm Reflect By Name >>      耗时：47ms; 次数：100000
asm Reflect By Index >>     耗时：30ms; 次数：100000
java Reflect >>             耗时：19ms; 次数：100000
java accessible Reflect >>  耗时：17ms; 次数：100000
```

补上reflectasm链接：https://github.com/EsotericSoftware/reflectasm
