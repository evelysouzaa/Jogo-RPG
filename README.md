# Legends of Java — Sistema de RPG

Projeto Java demonstrando Programação Orientada a Objetos com:
- Classes Abstratas (`Personagem`)
- Herança (`Guerreiro`, `Mago`, `Arqueiro`)
- Polimorfismo (`ArrayList<Personagem>`)
- Encapsulamento (atributos `protected`/getters/setters)
- Sobrescrita de métodos (`@Override`)

## Estrutura de Pacotes

```
src/br/com/rpg/
├── modelo/
│   ├── Personagem.java   ← classe abstrata base
│   ├── Guerreiro.java
│   ├── Mago.java
│   └── Arqueiro.java
├── sistema/
│   └── Arena.java        ← sistema de batalha e torneio
└── principal/
    └── JogoRPG.java      ← ponto de entrada (main)
```

## Como compilar e executar

```bash
# Compilar (da raiz do projeto)
javac -d out $(find src -name "*.java")

# Executar
java -cp out br.com.rpg.principal.JogoRPG
```

Requer Java 8+.
