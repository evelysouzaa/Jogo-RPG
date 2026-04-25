# Legends of Java — Sistema de RPG

Projeto Java demonstrando Programação Orientada a Objetos com:
- Classes Abstratas (`Personagem`)
- Herança (`Guerreiro`, `Mago`, `Arqueiro`)
- Polimorfismo (`ArrayList<Personagem>`)
- Encapsulamento (atributos `protected`/getters/setters)
- Sobrescrita de métodos (`@Override`)



## Como compilar e executar

```bash
# Compilar (da raiz do projeto)
javac -d out $(find src -name "*.java")

# Executar
java -cp out br.com.rpg.principal.JogoRPG
```
![print](https://i.imgur.com/NSQlkoW.png)
