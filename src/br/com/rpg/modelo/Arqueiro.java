package br.com.rpg.modelo;

/**
 * Arqueiro — combatente à distância ágil com ataque equilibrado.
 * Especialidade: precisão e chuvas de flechas que atingem múltiplas vezes.
 */
public class Arqueiro extends Personagem {

    // ─── Atributo exclusivo ────────────────────────────────────────────────────
    private int flechas;

    // ─── Construtor ────────────────────────────────────────────────────────────

    /**
     * Cria um Arqueiro com atributos padrão e 30 flechas.
     *
     * @param nome Nome do arqueiro
     */
    public Arqueiro(String nome) {
        super(nome, 100, 50, 28, 12);
        this.flechas = 30;
    }

    // ─── Métodos abstratos implementados ──────────────────────────────────────

    /**
     * Ataque padrão — consome 1 flecha, dano ×1.3.
     */
    @Override
    public void atacar(Personagem alvo) {
        if (flechas <= 0) {
            System.out.printf("  ❌ %s está sem flechas! Ataque corpo a corpo forçado!%n", nome);
            System.out.printf("  🗡️  %s golpeia %s com o arco!%n", nome, alvo.getNome());
            boolean morreu = alvo.receberDano(ataque / 2);
            if (morreu) ganharExperiencia(50);
            return;
        }

        System.out.printf("  🏹 %s dispara uma flecha certeira em %s!%n", nome, alvo.getNome());
        int dano = (int) (ataque * 1.3);
        flechas--;
        boolean morreu = alvo.receberDano(dano);
        System.out.printf("  🏹 Flechas restantes de %s: %d%n", nome, flechas);
        if (morreu) {
            ganharExperiencia(50);
        }
    }

    /**
     * Habilidade especial: Chuva de Flechas — custo 15 mana e 5 flechas, 3 ataques de dano ×2.0.
     */
    @Override
    public void usarHabilidadeEspecial(Personagem alvo) {
        int custoMana    = 15;
        int custoFlechas = 5;

        if (mana < custoMana) {
            System.out.printf("  ❌ %s não tem mana suficiente para Chuva de Flechas! (%d/%d)%n",
                    nome, mana, custoMana);
            return;
        }
        if (flechas < custoFlechas) {
            System.out.printf("  ❌ %s não tem flechas suficientes para Chuva de Flechas! (%d/%d)%n",
                    nome, flechas, custoFlechas);
            return;
        }

        System.out.println();
        System.out.printf("  🌬️  %s saca rapidamente múltiplas flechas...%n", nome);
        System.out.printf("  🌧️  CHUVA DE FLECHAS! Três disparos consecutivos em %s!%n", alvo.getNome());

        mana    -= custoMana;
        flechas -= custoFlechas;

        int dano = (int) (ataque * 2.0);
        boolean morreu = false;
        for (int i = 1; i <= 3 && !morreu; i++) {
            System.out.printf("    → Disparo %d: ", i);
            morreu = alvo.receberDano(dano);
        }

        System.out.printf("  🔵 Mana de %s: %d/%d | Flechas: %d%n", nome, mana, manaMaxima, flechas);

        if (morreu) {
            ganharExperiencia(50);
        }
        System.out.println();
    }

    @Override
    public String getTipo() {
        return "Arqueiro";
    }

    // ─── Método exclusivo do Arqueiro ──────────────────────────────────────────

    /**
     * Recarrega o estoque de flechas do arqueiro.
     *
     * @param quantidade Número de flechas a adicionar
     */
    public void recarregarFlechas(int quantidade) {
        flechas += quantidade;
        System.out.printf("  🪶 %s recarrega %d flechas. Total: %d flechas%n", nome, quantidade, flechas);
    }

    public int getFlechas() {
        return flechas;
    }

    @Override
    public void exibirStatus() {
        super.exibirStatus();
        System.out.printf("  🏹 Flechas: %d%n%n", flechas);
    }
}
