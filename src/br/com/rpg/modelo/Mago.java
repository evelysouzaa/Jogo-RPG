package br.com.rpg.modelo;

/**
 * Mago — conjurador arcano com alta mana e ataque, porém baixa defesa.
 * Especialidade: feitiços à distância e magias devastadoras.
 */
public class Mago extends Personagem {

    // ─── Construtor ────────────────────────────────────────────────────────────

    /**
     * Cria um Mago com atributos padrão da classe.
     *
     * @param nome Nome do mago
     */
    public Mago(String nome) {
        super(nome, 80, 120, 30, 8);
    }

    // ─── Métodos abstratos implementados ──────────────────────────────────────

    /**
     * Ataque padrão com bola de fogo — custo 10 de mana, dano ×1.5.
     */
    @Override
    public void atacar(Personagem alvo) {
        int custo = 10;
        if (mana < custo) {
            System.out.printf("  ❌ %s não tem mana suficiente para lançar feitiços! (%d/%d)%n",
                    nome, mana, custo);
            // Ataque físico de emergência sem custo de mana
            System.out.printf("  🪄  %s usa seu cajado em %s!%n", nome, alvo.getNome());
            boolean morreu = alvo.receberDano(ataque / 2);
            if (morreu) ganharExperiencia(50);
            return;
        }

        System.out.printf("  🔥 %s lança uma bola de fogo em %s!%n", nome, alvo.getNome());
        int dano = (int) (ataque * 1.5);
        mana -= custo;
        boolean morreu = alvo.receberDano(dano);
        System.out.printf("  🔵 Mana restante de %s: %d/%d%n", nome, mana, manaMaxima);
        if (morreu) {
            ganharExperiencia(50);
        }
    }

    /**
     * Habilidade especial: Meteoro Arcano — custo 40 de mana, dano ×3.0.
     */
    @Override
    public void usarHabilidadeEspecial(Personagem alvo) {
        int custo = 40;
        if (mana < custo) {
            System.out.printf("  ❌ %s não tem mana suficiente para Meteoro Arcano! (%d/%d)%n",
                    nome, mana, custo);
            return;
        }

        System.out.println();
        System.out.printf("  🌌 %s ergue os braços e invoca os astros...%n", nome);
        System.out.printf("  ☄️  METEORO ARCANO! Uma rocha em chamas cai sobre %s!%n", alvo.getNome());

        int dano = (int) (ataque * 3.0);
        mana -= custo;
        boolean morreu = alvo.receberDano(dano);
        System.out.printf("  🔵 Mana restante de %s: %d/%d%n", nome, mana, manaMaxima);

        if (morreu) {
            ganharExperiencia(50);
        }
        System.out.println();
    }

    @Override
    public String getTipo() {
        return "Mago";
    }

    // ─── Método exclusivo do Mago ──────────────────────────────────────────────

    /**
     * Medita para regenerar mana.
     *
     * @param quantidade Quantidade de mana a restaurar
     */
    public void regenerarMana(int quantidade) {
        int antes = mana;
        mana = Math.min(mana + quantidade, manaMaxima);
        System.out.printf("  🔮 %s medita e recupera %d de mana. Mana: %d/%d%n",
                nome, (mana - antes), mana, manaMaxima);
    }
}
