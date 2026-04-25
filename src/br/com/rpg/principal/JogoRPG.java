package br.com.rpg.principal;

import br.com.rpg.modelo.*;
import br.com.rpg.sistema.Arena;
import java.util.ArrayList;

/**
 * JogoRPG — Ponto de entrada principal do jogo "Legends of Java".
 *
 * Demonstra todos os conceitos de POO aplicados:
 *   - Classes abstratas e herança
 *   - Polimorfismo via ArrayList<Personagem>
 *   - Encapsulamento (getters/setters)
 *   - Sobrescrita de métodos (@Override)
 *   - Sistema de combate, experiência e torneio
 */
public class JogoRPG {

    public static void main(String[] args) throws InterruptedException {

        banner();

        // ══════════════════════════════════════════════════════════════════════
        // PARTE 1 — Criação de personagens
        // ══════════════════════════════════════════════════════════════════════
        titulo("1. CRIAÇÃO DE PERSONAGENS");

        Personagem guerreiro = new Guerreiro("Thorin");
        Personagem mago      = new Mago("Gandalf");
        Personagem arqueiro  = new Arqueiro("Legolas");

        // ══════════════════════════════════════════════════════════════════════
        // PARTE 2 — Demonstração de Polimorfismo
        // Todos tratados como Personagem, mas comportamento é de cada subclasse
        // ══════════════════════════════════════════════════════════════════════
        titulo("2. STATUS INICIAL (POLIMORFISMO)");

        ArrayList<Personagem> grupo = new ArrayList<>();
        grupo.add(guerreiro);
        grupo.add(mago);
        grupo.add(arqueiro);

        for (Personagem p : grupo) {
            p.exibirStatus();   // polimorfismo: Arqueiro sobrescreveu exibirStatus()
        }

        // ══════════════════════════════════════════════════════════════════════
        // PARTE 3 — Teste de habilidades especiais e métodos exclusivos
        // ══════════════════════════════════════════════════════════════════════
        titulo("3. HABILIDADES ESPECIAIS");

        // Guerreiro usa Investida Furiosa no Mago (dummy — só para demonstrar)
        System.out.println(">> Guerreiro usa habilidade especial em um alvo temporário:");
        Personagem alvoTreino = new Guerreiro("Boneco de Treino");
        guerreiro.usarHabilidadeEspecial(alvoTreino);

        // Mago usa Meteoro Arcano
        System.out.println(">> Mago usa habilidade especial:");
        Personagem alvoTreino2 = new Arqueiro("Alvo de Palha");
        mago.usarHabilidadeEspecial(alvoTreino2);

        // Arqueiro usa Chuva de Flechas
        System.out.println(">> Arqueiro usa habilidade especial:");
        Personagem alvoTreino3 = new Guerreiro("Espantalho");
        arqueiro.usarHabilidadeEspecial(alvoTreino3);

        // Métodos exclusivos de cada subclasse
        titulo("3B. MÉTODOS EXCLUSIVOS");

        // Cast necessário para acessar método exclusivo do Mago
        ((Mago) mago).regenerarMana(50);

        // Cast necessário para acessar método exclusivo do Arqueiro
        ((Arqueiro) arqueiro).recarregarFlechas(20);

        // ══════════════════════════════════════════════════════════════════════
        // PARTE 4 — Sistema de experiência e Level Up
        // ══════════════════════════════════════════════════════════════════════
        titulo("4. SISTEMA DE EXPERIÊNCIA E LEVEL UP");

        System.out.println(">> Concedendo experiência aos personagens...");
        guerreiro.ganharExperiencia(60);
        guerreiro.ganharExperiencia(60); // total 120 → level up!

        mago.ganharExperiencia(50);
        mago.ganharExperiencia(55);      // total 105 → level up!

        arqueiro.ganharExperiencia(100); // exato 100 → level up!

        System.out.println(">> Status após level up:");
        for (Personagem p : grupo) {
            p.exibirStatus();
        }

        // ══════════════════════════════════════════════════════════════════════
        // PARTE 5 — Batalha individual
        // ══════════════════════════════════════════════════════════════════════
        titulo("5. BATALHA INDIVIDUAL");

        // Cria combatentes frescos para batalha mais emocionante
        Personagem b1 = new Guerreiro("Aragorn");
        Personagem b2 = new Mago("Saruman");

        Arena arena = new Arena();
        arena.batalhar(b1, b2);

        // ══════════════════════════════════════════════════════════════════════
        // PARTE 6 — Torneio Final
        // ══════════════════════════════════════════════════════════════════════
        titulo("6. TORNEIO FINAL — LEGENDS OF JAVA");

        ArrayList<Personagem> participantes = new ArrayList<>();
        participantes.add(new Guerreiro("Thorin II"));
        participantes.add(new Mago("Merlin"));
        participantes.add(new Arqueiro("Robin"));
        participantes.add(new Guerreiro("Kratos"));

        arena.torneio(participantes);

        // ══════════════════════════════════════════════════════════════════════
        // FIM
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║    Obrigado por jogar Legends of Java! 🎮    ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }

    // ─── Helpers de formatação ─────────────────────────────────────────────────

    private static void banner() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║                                              ║");
        System.out.println("║      ⚔️   LEGENDS OF JAVA   ⚔️              ║");
        System.out.println("║         Sistema de RPG em Java               ║");
        System.out.println("║                                              ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println();
    }

    private static void titulo(String texto) {
        System.out.println();
        System.out.println("══════════════════════════════════════════════");
        System.out.println("  " + texto);
        System.out.println("══════════════════════════════════════════════");
    }
}
